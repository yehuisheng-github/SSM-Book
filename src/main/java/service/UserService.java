package service;

import java.util.List;
import pojo.User;

public interface UserService {
	/**
	 * 	ע���û�
	 * @param user	�û�
	 */
	public int registUser(User user);
	
	/**
	 * 	�û���¼
	 * @param user	�û�
	 * @return	����null����ʾ��¼ʧ��
	 */
	public List<User> login(User user);
	
	/**
	 * 	����û����Ƿ����
	 * @param username	�û���
	 * @return	����true��ʾ�û����Ѵ���
	 */
	public boolean existsUsername(String username);
}
