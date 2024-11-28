package edu.ntnu.idi.bidata.menu;

import edu.ntnu.idi.bidata.util.CookBookFormatter;
import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
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
  private final RecipeMutator recipeMutator = new RecipeMutator();
  private final RecipePrinter recipePrinter = new RecipePrinter();

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
        case CookBookCommand.CREATE_RECIPE -> recipeMutator.createRecipe(ERRORMESSAGE, cookBook);
        case CookBookCommand.REMOVE_RECIPE -> recipeMutator.removeRecipe(ERRORMESSAGE, cookBook);
        case CookBookCommand.PRINT_RECIPES -> recipePrinter.printRecipes(ERRORMESSAGE, cookBook);
        case CookBookCommand.PRINT_RECIPE -> recipePrinter.printRecipe(ERRORMESSAGE, uiInputHandler, cookBook);
        case CookBookCommand.RECIPE_RECOMMENDATION -> recipeRecommendation();
        case CookBookCommand.BACK -> System.out.println("Going back to main menu.");
        default -> System.out.println("Invalid command.");
      }
    } while (command != CookBookCommand.BACK);
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
