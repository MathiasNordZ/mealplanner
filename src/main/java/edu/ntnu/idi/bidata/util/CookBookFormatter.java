package edu.ntnu.idi.bidata.util;

import edu.ntnu.idi.bidata.recipe.Recipe;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Utility class for formatting the cookbook.
 * Provides methods to format a list of recipes, or a single recipe into a readable format.
 *
 * @author Mathias Erik Nord
 * @since 23.11.2024
 * @version 0.0.1
 */
public class CookBookFormatter {
  private CookBookFormatter() {
    // Private constructor to prevent instantiation.
  }

  /**
   * Formats a set of recipes into table format.
   *
   * @param recipes The recipes to add to table.
   * @return String table according to the given format.
   * @throws IllegalArgumentException if the recipe set is null
   * @throws NoSuchElementException if the recipe set is empty
   * @since 0.0.1
   */
  public static String formatRecipes(Map<String, Recipe> recipes) {
    recipeValidation(recipes);
    StringBuilder table = new StringBuilder();
    String format = "| %-20s | %-50s | %-50s | %-15s |\n";
    table.append(String.format(format, "Name", "Description",
        "Ingredients", "Servings"));
    table.append("---------------------------------------------------------------------------------"
        + "-------------------------------------------------------------------\n");

    for (Recipe recipe : recipes.values()) {
      table.append(String.format(format, recipe.getRecipeName(),
          recipe.getRecipeDescription(), recipe.getIngredients(),
          recipe.getAmountOfServings()));
    }
    return table.toString();
  }

  /**
   * Format a set of recipes into a table format.
   *
   * @param recipe The recipe to add to table.
   * @return A table string according to the given format.
   * @throws IllegalArgumentException if the recipe set is null
   * @throws NoSuchElementException if the recipe set is empty
   * @since 0.0.1
   */
  public static String formatRecipe(Map<String, Recipe> recipe) {
    recipeValidation(recipe);
    StringBuilder table = new StringBuilder();
    String format = "| %-20s | %-50s | %-50s | %-50s | %-15s |\n";
    table.append(String.format(format, "Name", "Description", "Instructions",
        "Ingredients", "Servings"));
    table.append("---------------------------------------------------------------------------------"
        + "--------------------------------------------------------------------"
        + "-------------------------------------------------------------------\n");

    for (Recipe recipeElement : recipe.values()) {
      table.append(String.format(format, recipeElement.getRecipeName(),
          recipeElement.getRecipeDescription(), recipeElement.getCookingInstructions(),
          recipeElement.getIngredients(), recipeElement.getAmountOfServings()));
    }
    return table.toString();
  }

  /**
   * Formats a single recipe into a table format.
   *
   * @param recipe The recipe to format.
   * @return A formatted string representation of the recipe.
   * @since 0.0.1
   */
  public static String formatRecipe(Recipe recipe) {
    return formatRecipe(Map.of(recipe.getRecipeName(), recipe));
  }

  /**
   * Validates the provided set of recipes.
   * Checks if the set is not null or empty.
   *
   * @param recipes The recipe set to validate.
   * @throws IllegalArgumentException if the recipe set is null
   * @throws NoSuchElementException if the recipe set is empty
   * @since 0.0.1
   */
  private static void recipeValidation(Map<String, Recipe> recipes) {
    if (recipes == null || recipes.isEmpty()) {
      if (recipes == null) {
        throw new IllegalArgumentException("Recipe list cannot be null!");
      } else {
        throw new NoSuchElementException("Recipe list has no elements!");
      }
    }
  }
}
