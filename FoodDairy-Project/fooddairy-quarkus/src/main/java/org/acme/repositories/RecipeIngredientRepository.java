package org.acme.repositories;

import org.acme.model.Ingredient;
import org.acme.model.Recipe;
import org.acme.model.RecipeIngredient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;

@ApplicationScoped
public class RecipeIngredientRepository {
    @Inject
    EntityManager em;

    @Transactional
    public RecipeIngredient createOrUpdateRecipeIngredient(int recipeId, int ingredientId, float amount){
        return em.merge(new RecipeIngredient(em.find(Recipe.class, recipeId), em.find(Ingredient.class, ingredientId), amount));
    }

}
