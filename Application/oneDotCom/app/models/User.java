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
	private List<Proposal> listOfProposals;
	
	@OneToMany(mappedBy = "assignee")
	private List<PropositionOfWriter> assigneePropositionsOfWriter;
	
	@OneToMany(mappedBy = "assignator")
	private List<PropositionOfWriter> assignatorPropositionsOfWriter;
	
	@OneToMany(mappedBy = "channelManager")
	private List<Channel> managedChannels;

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

	public List<Proposal> getListOfProposals() {
		return listOfProposals;
	}

	public void setListOfProposals(List<Proposal> listOfProposals) {
		this.listOfProposals = listOfProposals;
	}

	public List<PropositionOfWriter> getAssigneePropositionsOfWriter() {
		return assigneePropositionsOfWriter;
	}

	public void setAssigneePropositionsOfWriter(List<PropositionOfWriter> assigneePropositionsOfWriter) {
		this.assigneePropositionsOfWriter = assigneePropositionsOfWriter;
	}

	public List<PropositionOfWriter> getAssignatorPropositionsOfWriter() {
		return assignatorPropositionsOfWriter;
	}

	public void setAssignatorPropositionsOfWriter(List<PropositionOfWriter> assignatorPropositionsOfWriter) {
		this.assignatorPropositionsOfWriter = assignatorPropositionsOfWriter;
	}

	public List<Channel> getManagedChannels() {
		return managedChannels;
	}

	public void setManagedChannels(List<Channel> managedChannels) {
		this.managedChannels = managedChannels;
	}
}












