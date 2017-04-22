package services.data.jpaDao;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import models.ProposalContent;
import models.User;
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

	@Override
	@Transactional
	public ProposalContent getFromId(Long idPropCont) {
		try {
			Query query = jpaApi.em().createQuery("SELECT pc FROM ProposalContent pc WHERE pc.id=:idPropCont");
			query.setParameter("idPropCont", idPropCont);
			
			return (ProposalContent) query.getSingleResult();
		} catch (NoResultException e) { }
		
		return null;
	}

}
