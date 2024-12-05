package edu.ntnu.idi.bidata.menu.cookbook;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.menu.StringMenu;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.register.FoodStorage;

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
  private final StringMenu stringMenu;
  private final CookBookMenuMutator cookBookMenuMutator;
  private final CookBookMenuPrinter cookBookMenuPrinter;

  /**
   * Constructs a new instance of <code>CookBookMenu</code>.
   *
   * @param uiInputHandler The handler for user input, used to read commands and data from user.
   * @param cookBook The cookbook that contains the collection of recipes to be managed.
   * @param foodStorage The storage that contains the groceries used in the recipes.
   * @since 0.0.1
   */
  public CookBookMenu(UserInputHandler uiInputHandler, CookBook cookBook, FoodStorage foodStorage,
                      StringMenu stringMenu, CookBookMenuMutator cookBookMenuMutator,
                      CookBookMenuPrinter cookBookMenuPrinter) {
    this.uiInputHandler = uiInputHandler;
    this.cookBook = cookBook;
    this.foodStorage = foodStorage;
    this.stringMenu = stringMenu;
    this.cookBookMenuMutator = cookBookMenuMutator;
    this.cookBookMenuPrinter = cookBookMenuPrinter;
  }

  /**
   * Enum that is representing the command available in the cook book menu.
   */
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

    /**
     * Method that will return the command based on the given value.
     *
     * @param value The value of the given command.
     * @return The command corresponding to the value.
     * @throws IllegalArgumentException if the value does not correspond to any command.
     * @since 0.0.1
     */
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
   * The display of the cook book menu where the user is interacting.
   */
  public void cookBookMenu() {
    CookBookCommand command = null;

    do {
      stringMenu.printCookbookMenu();
      int commandValue = uiInputHandler.intReader("Enter your command: ");

      try {
        command = CookBookCommand.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command. " + e.getMessage());
        continue;
      }

      commandHandler(command);
    } while (command != CookBookCommand.BACK);
  }

  /**
   * Handles the given command by executing the action linked to the command.
   *
   * @param command The command to handle.
   * @since 0.0.1
   */
  private void commandHandler(CookBookCommand command) {
    switch (command) {
      case CookBookCommand.CREATE_RECIPE ->
          cookBookMenuMutator.createRecipe(ERRORMESSAGE, cookBook);
      case CookBookCommand.REMOVE_RECIPE ->
          cookBookMenuMutator.removeRecipe(ERRORMESSAGE, cookBook);
      case CookBookCommand.PRINT_RECIPES ->
          cookBookMenuPrinter.printRecipes(ERRORMESSAGE, cookBook);
      case CookBookCommand.PRINT_RECIPE ->
          cookBookMenuPrinter.printRecipe(ERRORMESSAGE, uiInputHandler, cookBook, foodStorage);
      case CookBookCommand.RECIPE_RECOMMENDATION ->
          cookBookMenuPrinter.recipeRecommendation(ERRORMESSAGE, cookBook, foodStorage);
      case CookBookCommand.BACK -> System.out.println("Going back to main menu. ");
      default -> System.out.println("Invalid command. ");
    }
  }
}
