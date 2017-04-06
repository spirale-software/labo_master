package services.data.dao;

import com.google.inject.ImplementedBy;

import models.Field;
import services.data.jpaDao.ChannelDaoJPA;
import services.data.jpaDao.FieldDaoJPA;

@ImplementedBy(FieldDaoJPA.class)
public interface FieldDAO {
	public Field insertField(Field field);
}
