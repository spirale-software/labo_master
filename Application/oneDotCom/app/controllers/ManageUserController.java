package controllers;

import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import services.data.dao.RoleDAO;
import services.data.dao.UserDAO;
import services.general.CustomAuthenticator;
import views.html.*;

import java.util.List;

import javax.inject.Inject;

import models.Role;
import models.User;

@Authenticated(CustomAuthenticator.class)
public class ManageUserController extends Controller {
	private UserDAO userDAO;
	private RoleDAO roleDAO;
	private FormFactory formFactory;

	@Inject
	public ManageUserController(FormFactory formFactory, UserDAO userDAO, RoleDAO roleDAO) {
		this.formFactory = formFactory;
		this.userDAO = userDAO;
		this.roleDAO = roleDAO;
	}
	
	@Transactional
	public Result roleAction() {
		List<User> users = userDAO.getAllUsers();
		List<Role> roles = roleDAO.getAllRoles();
		
		if(isFormSubmitted()) {
			String roleName = formFactory.form().bindFromRequest().get("roleName");
			String userId = formFactory.form().bindFromRequest().get("userId");
			if(roleName.equals("empty") || userId.equals("empty")) {
				
				flash("warning", "Choississez un utilisateur et un role avant de valider votre requête");
				return ok(manageRole.render(users, roles));
			}
			
			this.editRoleOfUser(Long.valueOf(userId), roleName);
			
			flash("success", "L'attribution du rôle a bien été enregistrée");
		}
		
		return ok(manageRole.render(users, roles));
	}
	
	/**************************** HELPERS METHODS ************************************/
	/********************************************************************************/
	
	public boolean isFormSubmitted() {
		return request().method().equals("POST");
	}
	
	@Transactional
	private void editRoleOfUser(Long idUser, String roleName) {
		Role role = roleDAO.getRoleByName(roleName);
		User user = userDAO.getUserById(idUser);
		
		user.setUserRole(role);
		userDAO.update(user);
	}

}
