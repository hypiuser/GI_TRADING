package com.lsjgitrading.mapper;

import com.lsjgitrading.entity.Goods;
import org.apache.ibatis.annotations.Update;
import java.util.List;
public interface GoodsMapper {

    int insert(Goods goods);

    int deleteById(Integer id);

    int updateById(Goods goods);

    Goods selectById(Integer id);

    List<Goods> selectAll(Goods goods);

    List<Goods> selectFrontAll(Goods goods);

    @Update("update goods set read_count = read_count + 1 where id = #{id}")
    void updateReadCount(Integer id);

}