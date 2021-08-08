package service;

import java.util.List;
import pojo.Book;

public interface BookService {
	/**
	 * 	添加图书
	 */
	public void addBook(Book book);
	
	/**
	 * 	删除图书
	 */
	public void deleteBookById(Integer id);
	
	/**
	 * 	修改图书
	 */
	public void updateBook(Book book);
	
	/**
	 * 	根据id查询某本书
	 */
	public Book queryBookById(Integer id);
	
	/**
	 * 	查询全部图书
	 */
	public List<Book> queryBooks();
	
	public List<Book> queryByPrice(int min,int max);
}
