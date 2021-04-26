package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.CardService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
public class CardResource {
    private static CardService cardService = new CardService();

    @POST
    @Path("/card")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTodoItem(String json) {
        return cardService.insert(json);
    }
}
