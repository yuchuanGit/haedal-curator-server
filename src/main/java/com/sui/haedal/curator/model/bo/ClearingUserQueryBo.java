package com.sui.haedal.curator.model.bo;

import com.sui.haedal.curator.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "ClearingUserQueryBo结构", description = "清算用户查询条件")
public class ClearingUserQueryBo extends BasePageQuery {
    @Schema(description = "borrow marketId")
    private String marketId;

    @Schema(description = "用户地址")
    private String userAddress;
}
