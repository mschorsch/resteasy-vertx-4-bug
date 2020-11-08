package com.github.mschorsch.resteasyvertx4bug

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class PingResource {

  @GET
  @Path("")
  @Produces(MediaType.TEXT_PLAIN)
  fun ping() = "ping"
}
