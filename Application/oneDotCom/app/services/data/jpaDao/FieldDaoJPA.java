package services.data.jpaDao;

import javax.inject.Inject;

import models.Field;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import services.data.dao.FieldDAO;

public class FieldDaoJPA implements FieldDAO {
	private final JPAApi jpaApi;
	
	@Inject
	public FieldDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	@Transactional
	public Field insertField(Field field) {
		jpaApi.em().persist(field);
		
		return field;
	}

}
