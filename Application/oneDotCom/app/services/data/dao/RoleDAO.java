package services.data.dao;

import java.util.List;

import com.google.inject.ImplementedBy;

import models.Role;
import services.data.jpaDao.RoleDaoJPA;

@ImplementedBy(RoleDaoJPA.class)
public interface RoleDAO {
	public Role insert(Role role);
	public Role getRoleByName(String roleName);
	public List<Role> getAllRoles();
}
