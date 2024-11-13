package edu.ntnu.idi.bidata;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.Map;

/**
 * This class is representing a recipe.
 *
 * @author Mathias Erik Nord
 * @since 13.11.24
 * @version 0.1.1
 */
public class Recipe {
  private String recipeName;
  private String recipeDescription;
  private String cookingInstructions;
  private Map<String, Float> ingredients;
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
                Map<String, Float> ingredients, int amountOfServings) {
    setRecipeName(recipeName);
    setRecipeDescription(recipeDescription);
    setCookingInstructions(cookingInstructions);
    setIngredients(ingredients);
    setAmountOfServings(amountOfServings);
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
  public Map<String, Float> getIngredients() {
    return ingredients;
  }

  /**
   * Error handling method for the method<code>setIngredients</code>.
   *
   * @param ingredients The Map to validate.
   * @throws IllegalArgumentException if the <code>ingredients</code> is null,
   *                                  if <code>ingredientName</code> is null or blank,
   *                               or if <code>quantity</code> is null, less than or equal to zero.
   */
  public void mapInputValidation(Map<String, Float> ingredients) {
    StringBuilder errorMessage = new StringBuilder();
    if (ingredients == null) {
      errorMessage.append("The inputted ingredients cannot be null\n");
    } else {
      for (Map.Entry<String, Float> entry : ingredients.entrySet()) {
        String ingredientName = entry.getKey();
        Float quantity = entry.getValue();

        if (ingredientName == null || ingredientName.isBlank()) {
          errorMessage.append("Ingredient name cannot be null or blank.\n");
        }
        if (quantity == null || quantity <= 0) {
          errorMessage.append("Quantity cannot be null, equal or less than zero.\n");
        }
      }
    }
    if (!errorMessage.isEmpty()) {
      throw new IllegalArgumentException(errorMessage.toString());
    }
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
  public void setIngredients(Map<String, Float> ingredients) {
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
    String errorMessage;
    if (amountOfServings <= 0) {
      errorMessage = "The inputted amount of servings cannot be less than zero";
      throw new IllegalArgumentException(errorMessage);
    }
    this.amountOfServings = amountOfServings;
  }

  /**
   * This is a boolean method to check if a recipe is possible to make,
   * with the groceries in a given food storage.
   *
   * @param foodStorage The storage to check for available groceries. Ex. fridge, freezer etc.
   * @return Will return true if recipe is possible to make, if not it will return false.
   */
  public boolean isPossibleToCook(FoodStorage foodStorage) {
    if (foodStorage == null) {
      throw new IllegalArgumentException("The foodStorage cannot be null.");
    }
    for (Map.Entry<String, Float> entry : ingredients.entrySet()) {
      String ingredientName = entry.getKey();
      float requiredQuantity = entry.getValue();
      boolean isFound = false;
      for (Grocery storedGrocery : foodStorage.getSortedList()) {
        if (storedGrocery.getName().equals(ingredientName) && storedGrocery.getQuantity()
                >= requiredQuantity) {
          isFound = true;
        }
      }
      if (!isFound) {
        return false;
      }
    }
    return true;
  }

  /**
   * This is a formatting method, that will format the object to a string when printing the object.
   * This is to make the object more readable, instead of getting an ID in return.
   *
   * @return Will return a formatted string, with information about the object.
   */
  @Override
  public String toString() {
    return "Name of recipe : " + getRecipeName()
            + ", Description : " + getRecipeDescription()
            + ", Cooking Instructions : " + getCookingInstructions()
            + ", Amount of servings : " + getAmountOfServings()
            + ", Ingredients : " + getIngredients();
  }
}
