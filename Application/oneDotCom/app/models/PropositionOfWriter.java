package models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PropositionOfWriter {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROPOSAL_ID")
	private Proposal concernedProposal;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSIGNEE_ID")
	private User assignee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSIGNATOR_ID")
	private User assignator;
	
	@Basic(optional=true)
	private Date deadLine;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Proposal getConcernedProposal() {
		return concernedProposal;
	}

	public void setConcernedProposal(Proposal concernedProposal) {
		this.concernedProposal = concernedProposal;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public User getAssignator() {
		return assignator;
	}

	public void setAssignator(User assignator) {
		this.assignator = assignator;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
}
