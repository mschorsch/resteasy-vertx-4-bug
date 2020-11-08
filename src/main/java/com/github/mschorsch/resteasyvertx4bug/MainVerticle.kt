package com.github.mschorsch.resteasyvertx4bug

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment

class MainVerticle : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>) {
    val deployment = VertxResteasyDeployment()
    deployment.start()
    deployment.registry.addPerInstanceResource(PingResource::class.java)

    vertx
      .createHttpServer()
      .requestHandler(VertxRequestHandler(vertx, deployment))
      .listen(8888) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8888")
        } else {
          startPromise.fail(http.cause());
        }
      }
  }
}
