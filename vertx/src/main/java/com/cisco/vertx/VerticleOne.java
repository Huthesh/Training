package com.cisco.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class VerticleOne extends AbstractVerticle{

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		System.out.println("VerticleOne deployed");
		
		vertx.eventBus().consumer("VerticleTwoReady",message->{
			System.out.println("VeticleTwoReady Received "+message.body());
		});
		
		startFuture.complete();
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		System.out.println("VerticleOne is stopped");
	}

}
