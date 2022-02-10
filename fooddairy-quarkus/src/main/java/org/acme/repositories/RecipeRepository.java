package org.acme.repositories;

import org.acme.model.Recipe;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class RecipeRepository {
    @Inject
    EntityManager em;

    public List<Recipe> getAllRecipes(){
        return em.createQuery("SELECT r from Recipe r", Recipe.class).getResultList();
    }

    @Transactional
    public Recipe createOrInsertRecipe(Recipe recipe){
        return em.merge(recipe);
    }

    public Recipe getRecipeById(int recipeId){
        return em.find(Recipe.class, recipeId);
    }

    @Transactional
    public void deleteRecipe(int recipeId){
        em.remove(em.find(Recipe.class, recipeId));
    }

}
