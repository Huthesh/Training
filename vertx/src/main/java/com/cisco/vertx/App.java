package com.cisco.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class App extends AbstractVerticle{

	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		vertx.deployVerticle("com.cisco.vertx.VerticleOne",result->{
			System.out.println("VerticleOne Deployed successfully");
			vertx.deployVerticle("com.cisco.vertx.VerticleTwo",resultTwo->{
				System.out.println("VerticleTwo Deployed successfully");
			});
		});
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		
	}
}
