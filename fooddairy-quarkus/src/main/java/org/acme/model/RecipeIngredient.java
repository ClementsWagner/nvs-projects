package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RecipeIngredient extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private float amount;

    public RecipeIngredient(){

    }

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, float amount){
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }
}
