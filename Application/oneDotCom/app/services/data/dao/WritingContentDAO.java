package services.data.dao;

import com.google.inject.ImplementedBy;

import models.WritingContent;
import services.data.jpaDao.WritingContentDaoJPA;

@ImplementedBy(WritingContentDaoJPA.class)
public interface WritingContentDAO {
	public WritingContent insert(WritingContent writingContent);
	public WritingContent getByIdProposal(Long idProposal);
}
