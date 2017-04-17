package services.data.dao;

import java.util.List;

import com.google.inject.ImplementedBy;

import models.Proposal;
import services.data.jpaDao.ProposalDaoJPA;

@ImplementedBy(ProposalDaoJPA.class)
public interface ProposalDAO {
	
	public Proposal insertProposal(Proposal newProposal);
	public List<Proposal> getAllProposals();
	public Proposal getProposalById(Long id);
	public Proposal getProposalByName(String name);
	public Proposal updateProposal(Proposal proposalUpdated);
	public List<Proposal> get10NewestProposals();
	public void delete(Long idProposal);
}
