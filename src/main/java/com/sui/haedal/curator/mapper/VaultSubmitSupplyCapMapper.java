package com.sui.haedal.curator.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sui.haedal.curator.model.bo.VaultSubmitQueryBo;
import com.sui.haedal.curator.model.entity.VaultSubmitSupplyCap;
import com.sui.haedal.curator.model.vo.VaultSubmitSupplyCapVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface VaultSubmitSupplyCapMapper extends BaseMapper<VaultSubmitSupplyCap> {



    List<VaultSubmitSupplyCapVo> submitSupplyCapPageQuery(IPage<VaultSubmitSupplyCapVo> page, VaultSubmitQueryBo queryBo);

}
