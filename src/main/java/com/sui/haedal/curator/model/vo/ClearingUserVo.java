package com.sui.haedal.curator.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "ClearingUserVo结构", description = "清算用户")
public class ClearingUserVo {

    private Integer id;


    @Schema(description = "用户地址")
    private String userAddress;

    @Schema(description = "市场ID")
    private String marketId;

    @Schema(description = "存入资产")
    private String supplyAssets;

    @Schema(description = "存入份额")
    private String supplyShares;

    @Schema(description = "用户抵押数量")
    private String collateral;

    @Schema(description = "用户借款资产数量")
    private String borrowAssets;

    @Schema(description = "用户借款份额")
    private String borrowShares;

    @Schema(description = "健康度")
    private String healthFactor;

    @Schema(description = "可提取资产")
    private String withdrawableAssets;

    @Schema(description = "最大可借量")
    private String maxBorrowable;

    @Schema(description = "最大可提取抵押")
    private String maxWithdrawableCollateral;

    @Schema(description = "市场名称")
    private String marketTitle;

    @JsonProperty("CollateralCoinDecimals")
    private Integer collateralCoinDecimals;//抵押币种精度

    @JsonProperty("LoanCoinDecimals")
    private Integer loanCoinDecimals;//贷款币种精度
}
