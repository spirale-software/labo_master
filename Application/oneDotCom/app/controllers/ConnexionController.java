package controllers;



import javax.inject.Inject;

import models.MembreFacultaire;
import play.data.FormFactory;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import play.db.jpa.Transactional;
import play.db.jpa.JPAApi;

public class ConnexionController extends Controller {
	
	private FormFactory formFactory;
	private JPAApi jpaApi;
	
	@Inject
	public ConnexionController(FormFactory formFactory, JPAApi jpaApi) {
		
		this.formFactory = formFactory;
		this.jpaApi = jpaApi;
	}
	
	public Result indexAction() {
		
		Form<MembreFacultaire> formMembre = formFactory.form(MembreFacultaire.class);
		
		return ok(index.render(formMembre));		
	}
	
	@Transactional
	public Result connexionAction() {
		
		MembreFacultaire membreFacultaire = formFactory.form(MembreFacultaire.class).bindFromRequest().get();
		
		jpaApi.em().persist(membreFacultaire);
		
		return ok();		
	}
	
	
	
	public Result verifierAction() {
		
		return ok();
	}

}
