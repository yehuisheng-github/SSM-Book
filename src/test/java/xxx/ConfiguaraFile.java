package xxx;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class ConfiguaraFile {

	@Test
	public void test() {
		/**
		 *	直接从系统中读取源配置文件，其他地方的classpath:需要修改为/src/main/resources/
		 *	FileSystemXmlApplicationContext ac=new FileSystemXmlApplicationContext
		 *		("/src/main/resources/applicationContext.xml");
		 */
		//classpath:从编译好的class路径读取配置文件
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		String names[]=ac.getBeanDefinitionNames();
		for(String i:names) {
			System.out.println("Spring容器对象："+i);
		}
	}

	@Test
	public void test1() {
		 WebApplicationContext wac = 
				 (WebApplicationContext) new ClassPathXmlApplicationContext("springmvc.xml");
		String names[]=wac.getBeanDefinitionNames();
		for(String i:names) {
			System.out.println("MVC容器对象："+i);
		}
	}
}


