package controllers;

import javax.inject.Inject;
import dao.IPropositionDAO;
import dao.IUserDAO;
import daoImpl.UserDAO;
import models.Proposition;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class PropositionController extends Controller {

	private FormFactory formFactory;
	private IPropositionDAO propositionDAO;

	@Inject
	public PropositionController(FormFactory formFactory) {

		this.formFactory = formFactory;
	}

	public Result propositionAction() {

		Form<Proposition> newPropositionForm = formFactory.form(Proposition.class);

		return ok(propositionForm.render(newPropositionForm));
	}

	public Result addPropositionAction() {

		return ok();
	}

	public Result getAllPropositionsAction() {

		return ok(propositions.render());
	}

	public Result detailPropositionAction() {

		return ok();
	}

	public Result editPropositionAction() {

		return ok();
	}

	public Result deletePropositionAction() {

		return ok();
	}

}