package com.darren.center.seata.one.dao;

import com.darren.center.seata.one.entity.One;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OneDao {
    int deleteByPrimaryKey(Integer id);

    int insert(One record);

    int insertSelective(One record);

    One selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(One record);

    int updateByPrimaryKey(One record);
}