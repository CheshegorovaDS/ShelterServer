package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.AnimalTypeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
public class AnimalTypeResource {
    private static AnimalTypeService animalTypeService = new AnimalTypeService();

    @GET
    @Path("/animal_types/")
    @Produces("application/json")
    public Response getAll() {
        return animalTypeService.getAll();
    }
}
