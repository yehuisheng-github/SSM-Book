package service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.User;

public class UserServiceTest {

	//�������ʱ��������Ը�ֵ
	public static UserService userService=null;
	static {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		userService =  (UserService) ac.getBean("userServiceImpl");
	}
	
//	@Autowired
//	UserService userService;
//	UserService userService=new UserServiceImpl();
	
	@Test
	public void testRegistUser() {
		if(userService.registUser(new User(null,"yhs123","admin","yhs123@qq.com"))<0) {
			System.out.println("�����û���Ϣʧ��");
		}else {
			System.out.println("�ɹ������û���Ϣ");
		}
	}

	@Test
	public void testLogin() {
		if(userService.login(new User(null,"yhs123","admin","yhs123@qq.com"))==null) {
			System.out.println("��¼ʧ�ܣ�");
		}else {
			System.out.println("��¼successful��");
			System.out.println(userService.login(new User(null,"yhs123","admin","yhs123@qq.com")));
		}
	} 

	@Test
	public void testExistsUsername() {
		if(userService.existsUsername("admin")) {
			System.out.println("�û����Ѵ��ڣ�");
		}else {
			System.out.println("�û������ã�");
		}
	}

}

