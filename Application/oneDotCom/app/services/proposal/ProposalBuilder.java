package services.proposal;

import java.util.Date;

import javax.inject.Inject;

import models.Proposal;
import models.ProposalVM;
import models.User;
import play.db.jpa.Transactional;
import services.data.dao.UserDAO;

public class ProposalBuilder {
	private ProposalVM proposalVM;
	private User authorOfProposal;
	private UserDAO userDAO;
	private ProposalState proposalState;
	@Inject
	public ProposalBuilder(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setProposalVM(ProposalVM proposalVM) {
		this.proposalVM = proposalVM;
	}

	public Proposal getProposal() {
		Proposal proposal = new Proposal();
		
		this.setAuthorOfProposal();
		proposal.setAuthorOfProposal(authorOfProposal);
		proposal.setCreationDate(new Date());
		proposal.setProposalName(this.proposalVM.getProposalName());
		proposal.setProposalState(this.getStateOfProposal(proposal));
		
		return proposal;	
	}
	
	private models.ProposalState getStateOfProposal(Proposal proposal) {
		if(this.proposalVM.getProposedWriter().isEmpty())
			return models.ProposalState.Created;
		else
		{
			proposalState = new AssignedState();
			proposalState.doAction(proposal);
			
			return models.ProposalState.Assigned;
		}
	}
	
	@Transactional
	private void setAuthorOfProposal() {
		this.authorOfProposal = userDAO.getUserById(proposalVM.getProposalAuthorId());
	}
}







