package service;

import java.util.List;
import pojo.Book;

public interface BookService {
	/**
	 * 	���ͼ��
	 */
	public void addBook(Book book);
	
	/**
	 * 	ɾ��ͼ��
	 */
	public void deleteBookById(Integer id);
	
	/**
	 * 	�޸�ͼ��
	 */
	public void updateBook(Book book);
	
	/**
	 * 	����id��ѯĳ����
	 */
	public Book queryBookById(Integer id);
	
	/**
	 * 	��ѯȫ��ͼ��
	 */
	public List<Book> queryBooks();
	
	public List<Book> queryByPrice(int min,int max);
}
