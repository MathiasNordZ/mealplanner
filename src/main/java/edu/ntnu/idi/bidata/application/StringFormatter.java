package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.util.List;

/**
 * @author Mathias Erik Nord
 * @version 0.0.1
 */
public class StringFormatter {
  private StringFormatter() {
  }

  /**
   *
   * @param groceries The groceries to add to table.
   * @return Will return a table according to the given format.
   * @since 0.0.1
   */
  public static String formatGroceries(List<Grocery> groceries) {
    StringBuilder table = new StringBuilder();
    String format = "%-20s %-10s %-10s %-15s %-15s\n";
    table.append(String.format(format, "Name", "Quantity", "Unit", "Price", "Expiry Date"));
    table.append("----------------------------------------------------------------------\n");

    for (Grocery grocery : groceries) {
      table.append(String.format(format, grocery.getName(), grocery.getQuantity(), grocery.getUnitOfMeasurement(), grocery.getPrice(), grocery.getExpirationDate()));
    }
    return table.toString();
  }

  /**
   *
   * @param foodStorage The foodStorage to check.
   * @return Will a sorted list, formatted as a table
   * @since 0.0.1
   */
  public static String formatSortedGroceries(FoodStorage foodStorage) {
    return formatGroceries(foodStorage.getSortedList());
  }

  /**
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
