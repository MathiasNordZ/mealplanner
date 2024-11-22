package edu.ntnu.idi.bidata.menu;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CookBookMenu {
  private final UserInputHandler uiInputHandler;
  private Recipe recipe;
  private final CookBook cookBook;

  public CookBookMenu(UserInputHandler uiInputHandler, CookBook cookBook) {
    this.uiInputHandler = uiInputHandler;
    this.cookBook = cookBook;
  }

  private enum CookBookCommand {
    CREATE_RECIPE(1),
    REMOVE_RECIPE(2),
    PRINT_RECIPES(3),
    PRINT_RECIPE(4),
    RECIPE_RECOMMENDATION(5),
    BACK(0);

    private final int value;

    CookBookCommand(int value) {
      this.value = value;
    }

    private static CookBookCommand fromValue(int value) {
      for (CookBookCommand command : CookBookCommand.values()) {
        if (command.value == value) {
          return command;
        }
      }
      throw new IllegalArgumentException("The command value is invalid" + value);
    }
  }

  public void cookBookMenu() {
    CookBookCommand command = null;

    do {
      System.out.println("""
      [1] - Create recipe.
      [2] - Remove recipe.
      [3] - Print recipes.
      [4] - Print recipe.
      [5] - Recipe recommendation.
      [0] - Go back.
      """);

      int commandValue = uiInputHandler.intReader("Enter your command: ");

      try {
        command = CookBookCommand.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        case CookBookCommand.CREATE_RECIPE -> createRecipe();
        case CookBookCommand.REMOVE_RECIPE -> removeRecipe();
        case CookBookCommand.PRINT_RECIPES -> printRecipes();
        case CookBookCommand.BACK -> System.out.println("Going back to main menu.");
        default -> System.out.println("Invalid command.");
      }
    } while (command != CookBookCommand.BACK);
  }

  private void createRecipe() {
    try {
      String nameOfRecipe = uiInputHandler.stringReader("Please enter name of recipe: ");
      String recipeDescription = uiInputHandler.stringReader("Please enter a recipe description: ");
      String cookingInstructions = uiInputHandler.stringReader("Please enter cooking instructions: ");
      int amountOfServings = uiInputHandler.intReader("Please enter amount of servings");

      Map<String, Float> ingredients = new HashMap<>();
      boolean isUserDone = false;

      while (!isUserDone) {
        String nameOfIngredient = uiInputHandler.stringReader("Please enter name of ingredient (type 'done' if finished): ");
        if (nameOfIngredient.equalsIgnoreCase("Done")) {
          isUserDone = true;
        } else {
          float quantityOfIngredient = uiInputHandler.intReader("Please enter required quantity of ingredient: ");
          ingredients.put(nameOfIngredient, quantityOfIngredient);
        }
      }
      recipe = new Recipe(nameOfRecipe, recipeDescription, cookingInstructions, ingredients, amountOfServings);
      cookBook.addRecipe(recipe);
      System.out.println(nameOfRecipe + " was added successfully!");
    } catch (IllegalArgumentException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }

  // stream part inspired by copilot
  private void removeRecipe() {
    try {
      String recipeToRemove = uiInputHandler.stringReader("Please enter recipe to remove: ");
      Recipe recipe = cookBook.getAllRecipes().stream()
          .filter(r -> r.getRecipeName().equalsIgnoreCase(recipeToRemove))
          .findFirst()
          .orElseThrow(() -> new NoSuchElementException("Recipe was not found!"));
      cookBook.removeRecipe(recipe);
      System.out.println(recipe + " was removed!");
    } catch (NoSuchElementException e) {
      System.out.println("An error occured: "+ e.getMessage());
    }
  }

  private void printRecipes() {
    try {
      System.out.println(cookBook.getAllRecipes());
    } catch (NoSuchElementException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }
}
