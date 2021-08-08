package dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

public class UserDaoTest {
	
	//ʹ�þ�̬�����������Ը�ֵ
	public static UserDao userDao=null;
	static {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao =  (UserDao) ac.getBean("userDao");
	}
	
//	@Autowired
//	private UserDao userDao;
	
	/**
	 * 	�����û��������û���Ϣ
	 */
	@Test
	public void test0() {
		List<User> list=userDao.queryUserByUserName("admin");
		for(User i:list) {
			System.out.println(i);
		}
	}
	
	/**
	 * 	�����û����������¼
	 */
	@Test
	public void test1() {
		List<User> list=userDao.queryUserByUserNameAndPassword("admin", "admin");
		for(User i:list) {
			System.out.println(i);
		}
	}
	
	/**
	 * 	�û�ע��
	 */
	@Test
	public void test2() {
		User user=new User(null, "yehuisheng", "123456", "yhs@163.com");
		int num=userDao.saveUser(user);
		System.out.println(num==1?"���ݿ����ɹ���":"���ݿ����ʧ�ܣ�");
	}

}
