package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

public class StringFormatter {

  public static String formatGroceries(FoodStorage foodStorage) {
    StringBuilder table = new StringBuilder();
    String format = "%-20s %-10s %15-s %-15s\n";
    table.append(String.format(format, "Name", "Quantity", "Unit", "Price", "Expiry Date"));
    table.append("----------------------------------------------------------------------");
    for (Grocery grocery : foodStorage.getSortedList()) {
      table.append(String.format(format, grocery.getName(), grocery.getQuantity(), grocery.getUnitOfMeasurement(), grocery.getPrice(), grocery.getExpirationDate()));
    }
    return table.toString();
  }
}
