package services.general;

import javax.inject.Inject;

import models.Channel;
import models.ChannelType;
import models.Role;
import models.RoleType;
import models.User;
import play.db.jpa.Transactional;
import services.data.dao.ChannelDAO;
import services.data.dao.RoleDAO;
import services.data.dao.UserDAO;

public class BuildBdDefaultValue {
	
	private ChannelDAO channelDAO;
	private RoleDAO roleDAO;
	private UserDAO userDAO;
	
	@Inject
	public BuildBdDefaultValue(ChannelDAO channelDAO, RoleDAO roleDAO, UserDAO userDAO) {
		this.channelDAO = channelDAO;
		this.roleDAO = roleDAO;
		this.userDAO = userDAO;
	}
	
	@Transactional
	public void createDbDefaultValue() {
		fillDBRole();
		fillDBChannel();
		fillDBUser();
	}
	
	@Transactional
	public void fillDBRole() {
		Role role1 = new Role();
		role1.setRoleName(RoleType.MEMBRE_FACULTAIRE.toString());
		roleDAO.insert(role1);
		
		Role role2 = new Role();
		role2.setRoleName(RoleType.RESP_CANAL.toString());
		roleDAO.insert(role2);
		
		Role role3 = new Role();
		role3.setRoleName(RoleType.RESP_COMMUNICATION.toString());
		roleDAO.insert(role3);
		
		Role role4 = new Role();
		role4.setRoleName(RoleType.PRESIDENT.toString());
		roleDAO.insert(role4);
	
	}
	
	@Transactional
	public void fillDBChannel() {
		Channel channel1 = new Channel();
		channel1.setChannelName(ChannelType.FACEBOOK.toString());
		channelDAO.insertChannel(channel1);
		
		Channel channel2 = new Channel();
		channel2.setChannelName(ChannelType.TWITTER.toString());
		channelDAO.insertChannel(channel2);
		
		Channel channel3 = new Channel();
		channel3.setChannelName(ChannelType.MAILING_LIST.toString());
		channelDAO.insertChannel(channel3);
	}
	
	@Transactional
	public void fillDBUser() {
		User user1 = new User();
		user1.setEmail("cindy@outlook.fr");
		user1.setUsername("Madix");
		Role role1 = roleDAO.getRoleByName(RoleType.RESP_COMMUNICATION.toString());
		user1.setUserRole(role1);
		user1.setPassword(CryptWithMD5.cryptWithMD5("123"));
		userDAO.insertUser(user1);
		
		User user2 = new User();
		user2.setEmail("gyleentrepreneur@gmail.com");
		user2.setUsername("Gyle");
		Role role2 = roleDAO.getRoleByName(RoleType.MEMBRE_FACULTAIRE.toString());
		user2.setUserRole(role2);
		user2.setPassword(CryptWithMD5.cryptWithMD5("123"));
		userDAO.insertUser(user2);
		
		User user3 = new User();
		user3.setEmail("lapigerard@yahoo.fr");
		user3.setUsername("Lapi");
		Role role3 = roleDAO.getRoleByName(RoleType.PRESIDENT.toString());
		user3.setUserRole(role3);
		user3.setPassword(CryptWithMD5.cryptWithMD5("123"));
		userDAO.insertUser(user3);
		
		User user4 = new User();
		user4.setEmail("symbol@yahoo.fr");
		user4.setUsername("Symbol");
		Role role4 = roleDAO.getRoleByName(RoleType.RESP_CANAL.toString());
		user4.setUserRole(role4);
		user4.setPassword(CryptWithMD5.cryptWithMD5("123"));
		userDAO.insertUser(user4);
		
	}
	
	

}
