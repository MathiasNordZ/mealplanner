package edu.ntnu.idi.bidata.menu.grocery;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.menu.StringMenu;
import edu.ntnu.idi.bidata.register.FoodStorage;

/**
 * This class represents the grocery menu in the application.
 * In the grocery menu, the user can manage groceries.
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 * @since <b>22.11.2024</b>
 */
public class GroceryMenu {
  private final UserInputHandler uiInputHandler;
  private final FoodStorage foodStorage;
  private static final String ERRORMESSAGE = "An error occurred: ";
  private final StringMenu stringMenu = new StringMenu();
  private final GrocerMenuMutator groceryMutator = new GrocerMenuMutator();
  private final GroceryMenuPrinter groceryPrinter = new GroceryMenuPrinter();

  /**
   * Constructor for the GroceryMenu class.
   *
   * @param foodStorage The food storage that contains the groceries.
   * @param uiInputHandler The user input handler.
   */
  public GroceryMenu(FoodStorage foodStorage, UserInputHandler uiInputHandler) {
    this.foodStorage = foodStorage;
    this.uiInputHandler = uiInputHandler;
  }

  /**
   * Inspired by world of zuul.
   * This is an enum that does represent the commands of the Grocery Menu.
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
   * This method does display the Grocery Menu and handles the input for the grocery commands.
   */
  public void groceryMenu() {
    GroceryCommand command = null;

    do {
        stringMenu.printGroceryMenu();
      int commandValue = uiInputHandler.intReader("Enter your command: ");

      try {
        command = GroceryCommand.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        case GroceryCommand.CREATE_NEW_GROCERY -> groceryMutator.createGrocery(ERRORMESSAGE, foodStorage);
        case GroceryCommand.REMOVE_GROCERY -> groceryMutator.removeGrocery(ERRORMESSAGE, foodStorage);
        case GroceryCommand.SEARCH_FOR_GROCERY -> groceryPrinter.searchForGrocery(foodStorage);
        case GroceryCommand.LIST_OF_ALL_GROCERIES -> groceryPrinter.listOfAllGroceries(ERRORMESSAGE, foodStorage);
        case GroceryCommand.LIST_OF_EXPIRED_GROCERIES -> groceryPrinter.listOfExpiredGroceries(ERRORMESSAGE, foodStorage);
        case GroceryCommand.VALUE_OF_ALL_GROCERIES -> groceryPrinter.valueOfAllGroceries(ERRORMESSAGE, foodStorage);
        case GroceryCommand.BACK -> System.out.println("Exiting");
        default -> System.out.println("Invalid command.");
      }
    } while (command != GroceryCommand.BACK);
  }
}