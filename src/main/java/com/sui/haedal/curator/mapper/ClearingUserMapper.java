package com.sui.haedal.curator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sui.haedal.curator.model.bo.ClearingUserQueryBo;
import com.sui.haedal.curator.model.entity.ClearingUser;
import com.sui.haedal.curator.model.vo.ClearingUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClearingUserMapper extends BaseMapper<ClearingUser> {

    List<ClearingUserVo> clearingUserPageQuery(IPage<ClearingUserVo> page, ClearingUserQueryBo queryBo);
}
