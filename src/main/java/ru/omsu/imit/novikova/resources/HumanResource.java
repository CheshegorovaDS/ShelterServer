package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.HumanService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api")
public class HumanResource {

    private static HumanService humanService = new HumanService();

    @POST
    @Path("/human")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTodoItem(String json) {
        return humanService.insert(json);
    }

    @DELETE
    @Path("/human/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam(value = "id") int id, String json) {
        return humanService.deleteById(id, json);
    }
}
