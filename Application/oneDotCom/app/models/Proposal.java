package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Proposal {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idProposal;
	
	private String proposalName;
	
	@Enumerated(EnumType.STRING)
	private ProposalState proposalState;
	
	private Date creationDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHOR_ID")
	private User authorOfProposal;
	
	@OneToMany(mappedBy="concernedProposal")
	private List<PropositionOfChannel> listOfPropositionsOfChannel;
	
	@OneToMany(mappedBy="concernedProposal")
	private List<PropositionOfWriter> listOfPropositionsOfWriter;

	public Long getIdProposal() {
		return idProposal;
	}

	public void setIdProposal(Long idProposal) {
		this.idProposal = idProposal;
	}

	public String getProposalName() {
		return proposalName;
	}

	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}

	public ProposalState getProposalState() {
		return proposalState;
	}

	public void setProposalState(ProposalState proposalState) {
		this.proposalState = proposalState;
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

	public List<PropositionOfChannel> getListOfPropositionsOfChannel() {
		return listOfPropositionsOfChannel;
	}

	public void setListOfPropositionsOfChannel(List<PropositionOfChannel> listOfPropositionsOfChannel) {
		this.listOfPropositionsOfChannel = listOfPropositionsOfChannel;
	}

	public List<PropositionOfWriter> getListOfPropositionsOfWriter() {
		return listOfPropositionsOfWriter;
	}

	public void setListOfPropositionsOfWriter(List<PropositionOfWriter> listOfPropositionsOfWriter) {
		this.listOfPropositionsOfWriter = listOfPropositionsOfWriter;
	}		
}
