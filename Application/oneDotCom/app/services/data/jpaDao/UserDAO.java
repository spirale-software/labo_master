package services.data.jpaDao;

import javax.inject.Inject;
import play.db.jpa.Transactional;
import services.data.dao.IUserDAO;
import models.User;
import play.db.jpa.JPAApi;


public class UserDAO implements IUserDAO {
	
	
	private JPAApi jpaApi;
	
	@Inject
	public UserDAO(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	@Transactional
	public Long addNewUser(User newUser) {
		
		jpaApi.em().persist(newUser);
		
		return Long.parseLong("2");
	}

	@Override
	public User getUserById(Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByCredentials(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}
}