package services.proposal;

import models.Proposal;

public interface ProposalState {

	public void doAction(Proposal proposition);
}
