package services.general;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class CustomAuthenticator extends Security.Authenticator {

	@Override
    public Result onUnauthorized(Http.Context context) {
		
		return redirect("/");
    }
}
