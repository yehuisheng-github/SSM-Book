package utils;
public class ConvertUtils {
	/**
	 * 	��String����ת��Ϊint����
	 * @param id	ͼ���ţ����ͣ�String
	 * @return	����int���͵�����
	 */
	public static int paraInt(String id,Integer number) {
		try {
			return Integer.parseInt(id);
		}catch(Exception e) {
//			System.out.println("Error������ת����������λ�ã�utils.ConvertUtils.paraInt(..)������");
		}
		return number;
	}
}
