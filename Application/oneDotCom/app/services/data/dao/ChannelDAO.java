package services.data.dao;

import com.google.inject.ImplementedBy;

import models.Channel;
import services.data.jpaDao.ChannelDaoJPA;

@ImplementedBy(ChannelDaoJPA.class)
public interface ChannelDAO {
	Channel insertChannel(Channel channel);
	Channel getChannelByName(String name);
}
