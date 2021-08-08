package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Order;

public interface OrderDao {

    public int saveOrder(Order order);
    public List<Order> queryOrder(@Param("queryOrderUserId")String userId);
}
