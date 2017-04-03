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
import services.data.dao.IProposalDAO;
import services.data.dao.IUserDAO;
import services.data.jpaDao.UserDAO;
import services.general.CustomAuthenticator;
import services.proposal.ProposalBuilder;
import views.html.*;

@Authenticated(CustomAuthenticator.class)
public class ProposalController extends Controller {

	private final FormFactory formFactory;
	private final IProposalDAO proposalDAO;
	private ProposalBuilder proposalBuilder;

	
	@Inject
	public ProposalController(FormFactory formFactory, IProposalDAO proposalDAO, ProposalBuilder proposalBuilder) {
		this.formFactory = formFactory;
		this.proposalDAO = proposalDAO;
		this.proposalBuilder = proposalBuilder;
	}

	public Result proposalAction() {
		return this.getDefaultProposal(false);
	}

	@Transactional
	public Result addProposalAction() { 
		ProposalVM proposalVM= formFactory.form(ProposalVM.class).bindFromRequest().get();
		proposalVM.setProposalAuthorId(Long.parseLong(session("idUser")));
		
		proposalBuilder.setProposalVM(proposalVM);
		Proposal newProposal = proposalBuilder.getProposal();
		
		proposalDAO.insertProposal(newProposal);
	   
		return this.getDefaultProposal(true);
	}

	@Transactional
	public Result getAllProposalsAction() {
		List<Proposal> listProposals = proposalDAO.get10NewestProposals();
		
		return ok(proposals.render(listProposals, session("username")));
	}

	public Result detailProposalAction() {
		return ok();
	}

	@Transactional
	public Result editProposalAction(Long idProposal) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
		
		ProposalVM proposalVM = buildProposalVMFromProposal(proposal);
		
		return ok(proposalForm.render(proposalVMForm.fill(proposalVM), session("username"), false));
	}

	public Result deleteProposalAction() {
		return ok();
	}
	
	@Transactional
	public Result writeProposalContentAction(Long idProposal) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		
		ProposalVM proposalVM = this.buildProposalVMFromProposal(proposal);
		
		Form<ProposalContentVM> proposalContentForm = formFactory.form(ProposalContentVM.class);
		return ok(proposalContent.render(proposalContentForm, proposalVM, session("username")));
	}
	
	@Transactional
	public Result detailProposalAction(Long idProposal) {
		Proposal proposal = this.proposalDAO.getProposalById(idProposal);
		
		return ok(proposalDetail.render(proposal, session("username")));
	}
	
	public Result writerSuggestionAction(String text) {
		
		return ok("Yesssssss!!!");
	}
	
	private Result getDefaultProposal(boolean isAlert) {
		Form<ProposalVM> proposalVMForm = formFactory.form(ProposalVM.class);
		return ok(proposalForm.render(proposalVMForm, session("username"), isAlert));	
	}
	
	private ProposalVM buildProposalVMFromProposal(Proposal proposal) {
		ProposalVM proposalVM = new ProposalVM();
		
		proposalVM.setProposalName(proposal.getProposalName());
		proposalVM.setProposedWriter("jules");
		proposalVM.setIdProposal(proposal.getIdProposal());
		
		return proposalVM;
	}
	 

}
