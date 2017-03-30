package controllers;

import play.db.jpa.JPAApi;
import play.mvc.Security.Authenticated;
import javax.inject.Inject;

import services.data.dao.IUserDAO;
import services.general.*;
import models.Role;
import models.RoleType;
import models.User;
import models.UserVM;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.*;

public class HomeController extends Controller {

	private final FormFactory formFactory;
	private final IUserDAO userDAO;
	private boolean isFail = false;

	@Inject
	public HomeController(FormFactory formFactory, IUserDAO userDAO) {
		this.formFactory = formFactory;
		this.userDAO = userDAO;
	}

	public Result indexAction() {
		Form<UserVM> userVM = formFactory.form(UserVM.class);
		
		return ok(index.render(userVM, isFail));
	}

	@Transactional
	public Result connectRegisteredUserAction() {
		UserVM userVM = formFactory.form(UserVM.class).bindFromRequest().get();

		User user = userDAO.getUserByCredentials(userVM.getEmail(), CryptWithMD5.cryptWithMD5(userVM.getPassword()));

		if (user != null) {
			userVM.setRole(user.getUserRole().getRoleName());
			userVM.setUsername(user.getUsername());
			userVM.setIdUser(user.getIdUser());
			createUserSession(userVM);
			
			return ok(dashboard.render(userVM));
		} else {
			isFail = true;
			return redirect("/");
		}
		
	}

	@Transactional
	public Result connectNewUserAction() {
		UserVM userVM = formFactory.form(UserVM.class).bindFromRequest().get();

		userVM.setRole(RoleType.MEMBRE_FACULTAIRE.toString());
		User user = userVM.createUser();
		Role role = new Role();
		role.setRoleName(RoleType.MEMBRE_FACULTAIRE.toString());
		user.setUserRole(role);
		long idUser = userDAO.addNewUser(user);
		userVM.setIdUser(idUser);
		createUserSession(userVM);

		return ok(dashboard.render(userVM));
	}

	@Authenticated(CustomAuthenticator.class)
	public Result logOutAction() {
		destroyUserSession();

		return redirect("/");
	}

	private void createUserSession(UserVM userVM) {
		session("idUser", Long.toString(userVM.getIdUser()));
		session("username", userVM.getUsername());
		session("email", userVM.getEmail());
		session("role", userVM.getRole());
	}

	private void destroyUserSession() {
		session().remove("idUser");
		session().remove("username");
		session().remove("email");
		session().remove("role");
	}
}
