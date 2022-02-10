package org.acme.repositories;

import org.acme.model.Ingredient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class IngredientRepository {
    @Inject
    protected EntityManager em;

    public List<Ingredient> getAllIngredients(){
        return em.createQuery("SELECT i FROM Ingredient i", Ingredient.class).getResultList();
    }

    @Transactional
    public Ingredient createOrUpdateIngredient(Ingredient ingredient){
       return em.merge(ingredient);
    }

    public Ingredient getIngredientById(int ingredientId){
        return em.find(Ingredient.class, ingredientId);
    }

    @Transactional
    public void deleteIngredient(int ingredientId){
        em.remove(em.find(Ingredient.class, ingredientId));
    }
}
