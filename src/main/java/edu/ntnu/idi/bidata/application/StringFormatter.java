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

  public static String formatSortedGroceries(FoodStorage foodStorage) {
    return formatGroceries(foodStorage.getSortedList());
  }

  public static String formatExpiredGroceries(FoodStorage foodStorage, String providedExpiryDate) {
    return formatGroceries(foodStorage.listOfExpiredGroceries(providedExpiryDate));
  }

  public static String formatGrocery(FoodStorage foodStorage, String groceryToSearch) {
    return formatGroceries(foodStorage.searchGrocery(groceryToSearch));
  }
}
