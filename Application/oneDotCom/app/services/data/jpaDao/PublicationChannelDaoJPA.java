package services.data.jpaDao;

import javax.inject.Inject;

import models.PublicationChannel;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.PublicationChannelDAO;

public class PublicationChannelDaoJPA implements PublicationChannelDAO {
	private final JPAApi jpaApi;

	@Inject
	public PublicationChannelDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	@Override
	@Transactional 
	public PublicationChannel insert(PublicationChannel publicationChannel) {
		this.jpaApi.em().persist(publicationChannel);
		
		return publicationChannel;
	}

}
