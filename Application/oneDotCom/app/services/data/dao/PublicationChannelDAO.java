package services.data.dao;

import com.google.inject.ImplementedBy;

import models.PublicationChannel;
import services.data.jpaDao.PublicationChannelDaoJPA;

@ImplementedBy(PublicationChannelDaoJPA.class)
public interface PublicationChannelDAO {
	public PublicationChannel insert(PublicationChannel publicationChannel);
}
