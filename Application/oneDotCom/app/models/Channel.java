package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Channel {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long channelId;
	
	private String channelName;
	
	@OneToMany(mappedBy = "proposedChannel")
	private List<PropositionOfChannel> listOfpropositionsOfChannel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MANAGER_ID")
	private User channelManager;
	
	@OneToMany(mappedBy = "channel")
	private List<FieldChannel> listOfFieldChannels;
	
	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List<PropositionOfChannel> getListOfpropositionsOfChannel() {
		return listOfpropositionsOfChannel;
	}

	public void setListOfpropositionsOfChannel(List<PropositionOfChannel> listOfpropositionsOfChannel) {
		this.listOfpropositionsOfChannel = listOfpropositionsOfChannel;
	}

	public User getChannelManager() {
		return channelManager;
	}

	public void setChannelManager(User channelManager) {
		this.channelManager = channelManager;
	}

	public List<FieldChannel> getListOfFieldChannels() {
		return listOfFieldChannels;
	}

	public void setListOfFieldChannels(List<FieldChannel> listOfFieldChannels) {
		this.listOfFieldChannels = listOfFieldChannels;
	}
}
