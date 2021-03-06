package models;


import services.general.CryptWithMD5;

public class UserVM {
	private long idUser;
	private String username;
	private String email;
	private String password;
	private String role;
	private User user;
		
	public UserVM(){}
	
	public User createUser() {
		user = new User();
		
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setPassword(CryptWithMD5.cryptWithMD5(user.getPassword()));
		
		return user;
	}
	
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
