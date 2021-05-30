package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.CategoryService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
public class CategoryResource {
    private static CategoryService categoryService = new CategoryService();

    @GET
    @Path("/categories/")
    @Produces("application/json")
    public Response getAll() {
        return categoryService.getAll();
    }
}
