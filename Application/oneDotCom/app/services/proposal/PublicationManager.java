package services.proposal;

import services.data.dao.ChannelDAO;
import services.data.dao.FieldChannelDAO;
import services.data.dao.ProposalContentDAO;
import services.data.dao.ProposalDAO;
import services.data.dao.PublicationChannelDAO;
import services.data.dao.PublicationDAO;
import services.data.dao.UserDAO;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import models.Channel;
import models.ChannelType;
import models.ContentField;
import models.DateField;
import models.Field;
import models.FieldChannel;
import models.LinkField;
import models.PlaceField;
import models.Proposal;
import models.ProposalContent;
import models.ProposalStateType;
import models.Publication;
import models.PublicationChannel;
import models.TitleField;
import models.User;
import play.db.jpa.Transactional;

public class PublicationManager {
	private ProposalDAO proposalDAO;
	private PublicationDAO publicationDAO;
	private PublicationChannelDAO publicationChannelDAO;
	private UserDAO userDAO;
	private ProposalContentDAO proposalContentDAO;
	private FieldChannelDAO fieldChannelDAO;
	private ChannelDAO channelDAO;
	
	@Inject
	public PublicationManager(ProposalDAO proposalDAO, PublicationDAO publicationDAO, PublicationChannelDAO publicationChannelDAO, 
			UserDAO userDAO, ProposalContentDAO proposalContentDAO, FieldChannelDAO fieldChannelDAO, ChannelDAO channelDAO) {
		this.proposalDAO = proposalDAO;	
		this.publicationDAO = publicationDAO;
		this.publicationChannelDAO = publicationChannelDAO;
		this.userDAO = userDAO;
		this.proposalContentDAO = proposalContentDAO;
		this.fieldChannelDAO = fieldChannelDAO;
		this.channelDAO = channelDAO;
	}
	
	public String manageFbPublication(Long idProposal, Long idPropCont, Long idPublisher) {		
		//this.savePublication(idProposal, idPropCont, idPublisher, ChannelType.FACEBOOK.name());
		return this.getMessageToPublishOnFb(idPropCont);
	}
	
	/*public String manageTwitterPublication(Long idProposal, Long idPropCont, Long idPublisher) {		
		this.savePublication(idProposal, idPropCont, idPublisher, ChannelType.TWITTER.name());
		return this.getMessageToPublishOnTwitter();
	}*/

	/*private String getMessageToPublishOnTwitter() {
		String message = "";
		List<FieldChannel> fieldsToPublishOnFb = this.fieldChannelDAO.getTwitterFields();
		for(FieldChannel fc : fieldsToPublishOnFb) {
			if(fc.getField() instanceof TitleField) 
				message += fc.getField().getFieldContent();
			if(fc.getField() instanceof LinkField)
				message += fc.getField().getFieldContent();
			if(fc.getField() instanceof PlaceField)
				message += fc.getField().getFieldContent();
			if(fc.getField() instanceof DateField) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				message += formatter.format(((DateField) fc.getField()).getDate());	
			}
		}

		return message;	
	}*/
	
	@Transactional
	private String getMessageToPublishOnFb(Long idPropCont) {
		String message = "ffffff";
		
		//List<FieldChannel> fieldsToPublishOnFb = this.fieldChannelDAO.getFbFields();
		
		ProposalContent propCont = this.proposalContentDAO.getFromId(idPropCont);
		
		List<Field> fieldsToPublishOnFb = propCont.getListOfFields();
		
		for(Field field : fieldsToPublishOnFb) {
			if(field instanceof TitleField) 
				message += field.getFieldContent() + "\n";
			if(field instanceof ContentField)
				message += field.getFieldContent() + "\n";
			if(field instanceof LinkField)
				message += field.getFieldContent() + "\n";
			if(field instanceof PlaceField)
				message += field.getFieldContent() + "\n";
			if(field instanceof DateField) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				message += formatter.format(((DateField) field).getDate());	
			}
		}
		
		return message;	
	}
	
	private void savePublication(Long idProposal, Long idPropCont, Long idPublisher, String channelName) {
		Publication publication = new Publication();
		ProposalContent propCont = this.proposalContentDAO.getFromId(idPropCont);
		User publisher = this.userDAO.getUserById(idPublisher);
		
		publication.setContent(propCont);
		publication.setPublisher(publisher);
		publication = this.publicationDAO.insert(publication);
		
		this.savePublicationChannel(publication, channelName);	
		
		this.setStateOfProposal(idProposal, channelName);
	}
	
	private void savePublicationChannel(Publication publication, String channelName) {
		PublicationChannel pc = new PublicationChannel();
		Channel channel = this.channelDAO.getChannelByName(channelName);
		
		pc.setPublication(publication);
		pc.setChannel(channel);
		
		this.publicationChannelDAO.insert(pc);	
	}
	
	private void setStateOfProposal(Long idProposal, String channelName) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		
		if(proposal.getProposalState().name().contains("Publised")) {
			proposal.setProposalState(ProposalStateType.Published);
		} else {
			if(channelName == ChannelType.FACEBOOK.name())
				proposal.setProposalState(ProposalStateType.Published_On_Facebook);
			if(channelName == ChannelType.TWITTER.name())
				proposal.setProposalState(ProposalStateType.Published_On_Twitter);				
		}
					
		this.proposalDAO.updateProposal(proposal);
	}
}
