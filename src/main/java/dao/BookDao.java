package dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.Book;
public interface BookDao {
	/**
	 * 	���ͼ��
	 */
	public int addBook(Book book);
	
	/**
	 * 	ɾ��ͼ��
	 */
	public int deleteBookById(Integer id);
	
	/**
	 * 	�޸�ͼ��
	 */
	public int updateBook(Book book);
	
	/**
	 * 	����id��ѯĳ����
	 */
	public Book queryBookById(Integer id);
	
	/**
	 * 	��ѯȫ��ͼ��
	 */
	public List <Book> queryBooks();
	
	/**
	 * 	���ݼ۸�Ϊ��������ѯ��������������
	 * @param min	��ͼ۸�
	 * @param max	��߼۸�
	 */
	public List<Book> queryByPrice(@Param("betweenMin")int min, @Param("betweenMax")int max);
	
}
