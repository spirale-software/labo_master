package services.data.jpaDao;

import javax.inject.Inject;

import models.Publication;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.PublicationDAO;

public class PublicationDaoJPA implements PublicationDAO {
	private final JPAApi jpaApi;
	
	@Inject
	public PublicationDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	@Transactional
	public Publication insert(Publication publication) {
		this.jpaApi.em().persist(publication);
		
		return publication;
	}

}
