package services.data.jpaDao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import models.Proposal;
import models.User;
import models.ProposalState;
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
	public Proposal insertProposal(Proposal proposal, long idAuthor) {
		
		User user = jpaApi.em().getReference(User.class, idAuthor);
		proposal.setAuthorOfProposal(user);
		jpaApi.em().merge(proposal);
		
		return proposal;
	}

	@Override
	public List<Proposal> getAllProposals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proposal getProposalById(Long id) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public List<Proposal> get10NewestProposals() {
		// TODO Auto-generated method stub
		return null;
	}
}
