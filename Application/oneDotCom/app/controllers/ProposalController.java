package controllers;

import java.util.List;

import javax.inject.Inject;

import models.Proposal;
import models.ProposalContentVM;
import models.ProposalVM;
import models.User;
import models.UserVM;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import services.data.dao.ProposalDAO;
import services.data.dao.UserDAO;
import services.data.jpaDao.UserDaoJPA;
import services.general.CustomAuthenticator;
import services.proposal.ProposalContentManager;
import services.proposal.ProposalEditionManager;
import services.proposal.ProposalManager;
import views.html.*;

@Authenticated(CustomAuthenticator.class)
public class ProposalController extends Controller {
	private final FormFactory formFactory;
	private final ProposalDAO proposalDAO;
	private ProposalManager proposalManager;
	private ProposalEditionManager proposalEditionManager;
	private ProposalContentManager contentManager;
	private UserDAO userDAO;

	@Inject
	public ProposalController(FormFactory formFactory, ProposalDAO proposalDAO, ProposalManager proposalBuilder,
			ProposalContentManager contentManager, UserDAO userDAO, ProposalEditionManager proposalEditionManager) {
		this.formFactory = formFactory;
		this.proposalDAO = proposalDAO;
		this.proposalManager = proposalBuilder;
		this.contentManager = contentManager;
		this.userDAO = userDAO;
		this.proposalEditionManager = proposalEditionManager;
	}

	@Transactional
	public Result proposalAction() {
		List<User> users = userDAO.getAllUsers();

		return this.getDefaultProposal(false, users);
	}

	@Transactional
	public Result addProposalAction() {
		ProposalVM proposalVM = formFactory.form(ProposalVM.class).bindFromRequest().get();
		proposalVM.setProposalAuthorId(Long.parseLong(session("idUser")));

		// return ok(propositionTest.render(proposalVM));

		proposalManager.setProposalVM(proposalVM);
		Proposal newProposal = proposalManager.getProposal();

		Proposal proposal = proposalDAO.insertProposal(newProposal);

		proposalManager.setUpPropositionOfWriter(proposal);
		proposalManager.setUpPropositionOfChannel(proposal);

		if (formFactory.form().bindFromRequest().get("action").equals("saveAndRedact"))
			return this.writeProposalContentAction(proposal.getIdProposal());

		List<User> users = userDAO.getAllUsers();
		return this.getDefaultProposal(true, users);
	}

	@Transactional
	public Result getAllProposalsAction() {
		List<Proposal> listProposals = proposalDAO.get10NewestProposals();

		return ok(proposals.render(listProposals, session("username")));
	}

	@Transactional
	public Result editProposalAction() {

		if (formFactory.form().bindFromRequest().get("action").equals("edit")) {
			ProposalVM proposalVM = formFactory.form(ProposalVM.class).bindFromRequest().get();
			this.proposalEditionManager.saveModifications(proposalVM);
			List<User> users = userDAO.getAllUsers();
			Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
			return ok(proposalDetail.render(proposalVMForm.fill(proposalVM), users));
			//return ok(detailProposalTest.render(proposalVM));
		} else if (formFactory.form().bindFromRequest().get("action").equals("delete")) {
			return ok("Delete Page");
		} else {
			return ok("Redact content");
		}

		// if(formFactory.form().bindFromRequest().get("action").equals("redact"))

		/*
		 * Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		 * Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
		 * 
		 * ProposalVM proposalVM = buildProposalVMFromProposal(proposal);
		 * 
		 * List<User> users = userDAO.getAllUsers();
		 * 
		 * return ok(proposalEdit.render(proposalVMForm.fill(proposalVM), false,
		 * users));
		 */

		// return ok(proposalForm.render(proposalVMForm.fill(proposalVM),
		// session("username"), false, users));
	}

	public Result deleteProposalAction() {
		return ok();
	}

	@Transactional
	public Result writeProposalContentAction(Long idProposal) {

		return this.getDefaultProposalContent(idProposal, false);
	}

	@Transactional
	public Result detailProposalAction(Long idProposal) {
		ProposalVM proposalVM = this.proposalEditionManager.getProposalVmFromProposal(idProposal);

		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);

		List<User> users = userDAO.getAllUsers();

		// return ok(detailProposalTest.render(proposalVM));

		return ok(proposalDetail.render(proposalVMForm.fill(proposalVM), users));
	}

	@Transactional
	public Result addProposalContentAction(Long idProposal) {
		ProposalContentVM proposalContentVM = formFactory.form(ProposalContentVM.class).bindFromRequest().get();
		proposalContentVM.setIdOfConcernedProposal(idProposal);
		contentManager.manageProposalContent(proposalContentVM, Long.parseLong(session("idUser")));

		return this.getDefaultProposalContent(idProposal, true);
		// return ok(proposalContentTest.render(proposalContentVM));
	}

	/***********************************
	 * HELPERS METHODS
	 ***********************************************************************/
	/***************************************************************************************************************************/

	@Transactional
	private Result getDefaultProposal(boolean isAlert, List<User> users) {

		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
		return ok(proposalForm.render(proposalVMForm, session("username"), isAlert, users));
	}

	@Transactional
	private Result getDefaultProposalContent(Long idProposal, boolean isAlert) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		ProposalVM proposalVM = this.buildProposalVMFromProposal(proposal);
		Form<ProposalContentVM> proposalContentForm = formFactory.form(ProposalContentVM.class);

		return ok(proposalContent.render(proposalContentForm, proposalVM, session("username"), isAlert));
	}

	private ProposalVM buildProposalVMFromProposal(Proposal proposal) {
		ProposalVM proposalVM = new ProposalVM();

		proposalVM.setProposalName(proposal.getProposalName());
		proposalVM.setProposedWriter("jules");
		proposalVM.setIdProposal(proposal.getIdProposal());

		return proposalVM;
	}

}
