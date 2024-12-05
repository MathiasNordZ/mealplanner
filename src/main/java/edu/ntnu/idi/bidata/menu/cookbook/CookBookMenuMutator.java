package edu.ntnu.idi.bidata.menu.cookbook;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This class provides the user methods that can mutate the state of the cookbook.
 * It allows the user to create and remove recipes from the cookbook.
 *
 * @version 0.0.1
 * @since 28.11.2024
 */
public class CookBookMenuMutator {
  private final UserInputHandler uiInputHandler;

  /**
   * Constructs a new instance of <code>CookBookMenuMutator</code>.
   */
  public CookBookMenuMutator(UserInputHandler uiInputHandler) {
    this.uiInputHandler = uiInputHandler;
  }

  /**
   * Allows the user to create a new recipe and add it to the cookbook.
   *
   * @param errorMessage The error message to display, in case of an exception.
   * @param cookBook The cookbook where the recipe will be added.
   * @since 0.0.1
   */
  public void createRecipe(String errorMessage, CookBook cookBook) {
    try {
      Recipe recipe;
      String nameOfRecipe = uiInputHandler.stringReader("Please enter name of recipe: ");
      String recipeDescription = uiInputHandler.stringReader("Please enter a recipe description: ");
      String cookingInstructions = uiInputHandler
          .stringReader("Please enter cooking instructions: ");
      int amountOfServings = uiInputHandler.intReader("Please enter amount of servings");

      Map<String, SimpleEntry<BigDecimal, String>> ingredients = new HashMap<>();
      boolean isUserDone = false;

      promptForIngredient(isUserDone, ingredients);
      recipe = new Recipe(nameOfRecipe, recipeDescription,
          cookingInstructions, ingredients, amountOfServings);
      cookBook.addRecipe(recipe);
      System.out.println(nameOfRecipe + " was added successfully!");
    } catch (IllegalArgumentException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Prompts the user to enter ingredients and their details.
   * The method will run until user does input 'done'.
   *
   * @param isUserDone A boolean flag to indicate if the user is finished entering ingredients.
   * @param ingredients A map to store the ingredients with their
   *                    given quantities and unit of measurement.
   * @since 0.0.1
   */
  private void promptForIngredient(boolean isUserDone,
                                   Map<String, SimpleEntry<BigDecimal, String>> ingredients) {
    while (!isUserDone) {
      String nameOfIngredient = uiInputHandler
          .stringReader("Please enter name of ingredient (type 'done' if finished): ");
      if (nameOfIngredient.equalsIgnoreCase("Done")) {
        isUserDone = true;
      } else {
        BigDecimal quantityOfIngredient = uiInputHandler
            .decimalReader("Please enter required quantity of ingredient: ");
        String unitOfMeasurement = uiInputHandler
            .unitReader("Please enter unit of measurement.");
        ingredients.put(nameOfIngredient,
            new SimpleEntry<>(quantityOfIngredient, unitOfMeasurement));
      }
    }
  }

  /**
   * Allow the user to remove a recipe from the cookbook.
   *
   * @param errorMessage The error message to display, in case of an exception.
   * @param cookBook The cookbook where the recipe will be added.
   * @since 0.0.1
   */
  public void removeRecipe(String errorMessage, CookBook cookBook) {
    try {
      String recipeToRemove = uiInputHandler.stringReader("Please enter recipe to remove: ");
      cookBook.removeRecipe(recipeToRemove);
      System.out.println(recipeToRemove + " was removed!");
    } catch (NoSuchElementException | IllegalArgumentException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }
}
