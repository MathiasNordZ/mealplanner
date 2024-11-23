package edu.ntnu.idi.bidata.menu;

import edu.ntnu.idi.bidata.application.StringFormatter;
import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.util.NoSuchElementException;

public class GroceryMenu {
  UserInputHandler uiInputHandler;
  FoodStorage foodStorage;

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
    float quantityOfGrocery = uiInputHandler
        .floatReader("Please enter quantity of grocery: ");
    String unitOfMeasurement = uiInputHandler
        .stringReader("Please enter unit of measurement (kilogram/liter): ");
    float priceOfGrocery = uiInputHandler.floatReader("Please enter price of grocery: ");
    String dateOfExpiry = uiInputHandler.stringReader("Please enter expiry date (YYYY-MM-DD): ");

    try {
      Grocery grocery = new Grocery(quantityOfGrocery,
          nameOfGrocery, unitOfMeasurement, priceOfGrocery, dateOfExpiry);
      foodStorage.addGrocery(grocery);
      System.out.println("Grocery was created successfully, and added to storage.");
    } catch (IllegalArgumentException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }

  /**
   * This method will prompt the user for which grocery the user wants to remove,
   * and how much quantity to remove.
   * If the grocery does exist, and the quantity to remove is valid.
   * It will be removed from the food storage.
   */
  private void removeGrocery() {
    String groceryToRemove = uiInputHandler.stringReader("Name of grocery to remove: ");
    float quantityToRemove = uiInputHandler.floatReader("Quantity to remove: ");

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
      String formattedGrocery = StringFormatter.formatGrocery(foodStorage, groceryToSearch);
      System.out.println(formattedGrocery);
    } catch (NoSuchElementException e) {
      System.out.println("No such grocery was found!" + e.getMessage());
    }
  }

  private void listOfAllGroceries() {
    try {
      String formattedGroceries = StringFormatter.formatSortedGroceries(foodStorage);
      System.out.println(formattedGroceries);
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
      String dateOfExpiry = uiInputHandler
          .stringReader("Please enter date to check which groceries expires before given date: ");
      String formattedExipredGroceries = StringFormatter.formatExpiredGroceries(foodStorage, dateOfExpiry);
      System.out.println(formattedExipredGroceries);
    } catch (IllegalArgumentException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }
}
