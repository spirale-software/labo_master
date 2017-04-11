package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PropositionOfChannel {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proposedChannel")
	private Channel proposedChannel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="concernedProposal")
	private Proposal concernedProposal;
	
	@Enumerated(EnumType.STRING)
	private ChannelPropositionType type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Channel getProposedChannel() {
		return proposedChannel;
	}

	public void setProposedChannel(Channel proposedChannel) {
		this.proposedChannel = proposedChannel;
	}

	public Proposal getConcernedProposal() {
		return concernedProposal;
	}

	public void setConcernedProposal(Proposal concernedProposal) {
		this.concernedProposal = concernedProposal;
	}

	public ChannelPropositionType getType() {
		return type;
	}

	public void setType(ChannelPropositionType type) {
		this.type = type;
	}
}
