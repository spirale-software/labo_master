package models;

public class Proposal_Channel {
	private long id;
	private Channel proposedCanal;
	private Proposal Proposition;
	private User propositionWriter;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Channel getProposedCanal() {
		return proposedCanal;
	}
	public void setProposedCanal(Channel proposedCanal) {
		this.proposedCanal = proposedCanal;
	}
	public Proposal getProposition() {
		return Proposition;
	}
	public void setProposition(Proposal proposition) {
		Proposition = proposition;
	}
	public User getPropositionWriter() {
		return propositionWriter;
	}
	public void setPropositionWriter(User propositionWriter) {
		this.propositionWriter = propositionWriter;
	}
}