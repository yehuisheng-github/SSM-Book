package dao;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.OrderItem;

public class OrderItemDaoTest {

	/**
	 * 	添加订单项
	 */
	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderItemDao orderItemDao =  (OrderItemDao) ac.getBean("orderItemDao");
		OrderItem orderItem=new OrderItem(1, "s", 11, new BigDecimal(111), new BigDecimal(111*11), "B", null);
		int number=orderItemDao.saveOrderItem(orderItem);
		System.out.println(number==1?"生成订单！":"失败！");	
	}
	
	/*
	 * 	测试一对多查询
	 */
	@Test
	public void test0() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderItemDao orderItemDao =  (OrderItemDao) ac.getBean("orderItemDao");
		List<OrderItem> number=orderItemDao.queryOrderItem("16282463562112");
		number.forEach(i->System.out.println(i));
	}
}
