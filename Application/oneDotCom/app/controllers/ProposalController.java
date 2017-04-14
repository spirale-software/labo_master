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
			
			flash("success", "Vos modifications ont bien été enregistrées");
			return ok(proposalDetail.render(proposalVMForm.fill(proposalVM), users));
			//return ok(detailProposalTest.render(proposalVM));
		} else if (formFactory.form().bindFromRequest().get("action").equals("delete")) {
			//TODO
			
			flash("success", "Cette proposition a bien été supprimée");
			return ok("Delete Page");
		} else {
			
			Long idProposal = Long.valueOf(formFactory.form().bindFromRequest().get("idProposal"));
			return this.getDefaultProposalContent(idProposal);
		}
	}

	public Result deleteProposalAction(String idProposal) {
		this.proposalEditionManager.delete(Long.valueOf(idProposal));
		
		flash("success", "La proposition a bien été supprimée.");
		return redirect("/proposition/lister");
	}

	@Transactional
	public Result detailProposalAction(Long idProposal) {

		ProposalVM proposalVM = this.proposalEditionManager.getProposalVmFromProposal(idProposal);

		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);

		List<User> users = userDAO.getAllUsers();

		// return ok(detailProposalTest.render(proposalVM));

		return ok(proposalDetail.render(proposalVMForm.fill(proposalVM), users));
	}
	
	/************************* Proposal Content **********************************************/

	@Transactional
	public Result writeProposalContentAction(Long idProposal) {
		return this.getDefaultProposalContent(idProposal);
	}
	
	@Transactional
	public Result addProposalContentAction(Long idProposal) {
		ProposalContentVM proposalContentVM = formFactory.form(ProposalContentVM.class).bindFromRequest().get();
		proposalContentVM.setIdOfConcernedProposal(idProposal);
		contentManager.manageProposalContent(proposalContentVM, Long.parseLong(session("idUser")));

		flash("success", "Le contenu de la proposition a bien été enregistré");
		return this.getDefaultProposalContent(idProposal);
		// return ok(proposalContentTest.render(proposalContentVM));
	}
	
	@Transactional
	public Result detailProposalContentAction(String idProposal) {
		Form<ProposalContentVM> pcVMForm = formFactory.form(ProposalContentVM.class);
		
		ProposalContentVM pcVM = this.contentManager.getProposalContentVMFromIdProposal(Long.valueOf(idProposal));
		Proposal proposal = proposalDAO.getProposalById(Long.valueOf(idProposal));
		
		return ok(contentDetail.render(pcVMForm.fill(pcVM), proposal));
	}

	/**************************** HELPERS METHODS ************************************/
	/*********************************************************************************/

	@Transactional
	private Result getDefaultProposal(boolean isAlert, List<User> users) {

		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
		return ok(proposalForm.render(proposalVMForm, session("username"), isAlert, users));
	}

	@Transactional
	private Result getDefaultProposalContent(Long idProposal) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		ProposalVM proposalVM = this.buildProposalVMFromProposal(proposal);
		Form<ProposalContentVM> proposalContentForm = formFactory.form(ProposalContentVM.class);

		return ok(proposalContent.render(proposalContentForm, proposalVM));
	}

	private ProposalVM buildProposalVMFromProposal(Proposal proposal) {
		ProposalVM proposalVM = new ProposalVM();

		proposalVM.setProposalName(proposal.getProposalName());
		proposalVM.setProposedWriter("//TODO");
		proposalVM.setIdProposal(proposal.getIdProposal());

		return proposalVM;
	}

}
