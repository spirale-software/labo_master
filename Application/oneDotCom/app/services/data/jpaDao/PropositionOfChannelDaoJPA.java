 package services.data.jpaDao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import models.PropositionOfChannel;
import models.User;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.PropositionOfChannelDAO;

public class PropositionOfChannelDaoJPA implements PropositionOfChannelDAO {
	private final JPAApi jpaApi;
	
	@Inject
	public PropositionOfChannelDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	@Override
	@Transactional
	public PropositionOfChannel insert(PropositionOfChannel propositionOfChannel) {
		jpaApi.em().persist(propositionOfChannel);
		
		return propositionOfChannel;
	}

	@Override
	public List<PropositionOfChannel> getAllByIdProposal(long idProposal) {
		Query query = jpaApi.em().createQuery("SELECT pc FROM PropositionOfChannel pc "
				+ "WHERE pc.concernedProposal.idProposal= :id");
		query.setParameter("id", idProposal);
		
		return  (List<PropositionOfChannel>) query.getResultList();
	}
}
