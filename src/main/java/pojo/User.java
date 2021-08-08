package pojo;
/**
 * 	编写数据库对应的JavaBean对象
 * 	私有属性，get/set方法，重写toString，无参构造，有参构造
 * @author 32326
 *
 */
public class User {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String code;
	

	public String getCode() {
		return code;
	} 
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User() {
		super();
	}
	public User(Integer id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", code="
				+ code + "]";
	}
}
