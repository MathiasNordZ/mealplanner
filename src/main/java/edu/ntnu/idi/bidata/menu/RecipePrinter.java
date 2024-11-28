package edu.ntnu.idi.bidata.menu;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.util.CookBookFormatter;

import java.util.NoSuchElementException;

public class RecipePrinter {
  public RecipePrinter() {

  }

  /**
   * Prints all the recipes in the cookbook.
   *
   * @since 0.0.1
   */
  public void printRecipes(String ERRORMESSAGE, CookBook cookBook) {
    try {
      String formattedRecipes = CookBookFormatter.formatRecipes(cookBook.getAllRecipes());
      System.out.println(formattedRecipes);
    } catch (NoSuchElementException e) {
      System.out.println(ERRORMESSAGE + e.getMessage());
    }
  }

  /**
   * Prints a given recipe from the cookbook.
   *
   * @since 0.0.1
   */
  public void printRecipe(String ERRORMESSAGE, UserInputHandler uiInputHandler, CookBook cookBook) {
    try {
      String recipeToPrint = uiInputHandler.stringReader("Please enter recipe to print: ");
      Recipe recipe = cookBook.getAllRecipes().stream()
          .filter(r -> r.getRecipeName().equalsIgnoreCase(recipeToPrint))
          .findFirst()
          .orElseThrow(() -> new NoSuchElementException("Recipe was not found!"));
      String formattedRecipe = CookBookFormatter.formatRecipe(recipe);
      System.out.println(formattedRecipe);
    } catch (NoSuchElementException e) {
      System.out.println(ERRORMESSAGE + e.getMessage());
    }
  }
}
