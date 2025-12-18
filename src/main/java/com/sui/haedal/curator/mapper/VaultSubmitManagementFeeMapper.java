package com.sui.haedal.curator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sui.haedal.curator.model.bo.VaultSubmitQueryBo;
import com.sui.haedal.curator.model.entity.VaultSubmitManagementFee;
import com.sui.haedal.curator.model.vo.VaultSubmitManagementFeeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VaultSubmitManagementFeeMapper extends BaseMapper<VaultSubmitManagementFee> {

    List<VaultSubmitManagementFeeVo> submitManagementFeePageQuery(IPage<VaultSubmitManagementFeeVo> page, VaultSubmitQueryBo queryBo);
}
