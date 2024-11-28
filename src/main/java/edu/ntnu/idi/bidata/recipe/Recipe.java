package edu.ntnu.idi.bidata.recipe;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import static edu.ntnu.idi.bidata.recipe.RecipeValidator.*;

/**
 * This class is representing a recipe.
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>13.11.24</b>
 * @version <b>0.1.1</b>
 */
public class Recipe {
  private String recipeName;
  private String recipeDescription;
  private String cookingInstructions;
  private Map<String, SimpleEntry<Float, String>> ingredients; // Refactor inspired by CoPilot.
  private int amountOfServings;

  /**
   * Constructor that will create a recipe.
   *
   * @param recipeName This is the name of the dish.
   * @param recipeDescription This is a description of the dish.
   * @param cookingInstructions This is the instructions of how to cook the dish.
   * @param ingredients This is a map of the ingredients needed and the amount of ingredient.
   *
   */
  public Recipe(String recipeName, String recipeDescription, String cookingInstructions,
                Map<String, SimpleEntry<Float, String>> ingredients, int amountOfServings) {
    setRecipeName(recipeName);
    setRecipeDescription(recipeDescription);
    setCookingInstructions(cookingInstructions);
    setIngredients(ingredients);
    setAmountOfServings(amountOfServings);
  }

  /**
   * Accessor method for <code>recipeName</code>.
   *
   * @return Will return name of the recipe.
   */
  public String getRecipeName() {
    return recipeName;
  }

  /**
   * Mutator method for <code>recipeName</code>.
   * Will set the name of the recipe.
   *
   * @param recipeName Name of recipe.
   */
  public void setRecipeName(String recipeName) {
    stringInputValidation(recipeName);
    this.recipeName = recipeName;
  }

  /**
   * Accessor method for <code>recipeDescription</code>.
   *
   * @return Will return the description of the recipe.
   */
  public String getRecipeDescription() {
    return recipeDescription;
  }

  /**
   * Mutator method for <code>recipeDescription</code>.
   * Will set the description of the recipe.
   *
   * @param recipeDescription Description of recipe.
   */
  public void setRecipeDescription(String recipeDescription) {
    stringInputValidation(recipeDescription);
    this.recipeDescription = recipeDescription;
  }

  /**
   * Accessor method for <code>cookingInstructions</code>.
   *
   * @return Will return the cooking instructions of the recipe.
   */
  public String getCookingInstructions() {
    return cookingInstructions;
  }

  /**
   * Mutator method for <code>cookingInstructions</code>.
   * This method will set the cooking instructions of the recipe.
   *
   * @param cookingInstructions The cooking instructions of the recipe.
   */
  public void setCookingInstructions(String cookingInstructions) {
    stringInputValidation(cookingInstructions);
    this.cookingInstructions = cookingInstructions;
  }

  /**
   * Accessor method for <code>getIngredients</code>.
   *
   * @return Will return a map of ingredients, with name as key and quantity as value.
   */
  public Map<String, SimpleEntry<Float, String>> getIngredients() {
    return ingredients;
  }

  /**
   * Mutator method for <code>ingredients</code>.
   * Will set the ingredients for a recipe.
   * Takes in the name of the ingredient as a <code>String</code>,
   * and the quantity of the ingredient as a <code>Float</code>.
   * Error handling is handled by <code>mapInputValidation</code>.
   *
   * @param ingredients The ingredients that are needed to make the recipe.
   */
  public void setIngredients(Map<String, SimpleEntry<Float, String>> ingredients) {
    mapInputValidation(ingredients);
    this.ingredients = ingredients;
  }

  /**
   * Accessor method for <code>amountOfServings</code>.
   *
   * @return Will return the amount of servings a recipe is intended for.
   */
  public int getAmountOfServings() {
    return amountOfServings;
  }

  /**
   * This is a mutator method for <code>amountOfServings</code>.
   * Will set the amount of servings a recipe is intended for.
   *
   * @param amountOfServings The amount of servings.
   */
  public void setAmountOfServings(int amountOfServings) {
    amountOfServingsValidation(amountOfServings);
    this.amountOfServings = amountOfServings;
  }
}
