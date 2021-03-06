package models;

import java.util.Date;

public class ProposalVM {
	private Long idProposal;
	private long proposalAuthorId;
	private String proposalName;
	private String proposedWriter;
	private Date deadLine;
	
	private String twitterProposed;
	private String facebookProposed;
	private String mailingListProposed;
	
	private String twitterAdded;
	private String facebookAdded;
	private String mailingListAdded;
	
	private String proposedChannels;
	private String proposalAuthorName;
	private String dateCreation;
	private String proposalState;
	
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
	public Long getIdProposal() {
		return idProposal;
	}
	public void setIdProposal(Long idProposal) {
		this.idProposal = idProposal;
	}
	public String getTwitterAdded() {
		return twitterAdded;
	}
	public void setTwitterAdded(String twitterAdded) {
		this.twitterAdded = twitterAdded;
	}
	public String getFacebookAdded() {
		return facebookAdded;
	}
	public void setFacebookAdded(String facebookAdded) {
		this.facebookAdded = facebookAdded;
	}
	public String getMailingListAdded() {
		return mailingListAdded;
	}
	public void setMailingListAdded(String mailingListAdded) {
		this.mailingListAdded = mailingListAdded;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	public String getTwitterProposed() {
		return twitterProposed;
	}
	public void setTwitterProposed(String twitterProposed) {
		this.twitterProposed = twitterProposed;
	}
	public String getFacebookProposed() {
		return facebookProposed;
	}
	public void setFacebookProposed(String facebookProposed) {
		this.facebookProposed = facebookProposed;
	}
	public String getMailingListProposed() {
		return mailingListProposed;
	}
	public void setMailingListProposed(String mailingListProposed) {
		this.mailingListProposed = mailingListProposed;
	}
	public String getProposedChannels() {
		return proposedChannels;
	}
	public void setProposedChannels(String proposedChannels) {
		this.proposedChannels = proposedChannels;
	}
	public String getProposalAuthorName() {
		return proposalAuthorName;
	}
	public void setProposalAuthorName(String proposalAuthorName) {
		this.proposalAuthorName = proposalAuthorName;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getProposalState() {
		return proposalState;
	}
	public void setProposalState(String proposalState) {
		this.proposalState = proposalState;
	}
	
}





