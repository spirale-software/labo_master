package controllers;

import play.db.jpa.JPAApi;
import play.mvc.Security.Authenticated;
import javax.inject.Inject;

import services.general.*;
import models.RoleType;
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

	@Inject
	public HomeController(FormFactory formFactory) {
		this.formFactory = formFactory;
	}

	public Result indexAction() {
		Form<UserVM> userVM = formFactory.form(UserVM.class);
			
		return ok(index.render(userVM));
	}

	public Result connectRegisteredUserAction() {
		/*User user = formFactory.form(User.class).bindFromRequest().get();
		
		createUserSession(user);
		
		user = userDAO.getUserByCredentials(user.getEmail(), user.getPassword());

		return ok(dashboard.render(user));*/
		
		return ok();
	}

	@Transactional
	public Result connectNewUserAction() {
		UserVM userVM = formFactory.form(UserVM.class).bindFromRequest().get();

		createUserSession(userVM);
		
		userVM.setRole(RoleType.MEMBRE_FACULTAIRE);

		return ok(dashboard.render(userVM));
	}
	
	@Authenticated(CustomAuthenticator.class)
	public Result logOutAction() {
		destroyUserSession();
		
		return redirect("/");
	}
	
	private void createUserSession(UserVM userVM) {
		session("username", userVM.getUsername());
		session("email", userVM.getEmail());
		session("role", userVM.getRole().toString());		
	}
	
	private void destroyUserSession() {
		session().remove("username");
		session().remove("email");
		session().remove("role");
	}
}












