package viewModel;


public class ProposalVM {
	private long proposalAuthorId;
	private String proposalName;
	private String proposedWriter;
	private String proposedChannels;
	
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
		return proposedChannels;
	}
	public void setProposedChannels(String proposedChannels) {
		this.proposedChannels = proposedChannels;
	}
}