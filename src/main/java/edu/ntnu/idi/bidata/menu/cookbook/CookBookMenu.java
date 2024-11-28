package edu.ntnu.idi.bidata.menu.cookbook;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.menu.StringMenu;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.register.GroceryManager;

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
  private GroceryManager groceryManager = new GroceryManager(new FoodStorage());
  private static final String ERRORMESSAGE = "An error occurred: ";
  private final StringMenu stringMenu = new StringMenu();
  private final CookBookMenuMutator cookBookMenuMutator = new CookBookMenuMutator();
  private final CookBookMenuPrinter cookBookMenuPrinter = new CookBookMenuPrinter();

  /**
   * Constructor for the <code>CookBookMeny</code> class.
   *
   * @param uiInputHandler The user input handler.
   * @param cookBook The cookbook containing recipes.
   * @param foodStorage The food storage containing groceries.
   */
  public CookBookMenu(UserInputHandler uiInputHandler, CookBook cookBook, GroceryManager groceryManager) {
    this.uiInputHandler = uiInputHandler;
    this.cookBook = cookBook;
    this.groceryManager = groceryManager;
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
        case CookBookCommand.CREATE_RECIPE -> cookBookMenuMutator.createRecipe(ERRORMESSAGE, cookBook);
        case CookBookCommand.REMOVE_RECIPE -> cookBookMenuMutator.removeRecipe(ERRORMESSAGE, cookBook);
        case CookBookCommand.PRINT_RECIPES -> cookBookMenuPrinter.printRecipes(ERRORMESSAGE, cookBook);
        case CookBookCommand.PRINT_RECIPE -> cookBookMenuPrinter.printRecipe(ERRORMESSAGE, uiInputHandler, cookBook);
        case CookBookCommand.RECIPE_RECOMMENDATION -> cookBookMenuPrinter.recipeRecommendation(ERRORMESSAGE, cookBook, groceryManager);
        case CookBookCommand.BACK -> System.out.println("Going back to main menu.");
        default -> System.out.println("Invalid command.");
      }
    } while (command != CookBookCommand.BACK);
  }
}
