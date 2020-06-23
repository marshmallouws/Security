package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("demo")
public class DemoResource {

    @Context
    private UriInfo context;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    SecurityContext securityContext;

    // Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    public String getFromUser(@PathParam("mail") String mail) {
        return ("{\"data\": \"Hello from user: " + mail + "\"}");  //Return as JSON
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    public String getFromAdmin(@PathParam("mail") String mail) {
        return ("{\"data\": \"Hello from admin" + mail + "\"}");  //Return as JSON
    }
}

