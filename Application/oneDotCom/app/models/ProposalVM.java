package models;

public class ProposalVM {
	private Long idProposal;
	private long proposalAuthorId;
	private String proposalName;
	private String proposedWriter;
	private String proposedChannel;
	
	private String twitterAdded;
	private String facebookAdded;
	private String mailingListAdded;
	
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
	public String getProposedChannel() {
		return proposedChannel;
	}
	public void setProposedChannel(String proposedChannel) {
		this.proposedChannel = proposedChannel;
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
}
