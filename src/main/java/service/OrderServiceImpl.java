package service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;
import pojo.OrderItem;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderDao orderDao;
	@Autowired
    private OrderItemDao orderItemDao;
	@Autowired
    private BookDao bookDao;
	
	@Transactional
	public String createOrder(Cart cart, Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // 保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),
            		cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId, null);

            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            if(cartItem.getCount() <= book.getStock() && cartItem.getCount()>0) {
                book.setSales( book.getSales() + cartItem.getCount() );
                book.setStock( book.getStock() - cartItem.getCount() );
                bookDao.updateBook(book);
                // 保存订单项到数据库
                orderItemDao.saveOrderItem(orderItem);
            }else {
            	System.out.println("Error：结算的图书数量错误！原因？库存不足或数量小于零！");
            	orderId=null;
            }
        }
        cart.clear();
        return orderId;
    }

	public List<Order> queryOrder(String userId) {
		return orderDao.queryOrder(userId);
	}

	public List<OrderItem> queryOrderItem(String orderId) {
		return orderItemDao.queryOrderItem(orderId);
	}
}

