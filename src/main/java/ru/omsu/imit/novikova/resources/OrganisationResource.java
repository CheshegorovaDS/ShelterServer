package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.OrganisationService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api")
public class OrganisationResource {

    private static OrganisationService organisationService = new OrganisationService();

    @POST
    @Path("/organisation")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTodoItem(String json) {
        return organisationService.insert(json);
    }

    @GET
    @Path("/organisation/{id}")
    @Produces("application/json")
    public Response getById(@PathParam(value = "id") int id) {
        return organisationService.getById(id);
    }

    @GET
    @Path("/organisation/email={email}")
    @Produces("application/json")
    public Response getByEmail(@PathParam(value = "email") String email) {
        return organisationService.getByEmail(email);
    }

    @PUT
    @Path("/organisation/{id}")
    @Produces("application/json")
    public Response editById(@PathParam(value = "id") int id, String json) {
        return organisationService.changeOrganisation(id, json);
    }

    @DELETE
    @Path("/organisation/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam(value = "id") int id, String json) {
        return organisationService.deleteById(id, json);
    }

}
