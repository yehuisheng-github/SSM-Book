package service;

import java.util.List;

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> queryOrder(String userId);
    public List<OrderItem> queryOrderItem(String orderId);
}
