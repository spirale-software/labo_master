package services.proposal;

import java.util.List;

import javax.inject.Inject;

import models.Proposal;
import models.ProposalVM;
import models.PropositionOfChannel;
import models.PropositionOfWriter;
import services.data.dao.ProposalDAO;
import services.data.dao.PropositionOfChannelDAO;
import services.data.dao.PropositionOfWriterDAO;

public class ProposalEditionManager {
	private PropositionOfChannelDAO propositionOfChannelDAO;
	private PropositionOfWriterDAO propositionOfWriterDAO;
	private ProposalDAO proposalDAO;
	
	@Inject
	public ProposalEditionManager(PropositionOfChannelDAO propositionOfChannelDAO, 
			PropositionOfWriterDAO propositionOfWriterDAO, ProposalDAO proposalDAO) {
		this.propositionOfChannelDAO = propositionOfChannelDAO;
		this.propositionOfWriterDAO = propositionOfWriterDAO;	
		this.proposalDAO = proposalDAO;
	}
	
	public ProposalVM getProposalVmFromProposal(long idProposal) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		PropositionOfWriter propOfWriter = this.propositionOfWriterDAO.getByIdProposal(idProposal);
		List<PropositionOfChannel> propOfChannels = this.propositionOfChannelDAO.getAllByIdProposal(idProposal);
		
		return  getProposalVM(proposal, propOfWriter, propOfChannels);
	}
	
	public void saveModifications(ProposalVM proposalVM) {
		//TODO
	}
	
	
	private ProposalVM getProposalVM(Proposal proposal, PropositionOfWriter propOfWriter, 
			List<PropositionOfChannel> propOfChannels) {
		ProposalVM proposalVM = new ProposalVM();
		
		proposalVM.setIdProposal(proposal.getIdProposal());
		proposalVM.setProposalName(proposal.getProposalName());
		proposalVM.setProposalAuthorName(proposal.getAuthorOfProposal().getUsername());
		proposalVM.setDateCreation(proposal.getCreationDate().toString());
		proposalVM.setProposalState(proposal.getProposalState().toString());
		
		if(propOfWriter != null) {
			proposalVM.setProposedWriter(propOfWriter.getAssignee().getUsername());
			if(propOfWriter.getDeadLine() != null) {
				proposalVM.setDeadLine(propOfWriter.getDeadLine());
			}
		}
		
		if(propOfChannels != null) {
			String proposedChannels = "";
			for(PropositionOfChannel propOfChannel : propOfChannels) {
				proposedChannels += propOfChannel.getProposedChannel().getChannelName() + ", ";
				
			}
			proposalVM.setProposedChannels(proposedChannels);
		}
		
		
		return proposalVM;
	}
}
