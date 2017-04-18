package services.proposal;

import javax.inject.Inject;

import models.ProposalStateType;

public class ProposalStateFactory {
	private ProposalState assignedState;
	
	@Inject
	public ProposalStateFactory(ProposalState assignedState) {
		this.assignedState = assignedState;
	}
	
	public static ProposalState getProposalState(ProposalStateType proposalStateType) {
		ProposalState proposalState = null;
		
		switch(proposalStateType) {
			case Created : return new CreatedState();
			case Assigned : return new AssignedState();
			case In_Writing : return new InWritingState();
			case Writed : return new WritedState();
			case Published: return new PublishState();
		}
		return proposalState;
	}
}
