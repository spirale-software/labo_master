package services.data.jpaDao;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;

import models.Proposal;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.IProposalDAO;

public class ProposalDAO implements IProposalDAO {
	private final JPAApi jpaApi;

	@Inject
	public ProposalDAO(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	@Override
	@Transactional
	public Proposal insertProposal(Proposal proposal) {
		jpaApi.em().persist(proposal);
		
		return proposal;
	}

	@Override
	public List<Proposal> getAllProposals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Proposal getProposalById(Long id) {
		return jpaApi.em().getReference(Proposal.class, id);
	}

	@Override
	public Proposal getProposalByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proposal updateProposal(Proposal proposalUpdated) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Proposal> get10NewestProposals() {
		
		Query query = jpaApi.em().createQuery("SELECT p FROM Proposal p");
		
		return  (List<Proposal>) query.getResultList();
	}
}











