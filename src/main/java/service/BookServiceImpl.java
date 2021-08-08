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
 *		isolation=Isolation.DEFAULT,	//ʹ�����ݿ�Ĭ�ϵĸ��뼶��
 *		readOnly=false,
 *		rollbackFor= {
 *			NullPointerException.class,RunningErrorException.class
 *		}
 *	)
 *
 *	@Transactional�����ϴ��붨��һ�£���Ϊ������������ֵ���Ǹ�ע��Ĭ�ϵ�ֵ
 *
 *	propagation��������Ϊ��һ����7��������Ϊ��
 *			PROPAGATION_REQUIRED��
 *				�������������������ǰ�ĳ���������񣬾ͼ��뵱ǰ�����У�����ǰû�����񣬾ʹ���һ���µ�����
 *			PROPAGATION_REQUIRES_NEW��
 *				��������������񣬲��ܵ�ǰ�ĳ����Ƿ�������񣬷���������һ���Լ����µ�����
 *			PROPAGATION_SUPPORTS��
 *				����֧�ֵ�ǰ��������ǰ�ĳ���û������Ҳ�����Է�����ʽ����
 *
 * 	rollbackFor:��������ֵ�ڵ��쳣��������������ع�
 * 		�����߼���spring���ȼ�鷽���׳����쳣�Ƿ���rollbackFor����ֵ��
 * 			1�������rollbackFor�б��У�����ع�
 * 			2���������rollbackFor�б��У��ټ���Ƿ�����ʱ�쳣������Ǿ�����ع�
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
