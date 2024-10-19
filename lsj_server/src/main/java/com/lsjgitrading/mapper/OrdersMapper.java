package com.lsjgitrading.mapper;

import com.lsjgitrading.entity.Orders;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrdersMapper {

    int insert(Orders orders);

    int deleteById(Integer id);

    int updateById(Orders orders);

    Orders selectById(Integer id);

    List<Orders> selectAll(Orders orders);

    @Select("select * from orders where order_no = #{orderNo}")
    Orders selectByOrderNo(String orderNo);

}