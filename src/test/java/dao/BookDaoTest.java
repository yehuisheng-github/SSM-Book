package dao;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.Book;

public class BookDaoTest {

	//使用静态代码块给类属性赋值
	public static BookDao bookDao=null;
	static {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		bookDao =  (BookDao) ac.getBean("bookDao");
	}
	
	/**
	 * 	添加图书功能
	 */
	@Test
	public void test() {
		Book book=new Book(100, "计算机辅助设计", new BigDecimal(39), "秦春霞", 0, 100, null);
		int number=bookDao.addBook(book);
		System.out.println(number==1?"添加图书成功！":"添加图书失败！");
	}
	
	/**
	 * 	删除图书功能
	 */
	@Test
	public void test1() {
		int number=bookDao.deleteBookById(100);
		System.out.println(number==1?"删除图书成功！":"删除图书失败！");
	}
	
	/**
	 * 	修改图书功能
	 */
	@Test
	public void test2() {
		Book book=new Book(100, "计算机网络", new BigDecimal(45.0), "谢希仁", 0, 100, null);
		int number=bookDao.updateBook(book);
		System.out.println(number==1?"修改图书成功！":"修改图书失败！");
	}
	
	/**
	 * 	查询某个图书
	 */
	@Test
	public void test3() {
		Book book=bookDao.queryBookById(100);
		System.out.println(book);
	}
	
	/**
	 * 	查询全部图书
	 */
	@Test
	public void test4() {
		List<Book> list=bookDao.queryBooks();
		for(Book i:list) {
			System.out.println(i);
		}
	}
	
	/**
	 * 	根据价格为条件，查询符合条件的数据
	 */
	@Test
	public void test5() {
		List<Book> list=bookDao.queryByPrice(20, 48);
		for(Book i:list) {
			System.out.println(i);
		}
	}
	
}
