package com.sui.haedal.curator.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * vault提交管理费实体类
 */
@Data
@TableName("vault_submit_management_fee")
public class VaultSubmitManagementFee {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * 金库ID
     */
    private String vaultId;

    /**
     * 操作用户
     */
    private String caller;


    /**
     * 手续费/费率
     */
    private String feeBps;

    /**
     * 生效时间
     */
    private Long validAtMs;

    /**
     * 事件类型 1 提交 2: 成功 3: 失败
     */
    private Integer eventType;


    /**
     * 数据链上交易unix时间
     */
    private Long transactionTimeUnix;

    /**
     * 数据链上交易时间
     */
    private Date transactionTime;
}