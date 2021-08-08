package utils;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

/*
 * 	�����Ż���
 * 		ͨ��BeanUtils��ѿͻ��˵������������ע�뵽JavaBean������
 * 		�Ͳ���Ҫÿ���������������һ������ȥ����
 */
public class ParamUtils {
	
	/**
	 * 	ͨ��JavaBean�����setXxx()���������ͻ��˵��������ע�뵽JavaBean����
	 * 	��Ϊ����ʹ����httpServelt��ֻ����web�㣬��϶ȸ�
	 */
	public static  void getParamUtils(HttpServletRequest request,Object bean) {
		try {
//			System.out.println("����ע��֮ǰ��"+bean);
			BeanUtils.populate(bean, request.getParameterMap());
//			System.out.println("����ע��֮��"+bean); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	ͨ��Map��ֵ�����ͻ��˵��������ע�뵽JavaBean���Ե���
	 * 	��������JavaEE����ܹ��е�����һ�㣬û�����ƣ���϶ȵ�
	 * 
	 * 	ʹ�÷��ͣ�����һ������ʱ����Ҫ����ǿ��ת��
	 */
	public static <T> T getValueUtils(Map value,T bean) {
		try {
//			System.out.println("����ע��֮ǰ��"+bean);
			BeanUtils.populate(bean,value);
//			System.out.println("����ע��֮��"+bean);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 	ȥ��С�����֣�ȡ��
	 */
	public static Integer reUpdate(String name) {
		if(name.contains(".")) {
			name=name.substring(0,name.indexOf("."));
		}
		return ConvertUtils.paraInt(name, 1);
	}
}
