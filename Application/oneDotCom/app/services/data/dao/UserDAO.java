package services.data.dao;

import java.util.List;

import com.google.inject.ImplementedBy;

import models.User;
import services.data.jpaDao.UserDaoJPA;

@ImplementedBy(UserDaoJPA.class)
public interface UserDAO {
	
	public Long insertUser(User newUser);
	public User getUserById(Long idUser);
	public User getUserByCredentials(String email, String pwd);
	public User getUserByUsernameAndEmail(String username, String email);
	public List<User> getAllUsers();
	public User update(User user);
}
