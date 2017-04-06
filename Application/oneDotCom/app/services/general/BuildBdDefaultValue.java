package services.general;

import javax.inject.Inject;

import models.Channel;
import models.Role;
import play.db.jpa.Transactional;
import services.data.dao.ChannelDAO;
import services.data.dao.RoleDAO;

public class BuildBdDefaultValue {
	
	private ChannelDAO channelDAO;
	private RoleDAO roleDAO;
	
	@Inject
	public BuildBdDefaultValue(ChannelDAO channelDAO, RoleDAO roleDAO) {
		this.channelDAO = channelDAO;
		this.roleDAO = roleDAO;
	}
	
	@Transactional
	public void createDbDefaultValue() {
		fillDBRole();
		fillDBChannel();
	}
	
	@Transactional
	public void fillDBRole() {
		Role role1 = new Role();
		role1.setRoleName("MEMBRE_FACULTAIRE");
		roleDAO.insert(role1);
		
		Role role2 = new Role();
		role2.setRoleName("RESPONSABLE_CANAL");
		roleDAO.insert(role2);
		
		Role role3 = new Role();
		role3.setRoleName("RESPONSABLE_COMMUNICATION");
		roleDAO.insert(role3);
		
		Role role4 = new Role();
		role4.setRoleName("PRESIDENT");
		roleDAO.insert(role4);
	
	}
	
	@Transactional
	public void fillDBChannel() {
		Channel channel1 = new Channel();
		channel1.setChannelName("FACEBOOK");
		channelDAO.insertChannel(channel1);
		
		Channel channel2 = new Channel();
		channel2.setChannelName("TWIITER");
		channelDAO.insertChannel(channel2);
		
		Channel channel3 = new Channel();
		channel3.setChannelName("MAILINGLIST");
		channelDAO.insertChannel(channel3);
	}

}
