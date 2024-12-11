package edu.ntnu.idi.bidata.menu.cookbook;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.util.StringFormatter;
import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This class provides the user methods that can interact with the cookbook.
 *
 * @author Mathias Erik Nord
 * @version 1.0.0
 * @since 28.11.2024
 */
public class CookBookMenuMutator {
  private final UserInputHandler inputHandler;

  /**
   * Constructs a new instance of <code>CookBookMenuMutator</code>.
   *
   * @param inputHandler The input handler.
   */
  public CookBookMenuMutator(UserInputHandler inputHandler) {
    this.inputHandler = inputHandler;
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
      String recipeName = inputHandler.stringReader("Please enter name of recipe: ");
      String recipeDescription = inputHandler.stringReader("Please enter a recipe description: ");
      String cookingInstructions = inputHandler
          .stringReader("Please enter cooking instructions: ");
      int servings = inputHandler.intReader("Please enter amount of servings");

      Map<String, SimpleEntry<BigDecimal, String>> ingredients = new HashMap<>();
      boolean isUserDone = false;

      ingredientPrompt(isUserDone, ingredients);
      recipe = new Recipe(recipeName, recipeDescription,
          cookingInstructions, ingredients, servings);
      cookBook.addRecipe(recipe);
      System.out.println("\n" + recipeName + StringFormatter.GREEN + " was added successfully!");
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
  private void ingredientPrompt(boolean isUserDone,
                                Map<String, SimpleEntry<BigDecimal, String>> ingredients) {
    while (!isUserDone) {
      String name = inputHandler
          .stringReader("Please enter name of ingredient (type 'done' if finished): ");
      if (name.equalsIgnoreCase("Done")) {
        isUserDone = true;
      } else {
        BigDecimal quantity = inputHandler
            .decimalReader("Please enter required quantity of ingredient: ");
        String unit = inputHandler
            .unitReader("Please enter unit of measurement. (kilogram/liter/pcs).");
        ingredients.put(name,
            new SimpleEntry<>(quantity, unit));
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
      String recipeToRemove = inputHandler.stringReader("Please enter recipe to remove: ");
      cookBook.removeRecipe(recipeToRemove);
      System.out.println("\n" + recipeToRemove
          + StringFormatter.GREEN + " was removed!" + StringFormatter.RESET);
    } catch (NoSuchElementException | IllegalArgumentException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }
}
