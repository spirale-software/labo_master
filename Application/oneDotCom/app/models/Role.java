package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long idRole;
	private String roleName;
	@OneToMany(mappedBy = "userRole")
	private List<User> usersWithThisRole;
	public long getIdRole() {
		return idRole;
	}
	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<User> getUsersWithThisRole() {
		return usersWithThisRole;
	}
	public void setUsersWithThisRole(List<User> usersWithThisRole) {
		this.usersWithThisRole = usersWithThisRole;
	}	
}
