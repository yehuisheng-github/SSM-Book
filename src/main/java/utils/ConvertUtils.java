package utils;
public class ConvertUtils {
	/**
	 * 	将String类型转换为int类型
	 * @param id	图书编号，类型：String
	 * @return	返回int类型的数据
	 */
	public static int paraInt(String id,Integer number) {
		try {
			return Integer.parseInt(id);
		}catch(Exception e) {
//			System.out.println("Error：类型转换发生错误！位置：utils.ConvertUtils.paraInt(..)方法！");
		}
		return number;
	}
}
