package services.data.jpaDao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import models.ChannelType;
import models.Field;
import models.FieldChannel;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.FieldChannelDAO;

public class FieldChannelDaoJPA implements FieldChannelDAO {
	private final JPAApi jpaApi;
	
	@Inject
	public FieldChannelDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	@Transactional
	public FieldChannel insertFieldChannel(FieldChannel fieldChannel) {
		this.jpaApi.em().persist(fieldChannel);
		
		return fieldChannel;
	}

	@Override
	@Transactional
	public List<FieldChannel> getFbFields() {
		Query query = this.jpaApi.em().createQuery("SELECT fc FROM FieldChannel fc WHERE fc.channel.channelName=:fb");
		query.setParameter("fb", ChannelType.FACEBOOK.name());
		
		return query.getResultList();
	}

}
