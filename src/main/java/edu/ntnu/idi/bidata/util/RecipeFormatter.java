package edu.ntnu.idi.bidata.util;

import edu.ntnu.idi.bidata.recipe.Recipe;

import java.util.NoSuchElementException;
import java.util.Set;

public class RecipeFormatter {
  RecipeFormatter() {

  }

  /**
   * This method is the template of how a list of recipes should look when printed.
   *
   * @param recipes The recipes to add to table.
   * @return Will return a table according to the given format.
   * @since 0.0.1
   */
  public static String formatRecipes(Set<Recipe> recipes) {
    recipeValidation(recipes);
    StringBuilder table = new StringBuilder();
    String format = "| %-20s | %-50s | %-30s | %-15s |\n";
    table.append(String.format(format, "Name", "Description",
        "Ingredients", "Servings"));
    table.append("---------------------------------------------------------------------------------"
        + "-------------------------------------------------------------------\n");

    for (Recipe recipe : recipes) {
      table.append(String.format(format, recipe.getRecipeName(),
          recipe.getRecipeDescription(), recipe.getIngredients(),
          recipe.getAmountOfServings()));
    }
    return table.toString();
  }

  /**
   * This method is the template of how a specific recipe should look when printed.
   *
   * @param recipe Recipe to add to table.
   * @return Will return a table according to the given format.
   * @since 0.0.1
   */
  public static String formatRecipe(Set<Recipe> recipe) {
    recipeValidation(recipe);
    StringBuilder table = new StringBuilder();
    String format = "| %-20s | %-50s | %-50s | %-30s | %-15s |\n";
    table.append(String.format(format, "Name", "Description", "Instructions",
        "Ingredients", "Servings"));
    table.append("---------------------------------------------------------------------------------"
        + "-------------------------------------------------------------------\n");

    for (Recipe recipeElement : recipe) {
      table.append(String.format(format, recipeElement.getRecipeName(),
          recipeElement.getRecipeDescription(), recipeElement.getCookingInstructions(),
          recipeElement.getIngredients(), recipeElement.getAmountOfServings()));
    }
    return table.toString();
  }

  /**
   * Validation method for <code>formatRecipes</code>.
   * Will validate that the provided Set is not <code>null</code> or empty.
   * Refactored to a separate method to be less complex and more modular.
   *
   * @param recipes The recipe set to validate.
   */
  private static void recipeValidation(Set<Recipe> recipes) {
    if (recipes == null || recipes.isEmpty()) {
      if (recipes == null) {
        throw new IllegalArgumentException("Recipe list cannot be null!");
      } else {
        throw new NoSuchElementException("Recipe list has no elements!");
      }
    }
  }

  /**
   * This method will format a recipe according to the <code>formatReicpe</code> template.
   *
   * @param recipe The recipe to format.
   * @return A formatted string representation of the recipe.
   * @since 0.0.1
   */
  public static String formatRecipe(Recipe recipe) {
    return formatRecipe(Set.of(recipe));
  }
}
