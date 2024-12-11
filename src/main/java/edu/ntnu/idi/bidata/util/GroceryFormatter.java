package edu.ntnu.idi.bidata.util;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Utility class for formatting groceries.
 * Provides methods to format a list of groceries into a readable table form.
 *
 * @author Mathias Erik Nord
 * @since 23.11.2024
 * @version 1.0.0
 */
public class GroceryFormatter {
  private GroceryFormatter() {
    // Private constructor to avoid instantiation.
  }

  /**
   * Formats a list of groceries into a table format.
   *
   * @param groceries The groceries to add to table.
   * @return A table according to the given format.
   * @throws IllegalArgumentException if the grocery list is null
   * @throws NoSuchElementException if the grocery list is empty
   * @since 0.0.1
   */
  public static String formatGroceries(List<Grocery> groceries) {
    groceryValidation(groceries);
    StringBuilder table = new StringBuilder();
    String format = "| %-20s | %-10s | %-10s | %-15s | %-15s |\n";
    table.append(String.format(format, "Name", "Quantity", "Unit", "Price", "Expiry Date"));
    table.append("----------------------------------------"
        + "----------------------------------------------\n");

    for (Grocery grocery : groceries) {
      table.append(String.format(format, grocery.getName(),
          grocery.getQuantity(), grocery.getUnit(),
          grocery.getPrice(), grocery.getExpiryDate()));
    }
    return table.toString();
  }

  /**
   * Validates the provided list of groceries.
   * Checks if the list is not null or empty.
   *
   * @param groceries The grocery list to validate.
   * @throws IllegalArgumentException if the grocery list is null
   * @throws NoSuchElementException if the grocery list is empty
   * @since 0.0.1
   */
  private static void groceryValidation(List<Grocery> groceries) {
    if (groceries == null || groceries.isEmpty()) {
      if (groceries == null) {
        throw new IllegalArgumentException("Grocery list cannot be null!");
      } else {
        throw new NoSuchElementException("List has no elements!");
      }
    }
  }

  /**
   * Formats the sorted list of groceries from the given food storage.
   *
   * @param foodStorage The foodStorage to check.
   * @return A formatted string of the sorted groceries.
   * @since 0.0.1
   */
  public static String formatSortedGroceries(FoodStorage foodStorage) {
    return formatGroceries(foodStorage.getSortedList());
  }

  /**
   * Formats the list of expired groceries from the given food storage.
   *
   * @param foodStorage The foodStorage to check.
   * @param providedExpiryDate The expiry date to sort by.
   * @return A list of expired groceries, formatted as table.
   * @since 0.0.1
   */
  public static String formatExpiredGroceries(FoodStorage foodStorage, String providedExpiryDate) {
    return formatGroceries(foodStorage.listOfExpiredGroceries(providedExpiryDate));
  }

  /**
   * Formats the search result of a grocery.
   *
   * @param foodStorage The foodStorage to check.
   * @param groceryToSearch The grocery to search for.
   * @return The grocery in a formatted table.
   * @since 0.0.1
   */
  public static String formatGrocery(FoodStorage foodStorage, String groceryToSearch) {
    return formatGroceries(foodStorage.searchGrocery(groceryToSearch));
  }
}
