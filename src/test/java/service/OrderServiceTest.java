package service;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Cart;
import pojo.CartItem;

public class OrderServiceTest {

	@Test
	public void test() {
		
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderService orderService=(OrderService) ac.getBean("orderServiceImpl");
		
		Cart cart=new Cart();
		
		CartItem cartItem=new CartItem(2, "计算机网络", 2, new BigDecimal(45), new BigDecimal(45*2));
		cart.addItem(cartItem);
		CartItem cartItem2=new CartItem(1, "Java程序设计", 5, new BigDecimal(49.8), new BigDecimal(49.8*5));
		cart.addItem(cartItem2);
		
		String orderId=orderService.createOrder(cart, 1);
		System.out.println(orderId);
	}

}
