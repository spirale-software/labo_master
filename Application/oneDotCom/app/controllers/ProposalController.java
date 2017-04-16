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
import services.data.dao.WritingContentDAO;
import services.data.jpaDao.UserDaoJPA;
import services.general.CustomAuthenticator;
import services.proposal.ProposalEditionManager;
import services.proposal.ProposalManager;
import views.html.*;

@Authenticated(CustomAuthenticator.class)
public class ProposalController extends Controller {
	private final FormFactory formFactory;
	private final ProposalDAO proposalDAO;
	private ProposalManager proposalManager;
	private ProposalEditionManager proposalEditionManager;
	private UserDAO userDAO;
	private WritingContentDAO writingContentDAO;

	@Inject
	public ProposalController(FormFactory formFactory, ProposalDAO proposalDAO, ProposalManager proposalBuilder,
			UserDAO userDAO, ProposalEditionManager proposalEditionManager, WritingContentDAO writingContentDAO) {
		this.formFactory = formFactory;
		this.proposalDAO = proposalDAO;
		this.proposalManager = proposalBuilder;
		this.userDAO = userDAO;
		this.proposalEditionManager = proposalEditionManager;
		this.writingContentDAO = writingContentDAO;
	}

	@Transactional
	public Result proposalAction() {
		List<User> users = userDAO.getAllUsers();
		
		if(this.isFormSubmitted()) {
			Proposal proposal = this.addProposal();
			
			if(this.isSaveButtonPressed()) 
				flash("success", "La proposition a bien été enregistrée.");
			
			if(this.isSaveAndRedactButtonPressed())
				return redirect("/proposition/contenu/" + proposal.getIdProposal());
		}
		
		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
		return ok(proposalForm.render(proposalVMForm, users));
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
			
			Proposal proposal = proposalDAO.getProposalById(proposalVM.getIdProposal());
			
			flash("success", "Vos modifications ont bien été enregistrées");
			return ok(proposalDetail.render(proposalVMForm.fill(proposalVM), users, proposal));
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
		ProposalVM proposalVM = this.proposalEditionManager.getProposalVMFromProposal(idProposal);
		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
		Proposal proposal = proposalDAO.getProposalById(idProposal);
		
		if(isAuthorized(idProposal)) {
			// return ok(detailProposalTest.render(proposalVM));
			List<User> users = userDAO.getAllUsers();
			
			return ok(proposalDetail.render(proposalVMForm.fill(proposalVM), users, proposal));
		}
		
		return ok(proposalDetail2.render(proposalVMForm.fill(proposalVM), proposal));
	}

	/**************************** HELPERS METHODS ************************************/
	/*********************************************************************************/


	@Transactional
	private Result getDefaultProposalContent(Long idProposal) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		ProposalVM proposalVM = this.buildProposalVMFromProposal(proposal);
		Form<ProposalContentVM> proposalContentForm = formFactory.form(ProposalContentVM.class);

		return ok(proposalContent.render(proposalContentForm, proposal));
	}
	
	public boolean isAuthorized(Long idProposal) {
		User authorOfProposal = this.proposalDAO.getProposalById(idProposal).getAuthorOfProposal();
				
		return authorOfProposal.getIdUser() == Long.valueOf(session("idUser"));
	}

	private ProposalVM buildProposalVMFromProposal(Proposal proposal) {
		ProposalVM proposalVM = new ProposalVM();

		proposalVM.setProposalName(proposal.getProposalName());
		proposalVM.setProposedWriter("//TODO");
		proposalVM.setIdProposal(proposal.getIdProposal());

		return proposalVM;
	}
	
	@Transactional
	public Proposal addProposal() {
		ProposalVM proposalVM = formFactory.form(ProposalVM.class).bindFromRequest().get();
		proposalVM.setProposalAuthorId(Long.parseLong(session("idUser")));

		// return ok(propositionTest.render(proposalVM));

		proposalManager.setProposalVM(proposalVM);
		Proposal newProposal = proposalManager.getProposal();

		Proposal proposal = proposalDAO.insertProposal(newProposal);

		proposalManager.setUpPropositionOfWriter(proposal);
		proposalManager.setUpPropositionOfChannel(proposal);
		
		return proposal;
	}
	
	public boolean isSaveButtonPressed() {
		return formFactory.form().bindFromRequest().get("action").equals("save");	
	}
	
	public boolean isSaveAndRedactButtonPressed() {
		return formFactory.form().bindFromRequest().get("action").equals("saveAndRedact");
	}
	
	public boolean isFormSubmitted() {
		return request().method().equals("POST");
	}
}
