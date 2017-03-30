package models;

import java.util.List;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idUser;
	private String username;
	private String email;
	private String password;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ROLE_ID")
	private Role userRole;
	@OneToMany(mappedBy = "authorOfProposal")
	@Basic(optional=true)
	private List<Proposal> madeProposals;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
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
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	public List<Proposal> getMadeProposals() {
		return madeProposals;
	}
	public void setMadeProposals(List<Proposal> madeProposals) {
		this.madeProposals = madeProposals;
	}
}












