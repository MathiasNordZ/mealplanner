package edu.ntnu.idi.bidata.menu.grocery;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.util.GroceryFormatter;
import edu.ntnu.idi.bidata.util.StringFormatter;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class provides methods to print information about groceries in the food storage.
 *
 * @version 0.0.1
 * @since 28.11.2024
 */
public class GroceryMenuPrinter {
  private final UserInputHandler uiInputHandler;

  /**
   * Constructs a new instance of <code>GroceryMenuPrinter</code>.
   *
   * @since 0.0.1
   */
  public GroceryMenuPrinter(UserInputHandler uiInputHandler) {
    this.uiInputHandler = uiInputHandler;
  }

  /**
   * Prompts the user for a grocery to search for and prints the details if found.
   *
   * @param foodStorage The food storage to search in.
   * @since 0.0.1
   */
  public void searchForGrocery(FoodStorage foodStorage, String errorMessage) {
    System.out.println("Available groceries to search for: ");
    foodStorage.getSortedList().forEach(grocery -> System.out.println(grocery.getName()));
    System.out.println();

    String groceryToSearch = uiInputHandler.stringReader("Please enter grocery to search for: ");
    try {
      String formattedGrocery = GroceryFormatter.formatGrocery(foodStorage, groceryToSearch);
      System.out.println(formattedGrocery);
    } catch (NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Prints a list of all groceries in the food storage.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param foodStorage The food storage to list the groceries from.
   * @since 0.0.1
   */
  public void listOfAllGroceries(String errorMessage, FoodStorage foodStorage) {
    try {
      String formattedGroceries = GroceryFormatter.formatSortedGroceries(foodStorage);
      System.out.println(formattedGroceries);
    } catch (NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Prompts the user for a date and prints the groceries that expires before the given date.
   * Will also print the total value of the expired groceries.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param foodStorage The food storage to list the expired groceries from.
   * @since 0.0.1
   */
  public void listOfExpiredGroceries(String errorMessage, FoodStorage foodStorage) {
    try {
      String dateOfExpiry = uiInputHandler
          .dateReader("Please enter date to check which groceries "
              + "expires before given date ('YYYY-MM-DD'): ");
      List<Grocery> expiredGroceries = foodStorage.listOfExpiredGroceries(dateOfExpiry);
      String formattedExpiredGroceries = GroceryFormatter
          .formatExpiredGroceries(foodStorage, dateOfExpiry);
      System.out.println(formattedExpiredGroceries);
      System.out.println(StringFormatter.GREEN + "Value of expired groceries: "
          + StringFormatter.RESET
          + foodStorage.valueOfExpiredGroceries(expiredGroceries) + "\n");
    } catch (IllegalArgumentException | NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Prints the total value of all groceries in the food storage.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param foodStorage The food storage to print the value from.
   * @since 0.0.1
   */
  public void valueOfAllGroceries(String errorMessage, FoodStorage foodStorage) {
    try {
      System.out.println(StringFormatter.GREEN + "Value of all groceries: "
          + StringFormatter.RESET + foodStorage.valueOfAllGroceries());
    } catch (Exception e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }
}
