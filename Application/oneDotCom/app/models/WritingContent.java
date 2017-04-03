package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class WritingContent {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="WRITER_ID")
	private User writer;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROPROSAL_ID")
	private Proposal concernedProposal;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROPOSAL_CONTENT_ID")
	private ProposalContent content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public Proposal getConcernedProposal() {
		return concernedProposal;
	}

	public void setConcernedProposal(Proposal concernedProposal) {
		this.concernedProposal = concernedProposal;
	}

	public ProposalContent getContent() {
		return content;
	}

	public void setContent(ProposalContent content) {
		this.content = content;
	}
}
