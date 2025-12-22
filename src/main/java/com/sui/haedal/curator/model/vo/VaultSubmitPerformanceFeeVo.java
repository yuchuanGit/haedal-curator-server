package com.sui.haedal.curator.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(title = "VaultSubmitPerformanceFeeVo结构", description = "vault提交绩效费")
public class VaultSubmitPerformanceFeeVo {

    private Integer id;

    @Schema(description = "金库ID")
    private String vaultId;

    @Schema(description = "金库名称")
    private String vaultName;

    @Schema(description = "金库存资产币种")
    private String assetType;

    @Schema(description = "资产小数位数")
    private Integer assetDecimals;

    @Schema(description = "操作用户")
    private String caller;

    @Schema(description = "手续费/费率")
    private String feeBps;

    @Schema(description = "生效时间")
    private Long validAtMs;

    @Schema(description = "生效时间格式")
    private String validAtMsStr;

    @Schema(description = "事件类型 1 提交 2: 成功 3: 失败")
    private Integer eventType;

    @Schema(description = "数据链上交易unix时间")
    private Long transactionTimeUnix;

    @Schema(description = "数据链上交易时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transactionTime;
}