package services.data.jpaDao;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import models.Role;
import play.db.jpa.JPAApi;
import services.data.dao.RoleDAO;

public class RoleDaoJPA implements RoleDAO {
private final JPAApi jpaApi;
	
	@Inject
	public RoleDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	public Role insert(Role role) {
		jpaApi.em().persist(role);
		
		return role;
	}

	@Override
	public Role getRoleByName(String roleName) {
		try {
			Query query = jpaApi.em().createQuery("SELECT role FROM Role role WHERE role.roleName=:roleName");
			query.setParameter("roleName", roleName);
			
			return (Role) query.getSingleResult();
		} catch (NoResultException e) { }
		
		return null;
	}
}