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
		 *	ֱ�Ӵ�ϵͳ�ж�ȡԴ�����ļ��������ط���classpath:��Ҫ�޸�Ϊ/src/main/resources/
		 *	FileSystemXmlApplicationContext ac=new FileSystemXmlApplicationContext
		 *		("/src/main/resources/applicationContext.xml");
		 */
		//classpath:�ӱ���õ�class·����ȡ�����ļ�
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		String names[]=ac.getBeanDefinitionNames();
		for(String i:names) {
			System.out.println("Spring��������"+i);
		}
	}

	@Test
	public void test1() {
		 WebApplicationContext wac = 
				 (WebApplicationContext) new ClassPathXmlApplicationContext("springmvc.xml");
		String names[]=wac.getBeanDefinitionNames();
		for(String i:names) {
			System.out.println("MVC��������"+i);
		}
	}
}


