package service;

import java.util.List;
import pojo.User;

public interface UserService {
	/**
	 * 	注册用户
	 * @param user	用户
	 */
	public int registUser(User user);
	
	/**
	 * 	用户登录
	 * @param user	用户
	 * @return	返回null，表示登录失败
	 */
	public List<User> login(User user);
	
	/**
	 * 	检查用户名是否存在
	 * @param username	用户名
	 * @return	返回true表示用户名已存在
	 */
	public boolean existsUsername(String username);
}
