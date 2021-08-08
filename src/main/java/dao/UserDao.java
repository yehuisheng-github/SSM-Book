package dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.User;

/*
 * 	���������ֵʱ��ʹ��Paramע������
 */
public interface UserDao {
	/**
	 * 	����û����Ƿ����---�����û�����ѯ�û���Ϣ
	 * @param username	�û���
	 * @return	�������null��˵��û������û�
	 */
	public List<User> queryUserByUserName(@Param("checkUsername")String username);
	
	/**
	 * 	�û���¼---�����û����������ѯ�û���Ϣ
	 * @param username	�û���
	 *  @param password ����
	 * @return	�������null��˵���û������������
	 */
	public List<User> queryUserByUserNameAndPassword(
			@Param("loginUsername")String username,@Param("loginPassword")String password);
	
	/**
	 * 	ע���û�---�����û���Ϣ
	 * @param username
	 * @return	����-1��ʾ����ʧ�ܣ�������Ӱ�������
	 */
	public int saveUser(User user);
}
