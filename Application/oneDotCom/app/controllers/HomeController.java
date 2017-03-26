package controllers;

import javax.inject.Inject;

import dao.IUserDAO;
import daoImpl.UserDAO;
import models.MembreFacultaire;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class HomeController extends Controller {

	private FormFactory formFactory;
	private IUserDAO userDAO;

	@Inject
	public HomeController(FormFactory formFactory) {

		this.formFactory = formFactory;
	}

	public Result indexAction() {

		Form<User> user = formFactory.form(User.class);

		return ok(index.render(user));
	}

	public Result connectRegisteredUserAction() {

		User user = formFactory.form(User.class).bindFromRequest().get();

		userDAO = new UserDAO();
		 user = userDAO.getUserByCredentials(user.getEmail(), user.getPassword());

		return ok(dashboard.render(user));
	}

	public Result connectNewUserAction() {

		User newUser = formFactory.form(User.class).bindFromRequest().get();

		userDAO = new UserDAO();
		userDAO.addNewUser(newUser);

		return ok(dashboard.render(newUser));
	}

}
