package com.sui.haedal.curator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sui.haedal.curator.model.bo.VaultSubmitQueryBo;
import com.sui.haedal.curator.model.entity.VaultSubmitPerformanceFee;
import com.sui.haedal.curator.model.vo.VaultSubmitPerformanceFeeVo;
import com.sui.haedal.curator.model.vo.VaultSubmitSupplyCapVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VaultSubmitPerformanceFeeMapper extends BaseMapper<VaultSubmitPerformanceFee> {

    List<VaultSubmitPerformanceFeeVo> submitPerformanceFeePageQuery(IPage<VaultSubmitPerformanceFeeVo> page, VaultSubmitQueryBo queryBo);
}
