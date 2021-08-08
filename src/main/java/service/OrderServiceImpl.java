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
        // ������===Ψһ��
        String orderId = System.currentTimeMillis()+""+userId;
        // ����һ����������
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // ���涩��
        orderDao.saveOrder(order);

        // �������ﳵ��ÿһ����Ʒ��ת����Ϊ������浽���ݿ�
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // ��ȡÿһ�����ﳵ�е���Ʒ��
            CartItem cartItem = entry.getValue();
            // ת��Ϊÿһ��������
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),
            		cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId, null);

            // ���¿�������
            Book book = bookDao.queryBookById(cartItem.getId());
            if(cartItem.getCount() <= book.getStock() && cartItem.getCount()>0) {
                book.setSales( book.getSales() + cartItem.getCount() );
                book.setStock( book.getStock() - cartItem.getCount() );
                bookDao.updateBook(book);
                // ���涩������ݿ�
                orderItemDao.saveOrderItem(orderItem);
            }else {
            	System.out.println("Error�������ͼ����������ԭ�򣿿�治�������С���㣡");
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

