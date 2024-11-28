package edu.ntnu.idi.bidata.menu.cookbook;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CookBookMenuMutator {
  private final UserInputHandler uiInputHandler = new UserInputHandler();

  public CookBookMenuMutator() {

  }

  /**
   * Makes the user able to create a new recipe.
   * Will then add it to the cookbook.
   *
   * @since 0.0.1
   */
  void createRecipe(String ERRORMESSAGE, CookBook cookBook) {
    try {
      Recipe recipe;
      String nameOfRecipe = uiInputHandler.stringReader("Please enter name of recipe: ");
      String recipeDescription = uiInputHandler.stringReader("Please enter a recipe description: ");
      String cookingInstructions = uiInputHandler
          .stringReader("Please enter cooking instructions: ");
      int amountOfServings = uiInputHandler.intReader("Please enter amount of servings");

      Map<String, SimpleEntry<Float, String>> ingredients = new HashMap<>();
      boolean isUserDone = false;

      while (!isUserDone) {
        String nameOfIngredient = uiInputHandler
            .stringReader("Please enter name of ingredient (type 'done' if finished): ");
        if (nameOfIngredient.equalsIgnoreCase("Done")) {
          isUserDone = true;
        } else {
          float quantityOfIngredient = uiInputHandler
              .intReader("Please enter required quantity of ingredient: ");
          String unitOfMeasurement = uiInputHandler.stringReader("Please enter unit of measurement.");
          ingredients.put(nameOfIngredient, new SimpleEntry<>(quantityOfIngredient, unitOfMeasurement));
        }
      }
      recipe = new Recipe(nameOfRecipe, recipeDescription,
          cookingInstructions, ingredients, amountOfServings);
      cookBook.addRecipe(recipe);
      System.out.println(nameOfRecipe + " was added successfully!");
    } catch (IllegalArgumentException e) {
      System.out.println(ERRORMESSAGE + e.getMessage());
    }
  }

  /**
   * stream part inspired by copilot.
   *
   * <p>Will make the user able to remove a recipe from the cookbook.</p>
   *
   * @since 0.0.1
   */
  void removeRecipe(String ERRORMESSAGE, CookBook cookBook) {
    try {
      String recipeToRemove = uiInputHandler.stringReader("Please enter recipe to remove: ");
      Recipe recipe = cookBook.getAllRecipes().stream()
          .filter(r -> r.getRecipeName().equalsIgnoreCase(recipeToRemove))
          .findFirst()
          .orElseThrow(() -> new NoSuchElementException("Recipe was not found!"));
      cookBook.removeRecipe(recipe);
      System.out.println(recipe + " was removed!");
    } catch (NoSuchElementException e) {
      System.out.println(ERRORMESSAGE + e.getMessage());
    }
  }
}
