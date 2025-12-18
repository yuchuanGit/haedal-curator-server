package com.sui.haedal.curator.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sui.haedal.curator.common.R;
import com.sui.haedal.curator.model.bo.HTokenBo;
import com.sui.haedal.curator.model.bo.VaultSubmitQueryBo;
import com.sui.haedal.curator.model.vo.HTokenVo;
import com.sui.haedal.curator.model.vo.VaultSubmitManagementFeeVo;
import com.sui.haedal.curator.model.vo.VaultSubmitPerformanceFeeVo;
import com.sui.haedal.curator.model.vo.VaultSubmitSupplyCapVo;
import com.sui.haedal.curator.service.EarnCuratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/earn")
@Tag(name = "earnCurator接口", description = "earnCurator接口")
public class EarnCuratorRest {


    @Autowired
    private EarnCuratorService service;



    @PostMapping("/submitSupplyCapPageQuery")
    @Operation(summary = "submitSupplyCapPageQuery", description = "vault提交生效cap分页查询")
    public R<IPage<VaultSubmitSupplyCapVo>> submitSupplyCapPageQuery(@RequestBody VaultSubmitQueryBo queryBo){
        return R.data(service.submitSupplyCapPageQuery(queryBo));
    }

    @PostMapping("/submitPerformanceFeePageQuery")
    @Operation(summary = "submitPerformanceFeePageQuery", description = "vault提交绩效费分页查询")
    public R<IPage<VaultSubmitPerformanceFeeVo>> submitPerformanceFeePageQuery(@RequestBody VaultSubmitQueryBo queryBo){
        return R.data(service.submitPerformanceFeePageQuery(queryBo));
    }

    @PostMapping("/submitManagementFeePageQuery")
    @Operation(summary = "submitManagementFeePageQuery", description = "vault提交管理费分页查询")
    public R<IPage<VaultSubmitManagementFeeVo>> submitManagementFeePageQuery(@RequestBody VaultSubmitQueryBo queryBo){
        return R.data(service.submitManagementFeePageQuery(queryBo));
    }


    @PostMapping("/geHTokenInfo")
    @Operation(summary = "geHTokenInfo", description = "geHTokenInfo")
    public R<HTokenVo> geHTokenInfo(@RequestBody @Valid HTokenBo tokenBo){
        return R.data(service.geHTokenInfo(tokenBo));
    }

}
