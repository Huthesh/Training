package com.cisco.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class VerticleTwo extends AbstractVerticle{

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		System.out.println("VerticleTwo deployed");
		
		vertx.eventBus().publish("VerticleTwoReady", "I am ready");
		
		vertx.eventBus().consumer("Channel1",message->{
			System.out.println("Received a message:"+message.body());
		});
		
		startFuture.complete();
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		System.out.println("VerticleTwo is stopped");
	}

}
