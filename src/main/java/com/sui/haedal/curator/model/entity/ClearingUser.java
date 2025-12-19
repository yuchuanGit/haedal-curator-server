package com.sui.haedal.curator.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 清算用户表实体类
 */
@Data
@TableName("clearing_user")
public class ClearingUser {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 市场ID
     */
    private String marketId;

    /**
     * 存入资产
     */
    private String supplyAssets;

    /**
     * 存入份额
     */
    private String supplyShares;

    /**
     * 用户抵押数量
     */
    private String collateral;

    /**
     * 用户借款资产数量
     */
    private String borrowAssets;

    /**
     * 用户借款份额
     */
    private String borrowShares;

    /**
     * 健康度
     */
    private String healthFactor;

    /**
     * 可提取资产
     */
    private String withdrawableAssets;

    /**
     * 最大可借量
     */
    private String maxBorrowable;

    /**
     * 最大可提取抵押
     */
    private String maxWithdrawableCollateral;
}