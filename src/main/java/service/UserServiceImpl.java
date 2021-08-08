package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public int registUser(User user) {
		return userDao.saveUser(user);
	}

	public List<User> login(User user){
		return userDao.queryUserByUserNameAndPassword(user.getUsername(), user.getPassword());
	}
	
	public boolean existsUsername(String username) {
		if(userDao.queryUserByUserName(username).size()==0) {
			return false;
		} 
		return true;
	}
}
