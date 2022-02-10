package org.acme.resources;

import org.acme.model.Ingredient;
import org.acme.model.Recipe;
import org.acme.repositories.IngredientRepository;
import org.acme.repositories.RecipeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("Recipe")
public class RecipeService {
    @Inject
    protected RecipeRepository recipeRepository;


    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recipe> getRecipes(){
        return  recipeRepository.getAllRecipes();
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Recipe createOrUpdateIngredient(Recipe recipe){
        return recipeRepository.createOrInsertRecipe(recipe);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Recipe getIngredientById(@PathParam("id") int recipeId){
        return recipeRepository.getRecipeById(recipeId);
    }

    @Path("/{id}")
    @DELETE
    public void deleteIngredient(@PathParam("id") int recipeId){
        recipeRepository.deleteRecipe(recipeId);
    }
}
