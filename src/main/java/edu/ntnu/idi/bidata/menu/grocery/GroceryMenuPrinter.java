package edu.ntnu.idi.bidata.menu.grocery;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.util.GroceryFormatter;
import edu.ntnu.idi.bidata.application.UserInputHandler;
import java.util.List;
import java.util.NoSuchElementException;

public class GroceryMenuPrinter {
  private final UserInputHandler uiInputHandler = new UserInputHandler();

  public GroceryMenuPrinter() {

  }

  /**
   * This method will prompt the user for a grocery they want to search for.
   * If the grocery does exist, it will be searched for.
   */
  public void searchForGrocery(FoodStorage foodStorage) {
    String groceryToSearch = uiInputHandler.stringReader("Please enter grocery to search for: ");

    try {
      String formattedGrocery = GroceryFormatter.formatGrocery(foodStorage, groceryToSearch);
      System.out.println(formattedGrocery);
    } catch (NoSuchElementException e) {
      System.out.println("No such grocery was found!" + e.getMessage());
    }
  }

  /**
   * This method will print a list of all groceries.
   */
  public void listOfAllGroceries(String errorMessage, FoodStorage foodStorage) {
    try {
      String formattedGroceries = GroceryFormatter.formatSortedGroceries(foodStorage);
      System.out.println(formattedGroceries);
      System.out.println("List was printed successfully!");
    } catch (NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * This method will prompt the user for a date.
   * The method will then search for the groceries that expire before the given date.
   * The bottom of the list will print the total value of the expired groceries.
   */
  public void listOfExpiredGroceries(String errorMessage, FoodStorage foodStorage) {
    try {
      String dateOfExpiry = uiInputHandler
          .stringReader("Please enter date to check which groceries expires before given date: ");
      List<Grocery> expiredGroceries = foodStorage.listOfExpiredGroceries(dateOfExpiry);
      String formattedExpiredGroceries = GroceryFormatter
          .formatExpiredGroceries(foodStorage, dateOfExpiry);
      System.out.println(formattedExpiredGroceries);
      System.out.println("Value of expired groceries: " + foodStorage.valueOfExpiredGroceries(expiredGroceries) + "\n");
    } catch (IllegalArgumentException | NoSuchElementException e ) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * This method will print the value of all groceries.
   */
  public void valueOfAllGroceries(String errorMessage, FoodStorage foodStorage) {
    try {
      System.out.println("Value of all groceries: " + foodStorage.valueOfAllGroceries());
    } catch (Exception e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }
}
