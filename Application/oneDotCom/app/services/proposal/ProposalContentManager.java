package services.proposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import models.Channel;
import models.ChannelType;
import models.ContentField;
import models.DateField;
import models.Field;
import models.FieldChannel;
import models.ImageField;
import models.LinkField;
import models.MediaField;
import models.PlaceField;
import models.Proposal;
import models.ProposalContent;
import models.ProposalContentVM;
import models.ProposalStateType;
import models.TitleField;
import models.User;
import models.WritingContent;
import services.data.dao.ChannelDAO;
import services.data.dao.FieldChannelDAO;
import services.data.dao.FieldDAO;
import services.data.dao.ProposalContentDAO;
import services.data.dao.ProposalDAO;
import services.data.dao.WritingContentDAO;
import services.data.jpaDao.UserDaoJPA;

public class ProposalContentManager {
	private WritingContentDAO writingContentDAO;
	private ProposalDAO proposalDAO;
	private UserDaoJPA userDAO;
	private FieldDAO fieldDAO;
	private ChannelDAO channelDAO;
	private FieldChannelDAO fieldChannelDAO;
	private ProposalContentDAO proposalContentDAO;
	
	@Inject
	public ProposalContentManager(WritingContentDAO writingContentDAO, ProposalDAO proposalDAO, UserDaoJPA userDAO, FieldDAO fieldDAO,
			ChannelDAO channelDAO, FieldChannelDAO fieldChannelDAO, ProposalContentDAO proposalContentDAO) {
		this.writingContentDAO = writingContentDAO;
		this.proposalDAO = proposalDAO;
		this.userDAO = userDAO;
		this.fieldDAO = fieldDAO;
		this.channelDAO = channelDAO;
		this.fieldChannelDAO = fieldChannelDAO;	
		this.proposalContentDAO = proposalContentDAO;
	}
	
	public void manageProposalContent(ProposalContentVM proposalContentVM, Long idOfContentWriter) {
		User writer = userDAO.getUserById(idOfContentWriter);
		
		Proposal concernedProposal = proposalDAO.getProposalById(proposalContentVM.getIdOfConcernedProposal());
		
		ProposalContent proposalContent = new ProposalContent();
		proposalContent.setWritingDate(new Date());
		proposalContent = proposalContentDAO.insertproposalContent(proposalContent);
		
		this.insertFiels(proposalContent, proposalContentVM);
		
		WritingContent writingContent = new WritingContent();
		writingContent.setConcernedProposal(concernedProposal);
		writingContent.setContent(proposalContent);
		writingContent.setWriter(writer);
		
		writingContentDAO.insert(writingContent);
		
		concernedProposal.setProposalState(ProposalStateType.Writed);
		proposalDAO.updateProposal(concernedProposal);
	}
	
	public ProposalContentVM getProposalContentVMFromIdProposal(Long idProposal) {
		ProposalContentVM pcVM = new ProposalContentVM();
		
		WritingContent writingContent = writingContentDAO.getByIdProposal(idProposal);
		List<Field> fields = writingContent.getContent().getListOfFields();
		
		for(Field field : fields) {
			if(field instanceof TitleField)
				pcVM.setTitle(field.getFieldContent());
			if(field instanceof ContentField)
				pcVM.setContent(field.getFieldContent());
			if(field instanceof LinkField)
				pcVM.setLink(field.getFieldContent());
			if(field instanceof PlaceField)
				pcVM.setPlace(field.getFieldContent());
		}
		
		//TODO Other field have to be doing
		
		return pcVM;
	}
	
	/********************************* HELPERS METHODS ****************************************************/
	/******************************************************************************************************/
	
	private void insertFiels(ProposalContent proposalContent, ProposalContentVM proposalContentVM) {
		insertTitleField(proposalContent, proposalContentVM.getTitle());		
		insertContentField(proposalContent, proposalContentVM.getContent());
		insertLinkField(proposalContent, proposalContentVM.getLink());
		insertPlaceField(proposalContent, proposalContentVM.getPlace());
		
		//TODO 
		//insertDateField(proposalContent, proposalContentVM.getDate());
		//insertImageField(proposalContent, proposalContentVM.getImage());		
		//insertMediaField(proposalContent, proposalContentVM.getMedia());
		
	}
	
	private void insertTitleField(ProposalContent proposalContent, String title) {
		Field field = this.insertField(proposalContent, "title", title);
		
		ArrayList<String> channelsNames = new ArrayList<>();
		channelsNames.add(ChannelType.FACEBOOK.toString());
		channelsNames.add(ChannelType.MAILING_LIST.toString());
		
		this.insertFieldsChannels(field, channelsNames);
	}
	
	private void insertContentField(ProposalContent proposalContent, String content) {
		Field field = this.insertField(proposalContent, "content", content);
		
		ArrayList<String> channelsNames = new ArrayList<>();
		channelsNames.add(ChannelType.FACEBOOK.toString());
		channelsNames.add(ChannelType.MAILING_LIST.toString());
		
		this.insertFieldsChannels(field, channelsNames);
	}
	
	private void insertPlaceField(ProposalContent proposalContent, String place) {
		Field field = this.insertField(proposalContent, "place", place);
		
		ArrayList<String> channelsNames = new ArrayList<>();
		channelsNames.add(ChannelType.FACEBOOK.toString());
		channelsNames.add(ChannelType.MAILING_LIST.toString());
		channelsNames.add(ChannelType.TWITTER.toString());
		
		this.insertFieldsChannels(field, channelsNames);
	}
	
	private void insertLinkField(ProposalContent proposalContent, String link) {
		Field field = this.insertField(proposalContent, "link", link);
		
		ArrayList<String> channelsNames = new ArrayList<>();
		channelsNames.add(ChannelType.FACEBOOK.toString());
		channelsNames.add(ChannelType.MAILING_LIST.toString());
		channelsNames.add(ChannelType.TWITTER.toString());
		
		this.insertFieldsChannels(field, channelsNames);
	}
	
	
	private void insertFieldsChannels(Field field, ArrayList<String> channels) {
		Channel channel;
		
		Iterator<String> i = channels.iterator();
		while(i.hasNext()) {
			channel = channelDAO.getChannelByName((String) i.next());
			this.insertFieldChannel(channel, field);
		}
	}
	
	private Field insertField(ProposalContent proposalContent, String fieldName, Object obj) {
		Field field = FieldFactory.get(fieldName);
		
		if(obj instanceof String) {
			field.setFieldContent((String) obj);
		}
		
		field.setContainer(proposalContent);
		return fieldDAO.insertField(field);
	}
	
	
	/*private void insertTitleField(ProposalContent proposalContent, String title) {
		Field titleField = new TitleField();
		titleField.setContainer(proposalContent);
		titleField.setFieldContent(title);
		fieldDAO.insertField(titleField);
		
		Channel channel;
		channel = channelDAO.getChannelByName(ChannelType.FACEBOOK.toString());
		insertFieldChannel(channel, titleField);
		
		channel = channelDAO.getChannelByName(ChannelType.TWITTER.toString());
		insertFieldChannel(channel, titleField);
		
		channel = channelDAO.getChannelByName(ChannelType.MAILING_LIST.toString());
		insertFieldChannel(channel, titleField);
	}*/
	
	private void insertFieldChannel(Channel channel, Field field) {
		FieldChannel fieldChannel = new FieldChannel();
		fieldChannel.setChannel(channel);
		fieldChannel.setField(field);
		fieldChannelDAO.insertFieldChannel(fieldChannel);
	}
	
	/********************************** Inner Class ********************************************/
	/*******************************************************************************************/
	static class FieldFactory {
		
		static Field get(String fieldName) {
			
			switch(fieldName) {
				case "title": return new TitleField();
				case "image": return new ImageField();
				case "date": return new DateField();
				case "link": return new LinkField();
				case "media": return new MediaField();
				case "content": return new ContentField();
				case "place": return new PlaceField();
			}
			return null;
		}
	}
	

}
