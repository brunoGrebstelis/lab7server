package obj;

public class UserInfo {
	
	private int user_id;
	private String login;
	private String password;
	
	public UserInfo(int user_id, String login, String password) {
		this.user_id=user_id;
		this.login=login;
		this.password=password;
	}
	
	public int getUser_id() {
		return this.user_id;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}

}
