package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Proposal {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idProposal;
	private String ProposalName;
	@Enumerated(EnumType.STRING)
	private ProposalState proposalState;
	private Date creationDate;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHOR_ID")
	private User authorOfProposal;
	
	public Long getIdProposal() {
		return idProposal;
	}

	public void setIdProposal(Long idProposal) {
		this.idProposal = idProposal;
	}

	public String getProposalName() {
		return ProposalName;
	}

	public void setProposalName(String proposalName) {
		ProposalName = proposalName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getAuthorOfProposal() {
		return authorOfProposal;
	}

	public void setAuthorOfProposal(User authorOfProposal) {
		this.authorOfProposal = authorOfProposal;
	}

	public ProposalState getProposalState() {
		return proposalState;
	}

	public void setProposalState(ProposalState propositionState) {
		this.proposalState = propositionState;
	}	
}