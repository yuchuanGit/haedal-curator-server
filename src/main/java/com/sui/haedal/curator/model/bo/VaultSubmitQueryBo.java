package com.sui.haedal.curator.model.bo;

import com.sui.haedal.curator.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "VaultSubmitSupplyCapQueryBo结构", description = "vault提交生效cap查询条件")
public class VaultSubmitQueryBo extends BasePageQuery {

    @Schema(description = "earn vaultId")
    private String vaultId;

    @Schema(description = "earn vaultName")
    private String vaultName;

    @Schema(description = "状态查询")
    private List<String> eventTypes;

    @Schema(description = "用户地址 submit权限查询(owner_or_curator)")
    private String submitUserAddress;

    @Schema(description = "用户地址 verify查询(owner_or_guardian)")
    private String verifyUserAddress;
}
