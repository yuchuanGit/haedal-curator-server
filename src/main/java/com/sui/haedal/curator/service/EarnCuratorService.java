package com.sui.haedal.curator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sui.haedal.curator.model.bo.HTokenBo;
import com.sui.haedal.curator.model.bo.VaultSubmitQueryBo;
import com.sui.haedal.curator.model.vo.HTokenVo;
import com.sui.haedal.curator.model.vo.VaultSubmitManagementFeeVo;
import com.sui.haedal.curator.model.vo.VaultSubmitPerformanceFeeVo;
import com.sui.haedal.curator.model.vo.VaultSubmitSupplyCapVo;

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
     * geHTokenInfo
     * @param tokenBo
     * @return
     */
    HTokenVo geHTokenInfo(HTokenBo tokenBo);

}
