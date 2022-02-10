package org.acme.resources;

import org.acme.model.Ingredient;
import org.acme.repositories.IngredientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("Ingredient")
public class IngredientService {
    @Inject
    protected IngredientRepository ingredientRepository;


    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingredient> getIngredients(){
        return  ingredientRepository.getAllIngredients();
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient createOrUpdateIngredient(Ingredient ingredient){
        return ingredientRepository.createOrUpdateIngredient(ingredient);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient getIngredientById(@PathParam("id") int ingredientId){
        return ingredientRepository.getIngredientById(ingredientId);
    }

    @Path("/{id}")
    @DELETE
    public void deleteIngredient(@PathParam("id") int ingredientId){
        ingredientRepository.deleteIngredient(ingredientId);
    }

}
