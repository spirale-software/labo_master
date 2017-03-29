package controllers;

import javax.inject.Inject;

import models.Proposal;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.data.dao.IPropositionDAO;
import services.data.dao.IUserDAO;
import services.data.jpaDao.UserDAO;
import viewModel.ProposalVM;
import views.html.*;

public class ProposalController extends Controller {

	private FormFactory formFactory;
	private IPropositionDAO propositionDAO;

	@Inject
	public ProposalController(FormFactory formFactory) {

		this.formFactory = formFactory;
	}

	public Result proposalAction() {

		Form<ProposalVM> proposalVM = formFactory.form(ProposalVM.class);
		String lastName = session("username");
		
		boolean isAlert = false;
		
		return ok(proposalForm.render(proposalVM, lastName, isAlert));
	}

	public Result addProposalAction() {
		
		
		DynamicForm requestData = formFactory.form().bindFromRequest();
	    String assignedWriter = requestData.get("assignedWriter");
	    String propositionName = requestData.get("propositionName");
	    String propososedCanal = requestData.get("propososedCanal");
	    
	    String lastName = session("prenom");
	    
	    Form<Proposal> newPropositionForm = formFactory.form(Proposal.class);
	    
	    boolean isAlert = true;
		return ok(proposalForm.render(newPropositionForm, lastName, isAlert));
	}

	public Result getAllProposalsAction() {

		String lastName = session("prenom");
		
		return ok(propositions.render(lastName));
	}

	public Result detailProposalAction() {

		return ok();
	}

	public Result editProposalAction() {

		return ok();
	}

	public Result deleteProposalAction() {

		return ok();
	}

}
