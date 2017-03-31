package models;

import java.util.Date;

public class ProposalVM {
	private long proposalAuthorId;
	private String proposalName;
	private String proposedWriter;
	private String proposedChannel;
		
	public ProposalVM() {
		
	}
	
	public Proposal createProposal() {
		Proposal newProposal = new Proposal();
		
		newProposal.setCreationDate(new Date());
		newProposal.setProposalName(this.proposalName);
		
		if(!this.proposedWriter.isEmpty()) {
			newProposal.setProposalState(ProposalState.Assigned);
			
		} else {
			newProposal.setProposalState(ProposalState.Created);
		}
		
		return newProposal;
	}
	
	public long getProposalAuthorId() {
		return proposalAuthorId;
	}
	public void setProposalAuthorId(long proposalAuthorId) {
		this.proposalAuthorId = proposalAuthorId;
	}
	public String getProposalName() {
		return proposalName;
	}
	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}
	public String getProposedWriter() {
		return proposedWriter;
	}
	public void setProposedWriter(String proposedWriter) {
		this.proposedWriter = proposedWriter;
	}
	public String getProposedChannels() {
		return proposedChannel;
	}
	public void setProposedChannels(String proposedChannel) {
		this.proposedChannel = proposedChannel;
	}
}