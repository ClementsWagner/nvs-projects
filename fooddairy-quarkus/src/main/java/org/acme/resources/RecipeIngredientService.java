package org.acme.resources;

import io.vertx.core.json.JsonObject;
import org.acme.model.Recipe;
import org.acme.model.RecipeIngredient;
import org.acme.repositories.RecipeIngredientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("RecipeIngredient")
public class RecipeIngredientService {
    @Inject
    RecipeIngredientRepository recipeIngredientRepository;

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RecipeIngredient createOrUpdateIngredient(String recipeIngredient){
        JsonObject json = new JsonObject(recipeIngredient);
        int recipeId = json.getInteger("recipeId");
        int ingredientId = json.getInteger("ingredientId");
        float amount = json.getFloat("amount");
        return recipeIngredientRepository.createOrUpdateRecipeIngredient(recipeId, ingredientId, amount);
    }

}
