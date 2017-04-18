package services.proposal;

import java.text.SimpleDateFormat;

import models.Proposal;
import models.PropositionOfWriter;
import models.User;
import services.data.dao.PropositionOfWriterDAO;
import services.general.Notify;
import services.general.SendEmail;

public class AssignedState implements ProposalState {
	private Notify notify;
	private PropositionOfWriterDAO propositionOfWriterDAO;
	
	@Override
	public void doAction(Proposal proposal) {
		notify = new SendEmail();
		String subject = "Assignation de rédaction du contenu d'une proposition";
		String message = "Monsieur " + proposal.getAuthorOfProposal().getUsername() + "\n"
				+ "Vous a assigé la rédaction de: " + proposal.getProposalName() + "\n";
		
		PropositionOfWriter propOfWriter = this.propositionOfWriterDAO.getByIdProposal(proposal.getIdProposal());
		if(propOfWriter.getDeadLine() != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			message += "avec pour date butoir de rédaction le: " + formatter.format(propOfWriter.getDeadLine());	
		}
					
		User userToNotify = propOfWriter.getAssignee();
		
		notify.sendNotification(userToNotify, subject, message);
	}

	public PropositionOfWriterDAO getPropositionOfWriterDAO() {
		return propositionOfWriterDAO;
	}

	public void setPropositionOfWriterDAO(PropositionOfWriterDAO propositionOfWriterDAO) {
		this.propositionOfWriterDAO = propositionOfWriterDAO;
	}
}











