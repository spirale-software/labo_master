package services.data.jpaDao;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.WritingContent;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.WritingContentDAO;

public class WritingContentDaoJPA implements WritingContentDAO {

	private final JPAApi jpaApi;
	
	@Inject
	public WritingContentDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	@Override
	@Transactional
	public WritingContent insert(WritingContent writingContent) {
		jpaApi.em().persist(writingContent);
		
		return writingContent;
	}

	@Override
	@Transactional
	public WritingContent getByIdProposal(Long idProposal) {
		try {
			Query query = jpaApi.em().createQuery("SELECT wc FROM WritingContent wc "
					+ "WHERE wc.concernedProposal.idProposal=:idProposal");
			query.setParameter("idProposal", idProposal);
			
			return (WritingContent) query.getSingleResult();
		} catch (NoResultException e) { }
		
		return null;
	}

}
