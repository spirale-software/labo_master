package services.data.dao;

import com.google.inject.ImplementedBy;

import models.PropositionOfChannel;
import services.data.jpaDao.FieldDaoJPA;
import services.data.jpaDao.PropositionOfChannelDaoJPA;

@ImplementedBy(PropositionOfChannelDaoJPA.class)
public interface PropositionOfChannelDAO {
	public PropositionOfChannel insert(PropositionOfChannel propositionOfChannel);
}
