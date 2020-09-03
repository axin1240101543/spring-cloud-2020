package com.darren.center.seata.two.dao;

import com.darren.center.seata.two.entity.Two;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TwoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Two record);

    int insertSelective(Two record);

    Two selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Two record);

    int updateByPrimaryKey(Two record);

    List<Two> selectAll();
}