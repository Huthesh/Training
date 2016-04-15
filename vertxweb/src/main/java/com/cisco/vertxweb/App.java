package com.cisco.vertxweb;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle{

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		vertx.deployVerticle("com.cisco.vertxweb.Webserver");
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		
	}
   
}
