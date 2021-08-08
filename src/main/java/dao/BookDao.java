package dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.Book;
public interface BookDao {
	/**
	 * 	添加图书
	 */
	public int addBook(Book book);
	
	/**
	 * 	删除图书
	 */
	public int deleteBookById(Integer id);
	
	/**
	 * 	修改图书
	 */
	public int updateBook(Book book);
	
	/**
	 * 	根据id查询某本书
	 */
	public Book queryBookById(Integer id);
	
	/**
	 * 	查询全部图书
	 */
	public List <Book> queryBooks();
	
	/**
	 * 	根据价格为条件，查询符合条件的数据
	 * @param min	最低价格
	 * @param max	最高价格
	 */
	public List<Book> queryByPrice(@Param("betweenMin")int min, @Param("betweenMax")int max);
	
}
