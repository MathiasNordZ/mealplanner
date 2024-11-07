package edu.ntnu.idi.bidata;

import edu.ntnu.idi.bidata.entity.Grocery;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is representing a recipe.
 */
public class Recipe {
  private String recipeName;
  private String recipeDescription;
  private String cookingInstructions;
  private List<Grocery> ingredients;
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
                List<Grocery> ingredients, int amountOfServings) {
    setRecipeName(recipeName);
    setRecipeDescription(recipeDescription);
    setCookingInstructions(cookingInstructions);
    setIngredients(ingredients);
    setAmountOfServings(amountOfServings);
  }

  public Recipe() {

  }

  /**
   * Validation method for <code>String</code> inputs.
   *
   * @param stringInput input that is passed from outer method.
   * @throws IllegalArgumentException is thrown if string input is null, blank or empty.
   */
  private void stringInputValidation(String stringInput) {
    String errorMessage = "Provided input cannot be null, empty or blank.";
    if (stringInput == null || stringInput.isBlank() || stringInput.isEmpty()) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    stringInputValidation(recipeName);
    this.recipeName = recipeName;
  }

  public String getRecipeDescription() {
    return recipeDescription;
  }

  public void setRecipeDescription(String recipeDescription) {
    stringInputValidation(recipeDescription);
    this.recipeDescription = recipeDescription;
  }

  public String getCookingInstructions() {
    return cookingInstructions;
  }

  public void setCookingInstructions(String cookingInstructions) {
    stringInputValidation(cookingInstructions);
    this.cookingInstructions = cookingInstructions;
  }

  public List<Grocery> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Grocery> ingredients) {
    String errorMessage;
    if (ingredients == null) {
      errorMessage = "The inputted ingredients cannot be null";
      throw new IllegalArgumentException(errorMessage);
    }
    this.ingredients = ingredients;
  }

  public int getAmountOfServings() {
    return amountOfServings;
  }

  public void setAmountOfServings(int amountOfServings) {
    String errorMessage;
    if (amountOfServings <= 0) {
      errorMessage = "The inputted amount of servings cannot be less than zero";
      throw new IllegalArgumentException(errorMessage);
    }
    this.amountOfServings = amountOfServings;
  }

  @Override
  public String toString() {
    return "Name of recipe : " + getRecipeName() +
        ", Description : " + getRecipeDescription() +
        ", Cooking Instructions : " + getCookingInstructions() +
        ", Amount of servings : " + getAmountOfServings() +
        ", Ingredients : " + getIngredients();
  }

  /*
  public boolean isPossibleToMake(FoodStorage foodStorage) {

  }
   */
}
