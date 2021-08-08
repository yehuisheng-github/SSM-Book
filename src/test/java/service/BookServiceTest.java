package service;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.github.pagehelper.PageHelper;
import dao.UserDao;
import pojo.Book;

public class BookServiceTest {

	//加载类的时候给类属性赋值
	public static BookService bookService=null;
	static {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		bookService=(BookService) ac.getBean("bookServiceImpl");
	}
	
//	@Autowired
//	private BookService bookService;
//	private BookService bookService=new BookServiceImpl();
	
	@Test
	public void testAddBook() {
		bookService.addBook(new Book(null, "数据结构（C语言版）", new BigDecimal(28), "严薇敏", 0, 100, null));
	}

	@Test
	public void testDeleteBookById() {
		bookService.deleteBookById(12);
	}
	
	@Test
	public void testUpdateBook() {
		bookService.updateBook(new Book(11, "数据结构（C语言版）", new BigDecimal(28), "严薇敏", 0, 200, null));
	}
	
	@Test
	public void testQueryBookById() {
		Book book=bookService.queryBookById(1);
		System.out.println(book);
	}

	@Test
	public void testQueryBooks() {
		PageHelper.startPage(2, 4);
		List<Book> list=bookService.queryBooks();
		for(Book i:list) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testQueryByPrice() {
		List<Book> list=bookService.queryByPrice(20, 30);
		for(Book i:list) {
			System.out.println(i);
		}
	}

}
