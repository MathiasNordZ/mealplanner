package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.NoSuchElementException;

public class UserInterfaceMenu {
  private FoodStorage foodStorage = new FoodStorage();
  private UserInterfaceHandler uiHandler = new UserInterfaceHandler();
  private final String cookBookCommand = """
      [1] - Create recipe.
      [2] - Remove recipe.
      [3] - Print recipes.
      [4] - Print recipe.
      [5] - Recipe recommendation.
      [0] - Go back.
      """;

  private enum MainCommands {
    GROCERY_MENU(1),
    COOKBOOK_MENU(2),
    EXIT(0);

    private final int value;
    MainCommands(int value) {
      this.value = value;
    }

    private int getValue() {
      return value;
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

    private int getValue() {
      return value;
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

  public void mainMenu() {
    MainCommands command = null;

    do {
      uiHandler.print("""
          [1] - Grocery Menu.
          [2] - Cookbook Menu.
          [0] - Exit.
          """);
      int commandValue = uiHandler.intReader("Enter command: ");

      try {
        command = MainCommands.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        uiHandler.print("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        case MainCommands.GROCERY_MENU -> groceryMenu();
        //case MainCommands.COOKBOOK_MENU -> cookBookMenu();
        default -> uiHandler.print("Invalid command.");
      }

    } while (command != MainCommands.EXIT);
  }

  private void groceryMenu() {
    GroceryCommand command = null;

    do {
      uiHandler.print("""
          [1] - Create a new grocery.
          [2] - Remove grocery.
          [3] - Search for grocery.
          [4] - List of all groceries.
          [5] - List of expired groceries.
          [0] - Go Back.
          """);
      int commandValue = uiHandler.intReader("Enter your command: ");

      try {
        command = GroceryCommand.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        uiHandler.print("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        case GroceryCommand.CREATE_NEW_GROCERY -> createGrocery();
        case GroceryCommand.REMOVE_GROCERY -> removeGrocery();
        case GroceryCommand.SEARCH_FOR_GROCERY -> searchForGrocery();
        case GroceryCommand.LIST_OF_ALL_GROCERIES -> listOfAllGroceries();
        case GroceryCommand.LIST_OF_EXPIRED_GROCERIES -> listOfExpiredGroceries();
        case GroceryCommand.BACK -> System.out.println("Exiting");
        default -> uiHandler.print("Invalid command.");
      }
    } while (command != GroceryCommand.BACK);
  }

  private void createGrocery() {
    String nameOfGrocery = uiHandler.stringReader("Please enter name of grocery: ");
    float quantityOfGrocery = uiHandler.floatReader("Please enter quantity of grocery: ");
    String unitOfMeasurement = uiHandler.stringReader("Please enter unit of measurement: ");
    float priceOfGrocery = uiHandler.floatReader("Please enter price of grocery: ");
    String dateOfExpiry = uiHandler.stringReader("Please enter expiry date (YYYY-MM-DD): ");

    try {
      Grocery grocery = new Grocery(quantityOfGrocery, nameOfGrocery, unitOfMeasurement, priceOfGrocery, dateOfExpiry);
      foodStorage.addGrocery(grocery);
      uiHandler.print("Grocery was created successfully, and added to storage.");
    } catch (IllegalArgumentException e) {
      uiHandler.print("An error occured: " + e.getMessage());
    }
  }

  private void removeGrocery () {
    String groceryToRemove = uiHandler.stringReader("Name of grocery to remove: ");
    int quantityToRemove = uiHandler.intReader("Quantity to remove: ");

    try {
      foodStorage.removeGrocery(groceryToRemove, quantityToRemove);
      uiHandler.print("Grocery was removed successfully!");
    } catch (IllegalArgumentException e) {
      uiHandler.print("An error occured: " + e.getMessage());
    } catch (NoSuchElementException e) {
      uiHandler.print("An error occured " + e.getMessage());
    }
  }

  private void searchForGrocery() {
    String groceryToSearch = uiHandler.stringReader("Please enter grocery to search for: ");

    try {
      foodStorage.searchGrocery(groceryToSearch);
      uiHandler.print("Grocery was found: " + groceryToSearch);
    } catch (NoSuchElementException e) {
      uiHandler.print("No such grocery was found!" + e.getMessage());
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

  private void listOfExpiredGroceries() {
    try {
      String dateOfExpiry = uiHandler.stringReader("Please enter date to check which groceries expires before given date: ");
      foodStorage.listOfExpiredGroceries(dateOfExpiry);
    } catch (IllegalArgumentException e) {
      uiHandler.print("An error occured: " + e.getMessage());
    }
  }
}
