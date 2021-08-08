package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pojo.OrderItem;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
    public List<OrderItem> queryOrderItem(@Param("queryOrderItemOrderId")String orderId);
}
