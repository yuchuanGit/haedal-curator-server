package com.sui.haedal.curator.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.sui.haedal.curator.common.FileCopyRenameUtil;
import com.sui.haedal.curator.common.ResourceFileUtil;
import com.sui.haedal.curator.common.page.Condition;
import com.sui.haedal.curator.config.HTokenConfig;
import com.sui.haedal.curator.mapper.VaultSubmitManagementFeeMapper;
import com.sui.haedal.curator.mapper.VaultSubmitPerformanceFeeMapper;
import com.sui.haedal.curator.mapper.VaultSubmitSupplyCapMapper;
import com.sui.haedal.curator.model.bo.HTokenBo;
import com.sui.haedal.curator.model.bo.VaultSubmitQueryBo;
import com.sui.haedal.curator.model.vo.HTokenVo;
import com.sui.haedal.curator.model.vo.VaultSubmitManagementFeeVo;
import com.sui.haedal.curator.model.vo.VaultSubmitPerformanceFeeVo;
import com.sui.haedal.curator.model.vo.VaultSubmitSupplyCapVo;
import com.sui.haedal.curator.service.EarnCuratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("earnCuratorService")
public class EarnCuratorServiceImpl implements EarnCuratorService {

    @Resource
    private VaultSubmitSupplyCapMapper submitSupplyCapMapper;

    @Resource
    private VaultSubmitPerformanceFeeMapper submitPerformanceFeeMapper;

    @Resource
    private VaultSubmitManagementFeeMapper submitManagementFeeMapper;

    @Resource
    private HTokenConfig hTokenConfig;



    /**
     * vault提交生效cap 分页查询
     * @param queryBo
     * @return
     */
    @Override
    public IPage<VaultSubmitSupplyCapVo> submitSupplyCapPageQuery(VaultSubmitQueryBo queryBo){
        IPage<VaultSubmitSupplyCapVo> page = Condition.getPage(queryBo);
        page.setRecords(submitSupplyCapMapper.submitSupplyCapPageQuery(page,queryBo));
        return page;
    }


    /**
     * vault提交绩效费 分页查询
     * @param queryBo
     * @return
     */
    @Override
    public IPage<VaultSubmitPerformanceFeeVo> submitPerformanceFeePageQuery(VaultSubmitQueryBo queryBo){
        IPage<VaultSubmitPerformanceFeeVo> page = Condition.getPage(queryBo);
        page.setRecords(submitPerformanceFeeMapper.submitPerformanceFeePageQuery(page,queryBo));
        return page;
    }

    /**
     * vault提交管理费 分页查询
     * @param queryBo
     * @return
     */
    @Override
    public IPage<VaultSubmitManagementFeeVo> submitManagementFeePageQuery(VaultSubmitQueryBo queryBo){
        IPage<VaultSubmitManagementFeeVo> page = Condition.getPage(queryBo);
        page.setRecords(submitManagementFeeMapper.submitManagementFeePageQuery(page,queryBo));
        return page;
    }

    /**
     * geHTokenInfo
     * @param tokenBo
     * @return
     */
    @Override
    public HTokenVo geHTokenInfo(HTokenBo tokenBo){
        HTokenVo vo = new HTokenVo();
        try {
            Map<String, String> replaceMap = new HashMap<>();
            replaceMap.put("MODULE_NAME",tokenBo.getModuleName());
            replaceMap.put("COIN_TYPE",tokenBo.getModuleName().toUpperCase());
            replaceMap.put("COIN_SYMBOL",tokenBo.getCoinSymbol());
            replaceMap.put("COIN_DECIMALS",tokenBo.getCoinDecimals());
            replaceMap.put("COIN_URL",tokenBo.getCoinUrl());
            String htokenReplace = hTokenConfig.getHtokenReplace();
            String htokenReplaceFileName = FileCopyRenameUtil.generateUniqueFileName();
            FileCopyRenameUtil.copyDirAndRename(hTokenConfig.getHtokenTemplate(), htokenReplace, htokenReplaceFileName);
            String htokenReplacePath = htokenReplace+htokenReplaceFileName+"/sources";
            String htokenReplaceFile = htokenReplacePath+"/asset.move";
            ResourceFileUtil.replaceFileContent(htokenReplaceFile, replaceMap, htokenReplaceFile);
            CommandResult commandResult =  executeSuiMoveBuild(htokenReplacePath,null);
            if(commandResult.getExitCode()==0){
                vo = JSONObject.parseObject(commandResult.getStdout(),HTokenVo.class);
            }else{
                log.error("sui 执行失败...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 执行 sui move build 命令（指定工作目录）
     * @param workDir 执行命令的目录（等效于 cd 到该目录）
     * @param suiExecutablePath sui 可执行文件路径（如 Windows: C:/sui/sui.exe; Linux: /usr/local/bin/sui）
     * @return 命令执行结果（退出码、标准输出、错误输出）
     * @throws IOException IO异常
     * @throws InterruptedException 线程中断异常
     */
    private CommandResult executeSuiMoveBuild(String workDir, String suiExecutablePath) throws IOException, InterruptedException {
        // 1. 校验参数
        File workDirFile = new File(workDir);
        Assert.isTrue(workDirFile.exists() && workDirFile.isDirectory(), "执行目录不存在或非目录：" + workDir);
//        File suiFile = new File(suiExecutablePath);
//        Assert.isTrue(suiFile.exists() || isCommandInPath(suiExecutablePath), "sui 命令不存在：" + suiExecutablePath);

        // 2. 构建命令列表（避免字符串拼接，兼容多系统）
        List<String> command = new ArrayList<>();
//        command.add(suiExecutablePath);       // sui 命令路径（优先绝对路径）
        command.add("sui");       // sui 命令路径（优先绝对路径）
        command.add("move");
        command.add("build");
        command.add("--silence-warnings");
        command.add("--dump-bytecode-as-base64");

        // 3. 构建进程：指定工作目录 + 分离输出/错误流
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(workDirFile); // 核心：指定命令执行的目录（等效 cd workDir）
        processBuilder.redirectErrorStream(false); // 分离stdout/stderr，便于分别捕获
        // 设置环境变量（可选：若sui依赖特定环境变量）
        processBuilder.environment().put("PATH", System.getenv("PATH"));

        // 4. 启动进程并读取流（异步读取避免缓冲区阻塞）
        Process process = processBuilder.start();
        String stdout = readStream(process.getInputStream());
        String stderr = readStream(process.getErrorStream());
        int exitCode = process.waitFor(); // 等待命令执行完成

        // 5. 返回封装结果
        return new CommandResult(exitCode, stdout, stderr);
    }

    /**
     * 读取输入流（stdout/stderr）为字符串
     */
    private static String readStream(java.io.InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    /**
     * 命令执行结果封装类
     */
    private  class CommandResult {
        private final int exitCode;    // 退出码（0=成功，非0=失败）
        private final String stdout;   // 标准输出（Base64字节码）
        private final String stderr;   // 错误输出（异常信息）

        public CommandResult(int exitCode, String stdout, String stderr) {
            this.exitCode = exitCode;
            this.stdout = stdout;
            this.stderr = stderr;
        }

        // Getter
        public int getExitCode() { return exitCode; }
        public String getStdout() { return stdout; }
        public String getStderr() { return stderr; }
        public boolean isSuccess() { return exitCode == 0; }
    }


}
