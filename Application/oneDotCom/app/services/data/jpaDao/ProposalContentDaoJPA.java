package services.data.jpaDao;

import javax.inject.Inject;

import models.ProposalContent;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.ProposalContentDAO;

public class ProposalContentDaoJPA implements ProposalContentDAO {
	private final JPAApi jpaApi;
	
	@Inject
	public ProposalContentDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	@Transactional
	public ProposalContent insertproposalContent(ProposalContent content) {
		jpaApi.em().persist(content);
		
		return content;
	}

}