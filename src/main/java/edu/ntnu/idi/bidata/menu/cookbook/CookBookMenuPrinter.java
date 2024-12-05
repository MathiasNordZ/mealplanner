package edu.ntnu.idi.bidata.menu.cookbook;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.util.CookBookFormatter;
import java.util.NoSuchElementException;

/**
 * This class provides methods to print recipes and recommendations from the cookbook.
 *
 * @author Mathias Erik Nord
 * @version 0.0.1
 * @since 28.11.2024
 */
public class CookBookMenuPrinter {

  /**
   * Constructs a new instance of <code>CookBookMenuPrinter</code>.
   */
  public CookBookMenuPrinter() {
    // Empty because no additional initialization is required.
  }

  /**
   * Prints all the recipes in the cookbook.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param cookBook The cookbook containing the recipes to be printed.
   */
  public void printRecipes(String errorMessage, CookBook cookBook) {
    try {
      String formattedRecipes = CookBookFormatter.formatRecipes(cookBook.getAllRecipes());
      System.out.println(formattedRecipes);
    } catch (NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Prints a specific recipe from the cookbook.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param uiInputHandler The handler for user input, used to read input from the user.
   * @param cookBook The cookbook containing the recipe to print.
   * @since 0.0.1
   */
  public void printRecipe(String errorMessage, UserInputHandler uiInputHandler, CookBook cookBook,
                          FoodStorage foodStorage) {
    try {
      System.out.println("Printable recipes: ");
      cookBook.getAllRecipes().forEach((s, recipe) -> System.out.println(recipe.getRecipeName()));
      System.out.println();
      String recipeToPrint = uiInputHandler.stringReader("Please enter recipe to print: ");
      Recipe recipe = cookBook.getRecipe(recipeToPrint);
      boolean isAvailable = cookBook.matchRecipeToGrocery(foodStorage, recipe);
      String availability = isAvailable ? "You have all ingredients for this recipe.\n"
          : "You do not have enough ingredients for this recipe.\n";
      String formattedRecipe = CookBookFormatter.formatRecipe(recipe);
      System.out.println(formattedRecipe);
      System.out.println(availability);
    } catch (NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Prints a recommended recipe based on the available ingredients in the food storage.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param cookBook The cookbook containing the recipes.
   * @param foodStorage The food storage containing the available ingredients.
   * @since 0.0.1
   */
  public void recipeRecommendation(String errorMessage, CookBook cookBook,
                                   FoodStorage foodStorage) {
    try {
      Recipe recommendedRecipe = cookBook.recipeRecommendation(foodStorage);
      System.out.println("Recommended recipe: " + recommendedRecipe.getRecipeName());
    } catch (NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }
}
