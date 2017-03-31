package services.data.dao;

import java.util.List;

import com.google.inject.ImplementedBy;

import models.Proposal;
import services.data.jpaDao.ProposalDAO;

@ImplementedBy(ProposalDAO.class)
public interface IProposalDAO {
	
	public Proposal insertProposal(Proposal newProposal, long idAuthor);
	public List<Proposal> getAllProposals();
	public Proposal getProposalById(Long id);
	public Proposal getProposalByName(String name);
	public Proposal updateProposal(Proposal proposalUpdated);
	public List<Proposal> get10NewestProposals();
}
