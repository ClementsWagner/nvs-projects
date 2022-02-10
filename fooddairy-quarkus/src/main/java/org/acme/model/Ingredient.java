package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient extends PanacheEntityBase {

    @GeneratedValue
    @Id
    private int ingredientId;

    private String name;
    private String unit;
    private int calories;
    private float fat;
    private float carbohydrates;
    private float sugar;
    private float protein;
    private float salt;

    public Ingredient(){

    }

    public Ingredient(String name, String unit, int calories, float fat, float carbohydrates, float sugar, float protein, float salt) {
        this.name = name;
        this.unit = unit;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.sugar = sugar;
        this.protein = protein;
        this.salt = salt;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getSugar() {
        return sugar;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getSalt() {
        return salt;
    }

    public void setSalt(float salt) {
        this.salt = salt;
    }
}
