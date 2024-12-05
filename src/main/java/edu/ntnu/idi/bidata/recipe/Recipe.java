package edu.ntnu.idi.bidata.recipe;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import static edu.ntnu.idi.bidata.recipe.RecipeValidator.*;

/**
 * This class represents a recipe.
 * It includes details such as the name, description, cooking instructions,
 * ingredients and amount of servings.
 *
 * @author Mathias Erik Nord
 * @since 13.11.24
 * @version 0.1.1
 */
public class Recipe {
  private String recipeName;
  private String recipeDescription;
  private String cookingInstructions;
  private Map<String, SimpleEntry<BigDecimal, String>> ingredients; // Refactor inspired by CoPilot.
  private int amountOfServings;

  /**
   * Constructor that will create a recipe.
   *
   * @param recipeName The name of the dish.
   * @param recipeDescription A description of the dish.
   * @param cookingInstructions The instructions on how to cook the dish.
   * @param ingredients A map of the ingredients needed and the amount of ingredient.
   * @param amountOfServings The number of servings the recipe is intended for.
   * @since 0.0.1
   *
   */
  public Recipe(String recipeName, String recipeDescription, String cookingInstructions,
                Map<String, SimpleEntry<BigDecimal, String>> ingredients, int amountOfServings) {
    setRecipeName(recipeName);
    setRecipeDescription(recipeDescription);
    setCookingInstructions(cookingInstructions);
    setIngredients(ingredients);
    setAmountOfServings(amountOfServings);
  }

  /**
   * Accessor method for <code>recipeName</code>.
   *
   * @return The name of the recipe.
   * @since 0.0.1
   */
  public String getRecipeName() {
    return recipeName;
  }

  /**
   * Mutator method for <code>recipeName</code>.
   * Will set the name of the recipe.
   *
   * @param recipeName Name of recipe.
   * @throws IllegalArgumentException if the recipe name is invalid.
   * @since 0.0.1
   */
  public void setRecipeName(String recipeName) {
    stringInputValidation(recipeName);
    this.recipeName = recipeName;
  }

  /**
   * Accessor method for <code>recipeDescription</code>.
   *
   * @return The description of the recipe.
   * @since 0.0.1
   */
  public String getRecipeDescription() {
    return recipeDescription;
  }

  /**
   * Mutator method for <code>recipeDescription</code>.
   * Will set the description of the recipe.
   *
   * @param recipeDescription Description of recipe.
   * @throws IllegalArgumentException if the recipe description is invalid.
   * @since 0.0.1
   */
  public void setRecipeDescription(String recipeDescription) {
    stringInputValidation(recipeDescription);
    this.recipeDescription = recipeDescription;
  }

  /**
   * Accessor method for <code>cookingInstructions</code>.
   *
   * @return The cooking instructions of the recipe.
   * @since 0.0.1
   */
  public String getCookingInstructions() {
    return cookingInstructions;
  }

  /**
   * Mutator method for <code>cookingInstructions</code>.
   * Sets the cooking instructions for the recipe.
   *
   * @param cookingInstructions The cooking instructions of the recipe.
   * @throws IllegalArgumentException if the cooking instructions are invalid.
   * @since 0.0.1
   */
  public void setCookingInstructions(String cookingInstructions) {
    stringInputValidation(cookingInstructions);
    this.cookingInstructions = cookingInstructions;
  }

  /**
   * Accessor method for <code>getIngredients</code>.
   *
   * @return A map of ingredients, name is key, key of value is quantity and value of value is unit of measurement.
   * @since 0.0.1
   */
  public Map<String, SimpleEntry<BigDecimal, String>> getIngredients() {
    return Map.copyOf(ingredients); // Use of copyOf inspired by CoPilot.
  }

  /**
   * Mutator method for <code>ingredients</code>.
   * Sets the ingredients for a recipe.
   *
   * @param ingredients The ingredients that are needed to make the recipe.
   * @throws IllegalArgumentException if the ingredient map is invalid.
   * @since 0.0.1
   */
  public void setIngredients(Map<String, SimpleEntry<BigDecimal, String>> ingredients) {
    mapInputValidation(ingredients);
    this.ingredients = ingredients;
  }

  /**
   * Accessor method for <code>amountOfServings</code>.
   *
   * @return The number of servings.
   * @since 0.0.1
   */
  public int getAmountOfServings() {
    return amountOfServings;
  }

  /**
   * This is a mutator method for <code>amountOfServings</code>.
   * Sets the amount of servings a recipe is intended for.
   *
   * @param amountOfServings The amount of servings.
   * @throws IllegalArgumentException if the number of servings is invalid.
   * @since 0.0.1
   */
  public void setAmountOfServings(int amountOfServings) {
    amountOfServingsValidation(amountOfServings);
    this.amountOfServings = amountOfServings;
  }
}
