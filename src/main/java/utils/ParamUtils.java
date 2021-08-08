package utils;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

/*
 * 	代码优化：
 * 		通过BeanUtils类把客户端的所有请求参数注入到JavaBean对象中
 * 		就不需要每个请求参数都创建一个变量去接收
 */
public class ParamUtils {
	
	/**
	 * 	通过JavaBean对象的setXxx()方法，将客户端的请求参数注入到JavaBean当中
	 * 	因为方法使用了httpServelt，只能在web层，耦合度高
	 */
	public static  void getParamUtils(HttpServletRequest request,Object bean) {
		try {
//			System.out.println("参数注入之前："+bean);
			BeanUtils.populate(bean, request.getParameterMap());
//			System.out.println("参数注入之后："+bean); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	通过Map的值，将客户端的请求参数注入到JavaBean属性当中
	 * 	方法可以JavaEE三层架构中的任意一层，没有限制，耦合度低
	 * 
	 * 	使用泛型，返回一个对象时不需要类型强制转换
	 */
	public static <T> T getValueUtils(Map value,T bean) {
		try {
//			System.out.println("参数注入之前："+bean);
			BeanUtils.populate(bean,value);
//			System.out.println("参数注入之后："+bean);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 	去除小数部分，取整
	 */
	public static Integer reUpdate(String name) {
		if(name.contains(".")) {
			name=name.substring(0,name.indexOf("."));
		}
		return ConvertUtils.paraInt(name, 1);
	}
}
