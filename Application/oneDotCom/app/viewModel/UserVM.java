package viewModel;

import javax.inject.Inject;

import models.Role;
import models.RoleType;
import models.User;
import services.data.dao.IUserDAO;
import services.general.CryptWithMD5;

public class UserVM {
	private long idUser;
	private String username;
	private String email;
	private String password;
	private RoleType role;
	
	private User user;
	private IUserDAO userDAO;
	
	@Inject
	public UserVM(User user, IUserDAO userDAO) {
		this.user = user;
		this.userDAO = userDAO;
	}
	
	public void createAndSaveUser() {
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(CryptWithMD5.cryptWithMD5(user.getPassword()));
		
		this.userDAO.addNewUser(user);
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
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
}
