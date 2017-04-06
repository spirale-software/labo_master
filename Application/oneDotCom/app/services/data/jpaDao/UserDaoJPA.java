package services.data.jpaDao;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import play.db.jpa.Transactional;
import services.data.dao.UserDAO;
import models.User;
import play.db.jpa.JPAApi;


public class UserDaoJPA implements UserDAO {
	
	private final JPAApi jpaApi;
	
	@Inject
	public UserDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	@Transactional
	public Long insertUser(User newUser) {
		
		jpaApi.em().persist(newUser);
		
		return newUser.getIdUser();
	}

	@Override
	@Transactional
	public User getUserById(Long idUser) {
		return jpaApi.em().find(User.class, idUser);
	}

	@Override
	@Transactional
	public User getUserByCredentials(String email, String password) {
		
		try {
			Query query = jpaApi.em().createQuery("SELECT user FROM User user WHERE user.email=:email AND user.password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			return (User) query.getSingleResult();
		} catch (NoResultException e) { }
		
		return null;
	}
}




