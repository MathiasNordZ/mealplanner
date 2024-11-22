package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.NoSuchElementException;

/**
 * The <code>UserInterfaceMenu</code> class handles the user interface for the application.
 * The class displays menus and handles user interaction, such as creating, removing,
 * searching and listing up groceries.
 *
 * @author Mathias Erik Nord
 * @version 0.0.0
 */
public class UserInterfaceMenu {
  private FoodStorage foodStorage = new FoodStorage();
  private UserInputHandler uiInputHandler = new UserInputHandler();
  private final String cookBookCommand = """
      [1] - Create recipe.
      [2] - Remove recipe.
      [3] - Print recipes.
      [4] - Print recipe.
      [5] - Recipe recommendation.
      [0] - Go back.
      """;

  /**
   * This is an enum that does represent the commands of the main menu.
   */
  private enum MainCommands {
    GROCERY_MENU(1),
    COOKBOOK_MENU(2),
    EXIT(0);

    private final int value;
    MainCommands(int value) {
      this.value = value;
    }

    private static MainCommands fromValue(int value) {
      for (MainCommands command : MainCommands.values()) {
        if (command.value == value) {
          return command;
        }
      }
      throw new IllegalArgumentException("The command value is invalid" + value);
    }
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
   * This method will display the main menu and handle the input of the main menu.
   */
  public void mainMenu() {
    MainCommands command = null;

    do {
      System.out.println("""
          [1] - Grocery Menu.
          [2] - Cookbook Menu.
          [0] - Exit.
          """);
      int commandValue = uiInputHandler.intReader("Enter command: ");

      try {
        command = MainCommands.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        case MainCommands.GROCERY_MENU -> groceryMenu();
        //case MainCommands.COOKBOOK_MENU -> cookBookMenu();
        default -> System.out.println("Invalid command.");
      }

    } while (command != MainCommands.EXIT);
  }

  /**
   * This method does display the Grocery Menu and handles the input for the grocery commands.
   */
  private void groceryMenu() {
    GroceryCommand command = null;

    do {
      System.out.println("""
          [1] - Create a new grocery.
          [2] - Remove grocery.
          [3] - Search for grocery.
          [4] - List of all groceries.
          [5] - List of expired groceries.
          [0] - Go Back.
          """);
      int commandValue = uiInputHandler.intReader("Enter your command: ");

      try {
        command = GroceryCommand.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        case GroceryCommand.CREATE_NEW_GROCERY -> createGrocery();
        case GroceryCommand.REMOVE_GROCERY -> removeGrocery();
        case GroceryCommand.SEARCH_FOR_GROCERY -> searchForGrocery();
        case GroceryCommand.LIST_OF_ALL_GROCERIES -> listOfAllGroceries();
        case GroceryCommand.LIST_OF_EXPIRED_GROCERIES -> listOfExpiredGroceries();
        case GroceryCommand.BACK -> System.out.println("Exiting");
        default -> System.out.println("Invalid command.");
      }
    } while (command != GroceryCommand.BACK);
  }

  /**
   * This method will prompt the user to enter details, to create a new grocery.
   * If the grocery is created successfully, it will be added to the food storage.
   */
  private void createGrocery() {
    String nameOfGrocery = uiInputHandler.stringReader("Please enter name of grocery: ");
    float quantityOfGrocery = uiInputHandler.floatReader("Please enter quantity of grocery: ");
    String unitOfMeasurement = uiInputHandler.stringReader("Please enter unit of measurement (kilogram/liter): ");
    float priceOfGrocery = uiInputHandler.floatReader("Please enter price of grocery: ");
    String dateOfExpiry = uiInputHandler.stringReader("Please enter expiry date (YYYY-MM-DD): ");

    try {
      Grocery grocery = new Grocery(quantityOfGrocery, nameOfGrocery, unitOfMeasurement, priceOfGrocery, dateOfExpiry);
      foodStorage.addGrocery(grocery);
      System.out.println("Grocery was created successfully, and added to storage.");
    } catch (IllegalArgumentException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }

  /**
   * This method will prompt the user for which grocery the user wants to remove, and how much quantity to remove.
   * If the grocery does exist, and the quantity to remove is valid. It will be removed from the food storage.
   */
  private void removeGrocery () {
    String groceryToRemove = uiInputHandler.stringReader("Name of grocery to remove: ");
    int quantityToRemove = uiInputHandler.intReader("Quantity to remove: ");

    try {
      foodStorage.removeGrocery(groceryToRemove, quantityToRemove);
      System.out.println("Grocery was removed successfully!");
    } catch (IllegalArgumentException e) {
      System.out.println("An error occured: " + e.getMessage());
    } catch (NoSuchElementException e) {
      System.out.println("An error occured " + e.getMessage());
    }
  }

  /**
   * This method will prompt the user for a grocery they want to search for.
   * If the grocery does exist, it will be searched for.
   */
  private void searchForGrocery() {
    String groceryToSearch = uiInputHandler.stringReader("Please enter grocery to search for: ");

    try {
      foodStorage.searchGrocery(groceryToSearch);
      System.out.println("Grocery was found: " + groceryToSearch);
    } catch (NoSuchElementException e) {
      System.out.println("No such grocery was found!" + e.getMessage());
    }
  }

  private void listOfAllGroceries() {
    try {
      System.out.println(foodStorage.getSortedList());
      System.out.println("List was printed successfully!");
    } catch (NoSuchElementException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }

  /**
   * This method will prompt the user for a date.
   * The method will then search for the groceries that expire before the given date.
   */
  private void listOfExpiredGroceries() {
    try {
      String dateOfExpiry = uiInputHandler.stringReader("Please enter date to check which groceries expires before given date: ");
      System.out.println(foodStorage.listOfExpiredGroceries(dateOfExpiry));
    } catch (IllegalArgumentException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }
}
