package com.darren.center.seata.three.dao;

import com.darren.center.seata.three.entity.Three;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThreeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Three record);

    int insertSelective(Three record);

    Three selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Three record);

    int updateByPrimaryKey(Three record);
}