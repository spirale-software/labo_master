package services.data.jpaDao;

import javax.inject.Inject;

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

}
