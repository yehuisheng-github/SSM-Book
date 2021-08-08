package dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.User;

/*
 * 	多个参数传值时，使用Param注解区分
 */
public interface UserDao {
	/**
	 * 	检查用户名是否存在---根据用户名查询用户信息
	 * @param username	用户名
	 * @return	如果返回null，说明没有这个用户
	 */
	public List<User> queryUserByUserName(@Param("checkUsername")String username);
	
	/**
	 * 	用户登录---根据用户名和密码查询用户信息
	 * @param username	用户名
	 *  @param password 密码
	 * @return	如果返回null，说明用户名或密码错误
	 */
	public List<User> queryUserByUserNameAndPassword(
			@Param("loginUsername")String username,@Param("loginPassword")String password);
	
	/**
	 * 	注册用户---保存用户信息
	 * @param username
	 * @return	返回-1表示操作失败，其他是影响的行数
	 */
	public int saveUser(User user);
}
