package services.data.dao;

import com.google.inject.ImplementedBy;

import models.User;
import services.data.jpaDao.UserDAO;

@ImplementedBy(UserDAO.class)
public interface IUserDAO {
	
	public Long insertUser(User newUser);
	public User getUserById(Long idUser);
	public User getUserByCredentials(String email, String pwd);
}
