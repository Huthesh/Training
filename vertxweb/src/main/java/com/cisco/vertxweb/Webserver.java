package com.cisco.vertxweb;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;


public class Webserver  extends AbstractVerticle{

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		System.out.println("Webserver is started");
		HttpServer httpServer=vertx.createHttpServer();
	
		Router router = Router.router(vertx);
				
		router.route("/user/:id").method(HttpMethod.GET).produces("application/json").handler(this::getUser);
		
		router.route().method(HttpMethod.POST).handler(BodyHandler.create());
		router.route("/user").method(HttpMethod.POST)
							 .consumes("application/json")
							 .produces("applcation/json")
							 .handler(this::createUser);
		
		
		httpServer.requestHandler(router::accept);
		httpServer.listen(9090);
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		System.out.println("Webserver is stopped");
	}
	
	private void getUser(RoutingContext routingContext ){
		HttpServerRequest httpRequest=routingContext.request();
		HttpServerResponse httpServerResponse=routingContext.response();
		httpServerResponse.putHeader("Content-type", "application/json");
		
		JsonObject jsonObject=new JsonObject();
		
		jsonObject.put("ID", httpRequest.getParam("id"));
		jsonObject.put("name","James Bond");
		
		httpServerResponse.end(jsonObject.encode());
	}
	
	private void createUser(RoutingContext routingContext){
		
		String userString=routingContext.getBodyAsString();
		
		HttpServerResponse httpServerResponse=routingContext.response();
		httpServerResponse.putHeader("content-type","applcation/json");
		try {
			User user=new ObjectMapper().readValue(userString, User.class);
			httpServerResponse.end(new ObjectMapper().writeValueAsString(user));
		} catch (IOException e) {
			e.printStackTrace();
			httpServerResponse.setStatusCode(500).end();
			
		}
	}
}
