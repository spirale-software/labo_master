package controllers;

import java.util.concurrent.CompletionStage;

import play.mvc.Http.Context;
import play.mvc.Http.Session;
import play.mvc.Result;

public class TestAction extends play.mvc.Action.Simple {

	@Override
	public CompletionStage<Result> call(Context ctx) {
		
		ctx.session().put("test", "That's works");
		
		return delegate.call(ctx);
	}

}
