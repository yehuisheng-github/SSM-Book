package service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.User;

public class UserServiceTest {

	//加载类的时候给类属性赋值
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
			System.out.println("保存用户信息失败");
		}else {
			System.out.println("成功保存用户信息");
		}
	}

	@Test
	public void testLogin() {
		if(userService.login(new User(null,"yhs123","admin","yhs123@qq.com"))==null) {
			System.out.println("登录失败！");
		}else {
			System.out.println("登录successful！");
			System.out.println(userService.login(new User(null,"yhs123","admin","yhs123@qq.com")));
		}
	} 

	@Test
	public void testExistsUsername() {
		if(userService.existsUsername("admin")) {
			System.out.println("用户名已存在！");
		}else {
			System.out.println("用户名可用！");
		}
	}

}

