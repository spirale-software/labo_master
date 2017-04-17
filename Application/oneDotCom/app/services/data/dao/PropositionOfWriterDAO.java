package services.data.dao;

import com.google.inject.ImplementedBy;

import models.PropositionOfWriter;
import services.data.jpaDao.PropositionOfWriterDaoJPA;

@ImplementedBy(PropositionOfWriterDaoJPA.class)
public interface PropositionOfWriterDAO {
	public PropositionOfWriter insert(PropositionOfWriter propositionOfWriter);
	public PropositionOfWriter getByIdProposal(long idProposal);
	public void update(PropositionOfWriter propositionOfWriter);
	public void deleteFromIdProposal(Long idProposal);
}
