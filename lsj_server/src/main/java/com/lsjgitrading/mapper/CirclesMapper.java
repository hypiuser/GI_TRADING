package com.lsjgitrading.mapper;

import com.lsjgitrading.entity.Circles;
import java.util.List;
public interface CirclesMapper {

    int insert(Circles circles);

    int deleteById(Integer id);

    int updateById(Circles circles);

    Circles selectById(Integer id);

    List<Circles> selectAll(Circles circles);

}