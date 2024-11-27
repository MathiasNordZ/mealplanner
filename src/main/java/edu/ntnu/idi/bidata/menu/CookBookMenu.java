package edu.ntnu.idi.bidata.menu;

import edu.ntnu.idi.bidata.util.StringFormatter;
import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This class represents the cook book menu in the application.
 * In the cook book menu the user should be able to manage the recipes.
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>>
 * @since <b>22.11.2024</b>
 */
public class CookBookMenu {
  private final UserInputHandler uiInputHandler;
  private final CookBook cookBook;
  private final FoodStorage foodStorage;
  private static final String ERRORMESSAGE = "An error occurred: ";
  private final StringMenu stringMenu = new StringMenu();

  /**
   * Constructor for the <code>CookBookMeny</code> class.
   *
   * @param uiInputHandler The user input handler.
   * @param cookBook The cookbook containing recipes.
   * @param foodStorage The food storage containing groceries.
   */
  public CookBookMenu(UserInputHandler uiInputHandler, CookBook cookBook, FoodStorage foodStorage) {
    this.uiInputHandler = uiInputHandler;
    this.cookBook = cookBook;
    this.foodStorage = foodStorage;
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

  /**
   * The display of the cook book menu.
   * Interacts with the user by taking the inputs.
   */
  public void cookBookMenu() {
    CookBookCommand command = null;

    do {
      stringMenu.printCookbookMenu();
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
        case CookBookCommand.PRINT_RECIPE -> printRecipe();
        case CookBookCommand.RECIPE_RECOMMENDATION -> recipeRecommendation();
        case CookBookCommand.BACK -> System.out.println("Going back to main menu.");
        default -> System.out.println("Invalid command.");
      }
    } while (command != CookBookCommand.BACK);
  }

  /**
   * Makes the user able to create a new recipe.
   * Will then add it to the cookbook.
   *
   * @since 0.0.1
   */
  private void createRecipe() {
    try {
      Recipe recipe;
      String nameOfRecipe = uiInputHandler.stringReader("Please enter name of recipe: ");
      String recipeDescription = uiInputHandler.stringReader("Please enter a recipe description: ");
      String cookingInstructions = uiInputHandler
          .stringReader("Please enter cooking instructions: ");
      int amountOfServings = uiInputHandler.intReader("Please enter amount of servings");

      Map<String, Float> ingredients = new HashMap<>();
      boolean isUserDone = false;

      while (!isUserDone) {
        String nameOfIngredient = uiInputHandler
            .stringReader("Please enter name of ingredient (type 'done' if finished): ");
        if (nameOfIngredient.equalsIgnoreCase("Done")) {
          isUserDone = true;
        } else {
          float quantityOfIngredient = uiInputHandler
              .intReader("Please enter required quantity of ingredient: ");
          ingredients.put(nameOfIngredient, quantityOfIngredient);
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
      System.out.println(ERRORMESSAGE + e.getMessage());
    }
  }

  /**
   * Prints all the recipes in the cookbook.
   *
   * @since 0.0.1
   */
  private void printRecipes() {
    try {
      String formattedRecipes = StringFormatter.formatRecipes(cookBook.getAllRecipes());
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
  private void printRecipe() {
    try {
      String recipeToPrint = uiInputHandler.stringReader("Please enter recipe to print: ");
      Recipe recipe = cookBook.getAllRecipes().stream()
          .filter(r -> r.getRecipeName().equalsIgnoreCase(recipeToPrint))
          .findFirst()
          .orElseThrow(() -> new NoSuchElementException("Recipe was not found!"));
      String formattedRecipe = StringFormatter.formatRecipe(recipe);
      System.out.println(formattedRecipe);
    } catch (NoSuchElementException e) {
      System.out.println(ERRORMESSAGE + e.getMessage());
    }
  }

  private void recipeRecommendation() {
    try {
      Recipe recommendedRecipe = cookBook.recipeRecommendation(foodStorage);
      System.out.println("Recommended recipe: " + recommendedRecipe.getRecipeName());
    } catch (NoSuchElementException e) {
      System.out.println(ERRORMESSAGE + e.getMessage());
    }
  }
}
