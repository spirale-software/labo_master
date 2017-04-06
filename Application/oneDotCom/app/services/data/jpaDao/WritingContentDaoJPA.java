package services.data.jpaDao;

import javax.inject.Inject;

import models.WritingContent;
import play.db.jpa.JPAApi;
import services.data.dao.WritingContentDAO;

public class WritingContentDaoJPA implements WritingContentDAO {

	private final JPAApi jpaApi;
	
	@Inject
	public WritingContentDaoJPA(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}
	
	@Override
	public WritingContent insert(WritingContent writingContent) {
		jpaApi.em().persist(writingContent);
		
		return writingContent;
	}

}