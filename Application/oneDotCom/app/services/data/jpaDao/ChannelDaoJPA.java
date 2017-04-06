package services.data.jpaDao;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import models.Channel;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.ChannelDAO;

public class ChannelDaoJPA implements ChannelDAO {
private final JPAApi jpaApi;
	
	@Inject
	public ChannelDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	@Transactional
	public Channel insertChannel(Channel channel) {
		jpaApi.em().persist(channel);
		
		return channel;
	}

	@Override
	@Transactional
	public Channel getChannelByName(String channelName) {
		try {
			Query query = jpaApi.em().createQuery("SELECT channel FROM Channel channel WHERE channel.channelName=:channelName");
			query.setParameter("channelName", channelName);
			
			return (Channel) query.getSingleResult();
			
		} catch (NoResultException e) { } 
		
		return null;	
	}

}
