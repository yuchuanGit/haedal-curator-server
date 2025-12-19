package com.sui.haedal.curator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sui.haedal.curator.model.bo.ClearingUserQueryBo;
import com.sui.haedal.curator.model.bo.HTokenBo;
import com.sui.haedal.curator.model.bo.VaultSubmitQueryBo;
import com.sui.haedal.curator.model.vo.*;

public interface EarnCuratorService {

    /**
     * vault提交生效cap 分页查询
     * @param queryBo
     * @return
     */
    IPage<VaultSubmitSupplyCapVo> submitSupplyCapPageQuery(VaultSubmitQueryBo queryBo);

    /**
     * vault提交绩效费 分页查询
     * @param queryBo
     * @return
     */
    IPage<VaultSubmitPerformanceFeeVo> submitPerformanceFeePageQuery(VaultSubmitQueryBo queryBo);

    /**
     * vault提交管理费 分页查询
     * @param queryBo
     * @return
     */
    IPage<VaultSubmitManagementFeeVo> submitManagementFeePageQuery(VaultSubmitQueryBo queryBo);

    /**
     * 分页查询清算用户
     * @param queryBo
     * @return
     */
    IPage<ClearingUserVo> clearingUserPageQuery(ClearingUserQueryBo queryBo);

    /**
     * geHTokenInfo
     * @param tokenBo
     * @return
     */
    HTokenVo geHTokenInfo(HTokenBo tokenBo);

}
