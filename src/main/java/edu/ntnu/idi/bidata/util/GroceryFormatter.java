package edu.ntnu.idi.bidata.util;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.util.List;
import java.util.NoSuchElementException;

public class GroceryFormatter {
  public GroceryFormatter() {

  }

  /**
   * This method is the template of how a grocery should look when printed.
   *
   * @param groceries The groceries to add to table.
   * @return Will return a table according to the given format.
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
          grocery.getQuantity(), grocery.getUnitOfMeasurement(),
          grocery.getPrice(), grocery.getExpirationDate()));
    }
    return table.toString();
  }

  /**
   * Validation method for <code>formatGroceries</code>.
   * Will validate that the provided Set is not <code>null</code> or empty.
   * Refactored to a separate method to be less complex and more modular.
   *
   * @param groceries Grocery list to validate.
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
   * This method will format the <code>getSortedList</code>,
   * according to the <code>formatGroceries</code> template.
   *
   * @param foodStorage The foodStorage to check.
   * @return Will a sorted list, formatted as a table
   * @since 0.0.1
   */
  public static String formatSortedGroceries(FoodStorage foodStorage) {
    return formatGroceries(foodStorage.getSortedList());
  }

  /**
   * This method will format the <code>listOfExpiredGroceries</code>,
   * according to the <code>formatGroceries</code> template.
   *
   * @param foodStorage The foodStorage to check.
   * @param providedExpiryDate Expiry date to sort by.
   * @return Will return list of expired groceries, formatted as table.
   * @since 0.0.1
   */
  public static String formatExpiredGroceries(FoodStorage foodStorage, String providedExpiryDate) {
    return formatGroceries(foodStorage.listOfExpiredGroceries(providedExpiryDate));
  }

  /**
   * This method will format <code>searchGrocery</code>,
   * according to the <code>formatGroceries</code> template.
   *
   * @param foodStorage The foodStorage to check.
   * @param groceryToSearch The grocery to search for.
   * @return Will return the grocery in a formatted table.
   * @since 0.0.1
   */
  public static String formatGrocery(FoodStorage foodStorage, String groceryToSearch) {
    return formatGroceries(foodStorage.searchGrocery(groceryToSearch));
  }
}
