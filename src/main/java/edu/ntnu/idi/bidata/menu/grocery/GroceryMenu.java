package edu.ntnu.idi.bidata.menu.grocery;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.menu.StringMenu;
import edu.ntnu.idi.bidata.register.FoodStorage;

/**
 * This class represents the grocery menu in the application.
 * In the grocery menu, the user can manage groceries.
 *
 * @author Mathias Erik Nord
 * @version 1.0.0
 * @since 22.11.2024
 */
public class GroceryMenu {
  private final UserInputHandler inputHandler;
  private final FoodStorage foodStorage;
  private static final String ERRORMESSAGE = "An error occurred: ";
  private final StringMenu stringMenu;
  private final GroceryMenuMutator groceryMutator;
  private final GroceryMenuPrinter groceryPrinter;

  /**
   * Constructs a new instance of <code>GroceryMenu</code>.
   *
   * @param foodStorage The food storage that contains the groceries.
   * @param inputHandler The user input handler.
   * @param stringMenu The string menu.
   * @param groceryMutator The grocery menu mutator.
   * @since 0.0.1
   */
  public GroceryMenu(FoodStorage foodStorage, UserInputHandler inputHandler,
                     StringMenu stringMenu, GroceryMenuMutator groceryMutator) {
    this.foodStorage = foodStorage;
    this.inputHandler = inputHandler;
    this.stringMenu = stringMenu;
    this.groceryMutator = groceryMutator;
    this.groceryPrinter = new GroceryMenuPrinter(inputHandler);
  }

  /**
   * Inspired by world of zuul.
   * This is an enum representing the available commands in the grocery menu.
   *
   * @since 0.0.1
   */
  private enum GroceryCommand {
    CREATE_NEW_GROCERY(1),
    REMOVE_GROCERY(2),
    SEARCH_FOR_GROCERY(3),
    LIST_OF_ALL_GROCERIES(4),
    LIST_OF_EXPIRED_GROCERIES(5),
    VALUE_OF_ALL_GROCERIES(6),
    BACK(0);

    private final int value;

    GroceryCommand(int value) {
      this.value = value;
    }

    /**
     * Returns the command that corresponds to the given value.
     *
     * @param value The value of the given command.
     * @return The corresponding command.
     * @throws IllegalArgumentException if the value does not correspond to any command.
     * @since 0.0.1
     */
    private static GroceryCommand fromValue(int value) {
      for (GroceryCommand command : GroceryCommand.values()) {
        if (command.value == value) {
          return command;
        }
      }
      throw new IllegalArgumentException("The command value is invalid" + value);
    }
  }

  /**
   * Displays the Grocery Menu and handles the input for the grocery commands.
   *
   * @since 0.0.1
   */
  public void groceryMenu() {
    GroceryCommand command = null;

    do {
      stringMenu.printGroceryMenu();
      int commandValue;
      try {
        commandValue = inputHandler.intReader("Enter your command: ");
        command = GroceryCommand.fromValue(commandValue);
        commandHandler(command);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    } while (command != GroceryCommand.BACK);
  }

  /**
   * Handles the execution of the given command.
   * Will execute an action according to the given command.
   *
   * @param command The command to execute.
   * @since 0.0.1
   */
  private void commandHandler(GroceryCommand command) {
    switch (command) {
      case GroceryCommand.CREATE_NEW_GROCERY ->
          groceryMutator.createGrocery(ERRORMESSAGE, foodStorage);
      case GroceryCommand.REMOVE_GROCERY ->
          groceryMutator.removeGrocery(ERRORMESSAGE, foodStorage);
      case GroceryCommand.SEARCH_FOR_GROCERY ->
          groceryPrinter.searchForGrocery(foodStorage, ERRORMESSAGE);
      case GroceryCommand.LIST_OF_ALL_GROCERIES ->
          groceryPrinter.listOfAllGroceries(ERRORMESSAGE, foodStorage);
      case GroceryCommand.LIST_OF_EXPIRED_GROCERIES ->
          groceryPrinter.listOfExpiredGroceries(ERRORMESSAGE, foodStorage);
      case GroceryCommand.VALUE_OF_ALL_GROCERIES ->
          groceryPrinter.valueOfAllGroceries(ERRORMESSAGE, foodStorage);
      case GroceryCommand.BACK -> System.out.println("Going back to main menu.\n");
      default -> System.out.println("Invalid command.\n");
    }
  }
}