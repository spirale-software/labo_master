package controllers;

import javax.inject.Inject;

import models.Proposal;
import models.ProposalVM;
import models.User;
import models.UserVM;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.data.dao.IProposalDAO;
import services.data.dao.IUserDAO;
import services.data.jpaDao.UserDAO;
import views.html.*;

public class ProposalController extends Controller {

	private final FormFactory formFactory;
	private final IProposalDAO proposalDAO;
	private boolean isAlert = false;

	@Inject
	public ProposalController(FormFactory formFactory, IProposalDAO proposalDAO) {

		this.formFactory = formFactory;
		this.proposalDAO = proposalDAO;
	}

	public Result proposalAction() {
		Form<ProposalVM> proposalVM = formFactory.form(ProposalVM.class);
		String username = session("username");
		
		return ok(proposalForm.render(proposalVM, username, isAlert));
	}

	@Transactional
	public Result addProposalAction() { 
		ProposalVM proposalVM= formFactory.form(ProposalVM.class).bindFromRequest().get();
		
		long idAuthor = Long.parseLong(session("idUser"));
		proposalVM.setProposalAuthorId(idAuthor);
		
		Proposal newProposal = proposalVM.createProposal();
		
		proposalDAO.insertProposal(newProposal, proposalVM.getProposalAuthorId());
		
		isAlert = true;	   
		return redirect("/proposition/form");
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
