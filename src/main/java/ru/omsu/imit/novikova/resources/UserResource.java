package ru.omsu.imit.novikova.resources;

import ru.omsu.imit.novikova.service.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
public class UserResource {

    private static UserService service  = new UserService();

    @GET
    @Path("/login/login={login}&pass={pass}")
    @Produces("application/json")
    public Response getById(@PathParam(value = "login") String login, @PathParam(value = "pass") String pass) {
        return service.login(login, pass);
    }
}
