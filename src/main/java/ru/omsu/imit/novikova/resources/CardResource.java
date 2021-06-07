package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.CardService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    @Path("/card/{idAnimal}")
    @Produces("application/json")
    public Response getByAnimalId(@PathParam(value = "idAnimal") int id) {
        return cardService.getByAnimalId(id);
    }

    @GET
    @Path("/cards/")
    @Produces("application/json")
    public Response getAll() {
        return cardService.getAll();
    }

    @GET
    @Path("/cards/user={idUser}")
    @Produces("application/json")
    public Response getAllByUser(@PathParam(value = "idUser") int id) {
        return cardService.getAllByUser(id);
    }

    @GET
    @Path("/cards/animalType={idAnimalType}")
    @Produces("application/json")
    public Response getAllByAnimalType(@PathParam(value = "idAnimalType") int id) {
        return cardService.getAllByAnimalType(id);
    }

    @GET
    @Path("/cards/category={idCategory}")
    @Produces("application/json")
    public Response getAllByCategory(@PathParam(value = "idCategory") int id) {
        return cardService.getAllByCategory(id);
    }

    @GET
    @Path("/cards/request={request}")
    @Produces("application/json")
    public Response getAllByString(@PathParam(value = "request") String request) {
        return cardService.getAllByString(request);
    }

    @GET
    @Path("/cards/filters")
    @Produces("application/json")
    public Response getAllByFilters(
            @QueryParam(value = "category") List<Integer> listCategoriesId,
            @QueryParam(value = "animal_type") List<Integer> listAnimalTypesId
    ) {
        return cardService.getAllByFilters(listCategoriesId, listAnimalTypesId);
    }

    @PUT
    @Path("/card/{id}")
    @Produces("application/json")
    public Response editById(@PathParam(value = "id") int id, String json) {
        return cardService.changeCard(id, json);
    }

    @DELETE
    @Path("/card/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam(value = "id") int id, String json) {
        return cardService.deleteById(id, json);
    }
}
