package resource;


import jwt.GenerateToken;
import model.ShopUser;
import org.eclipse.microprofile.jwt.JsonWebToken;
import repository.UserRepository;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped
@Path("/user")
public class UserService {

    @Inject
    protected UserRepository userRepository;

    @POST
    @Path("authenticate")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(String email, String password){
        boolean authenticated = userRepository.authenticateUser(email, password);

        if(!authenticated) return Response.status(403, "Authorization failed").build();

        return Response.ok(GenerateToken.generate()).build();
    }

    @POST
    @Path("register")
    @PermitAll
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String email, String password){
        ShopUser newUser = new ShopUser();
        newUser.setEmail(email);
        newUser.setUserPassword(password);

        return Response.ok(userRepository.addUser(newUser)).build();
    }

}
