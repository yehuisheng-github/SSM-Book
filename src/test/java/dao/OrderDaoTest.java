package dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.Order;

public class OrderDaoTest {

	/**
	 * 	添加订单
	 */
	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderDao orderDao =  (OrderDao) ac.getBean("orderDao");
		Order order=new Order("B", new Date(), new BigDecimal(11), 0, 1);
		int number=orderDao.saveOrder(order);
		System.out.println(number==1?"生成订单！":"失败！"); 
		
	}
}
