package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.BookDao;
import pojo.Book;

/**
 *	@Transactional(
 *		propagation=Propagation.REQUIRED,
 *		isolation=Isolation.DEFAULT,	//使用数据库默认的隔离级别
 *		readOnly=false,
 *		rollbackFor= {
 *			NullPointerException.class,RunningErrorException.class
 *		}
 *	)
 *
 *	@Transactional和以上代码定义一致，因为上面代码的属性值都是该注解默认的值
 *
 *	propagation：传播行为，一共有7个传播行为：
 *			PROPAGATION_REQUIRED：
 *				方法必须存在事务，若当前的程序存在事务，就加入当前事务中；若当前没有事务，就创建一个新的事务
 *			PROPAGATION_REQUIRES_NEW：
 *				方法必须存在事务，不管当前的程序是否存在事务，方法都创建一个自己的新的事务
 *			PROPAGATION_SUPPORTS：
 *				方法支持当前事务，若当前的程序没有事务，也可以以非事务方式运行
 *
 * 	rollbackFor:发生属性值内的异常，方法进行事务回滚
 * 		处理逻辑：spring首先检查方法抛出的异常是否在rollbackFor属性值中
 * 			1、如果在rollbackFor列表中，事务回滚
 * 			2、如果不在rollbackFor列表中，再检查是否运行时异常，如果是就事务回滚
 * 
 */
@Transactional
@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	public void deleteBookById(Integer id) {
		bookDao.deleteBookById(id);
	}

	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	public Book queryBookById(Integer id) {
		return bookDao.queryBookById(id);
	}

	public List<Book> queryBooks() {
		return bookDao.queryBooks();
	}

	public List<Book> queryByPrice(int min, int max) {
		return bookDao.queryByPrice(min, max);
	}

}
