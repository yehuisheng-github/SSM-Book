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

	//ʹ�þ�̬�����������Ը�ֵ
	public static BookDao bookDao=null;
	static {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		bookDao =  (BookDao) ac.getBean("bookDao");
	}
	
	/**
	 * 	���ͼ�鹦��
	 */
	@Test
	public void test() {
		Book book=new Book(100, "������������", new BigDecimal(39), "�ش�ϼ", 0, 100, null);
		int number=bookDao.addBook(book);
		System.out.println(number==1?"���ͼ��ɹ���":"���ͼ��ʧ�ܣ�");
	}
	
	/**
	 * 	ɾ��ͼ�鹦��
	 */
	@Test
	public void test1() {
		int number=bookDao.deleteBookById(100);
		System.out.println(number==1?"ɾ��ͼ��ɹ���":"ɾ��ͼ��ʧ�ܣ�");
	}
	
	/**
	 * 	�޸�ͼ�鹦��
	 */
	@Test
	public void test2() {
		Book book=new Book(100, "���������", new BigDecimal(45.0), "лϣ��", 0, 100, null);
		int number=bookDao.updateBook(book);
		System.out.println(number==1?"�޸�ͼ��ɹ���":"�޸�ͼ��ʧ�ܣ�");
	}
	
	/**
	 * 	��ѯĳ��ͼ��
	 */
	@Test
	public void test3() {
		Book book=bookDao.queryBookById(100);
		System.out.println(book);
	}
	
	/**
	 * 	��ѯȫ��ͼ��
	 */
	@Test
	public void test4() {
		List<Book> list=bookDao.queryBooks();
		for(Book i:list) {
			System.out.println(i);
		}
	}
	
	/**
	 * 	���ݼ۸�Ϊ��������ѯ��������������
	 */
	@Test
	public void test5() {
		List<Book> list=bookDao.queryByPrice(20, 48);
		for(Book i:list) {
			System.out.println(i);
		}
	}
	
}
