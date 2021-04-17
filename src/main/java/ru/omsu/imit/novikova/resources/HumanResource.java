package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.HumanService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
public class HumanResource {

    private static HumanService humanService = new HumanService();

    @POST
    @Path("/voter")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTodoItem(String json) {
        return humanService.insert(json);
    }

}
