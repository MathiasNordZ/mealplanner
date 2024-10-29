package edu.ntnu.idi.bidata;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a storage for groceries.
 */
public class FoodStorage {
  private final Map<String, Grocery> groceries = new HashMap<>();

  public FoodStorage() {
    // Currently empty
  }

  /**
   * Add method to add grocery to HashMap.
   *
   * @param grocery Instance of a Grocery.
   */
  public void addGrocery(Grocery grocery) {
    groceries.put(grocery.getName(), grocery);
  }

  /**
   * Remove method to remove grocery from HashMap
   *
   * @param grocery Instance of a Grocery.
   */
  public void removeGrocery(Grocery grocery) {
    groceries.remove(grocery.getName(), grocery);
  }

  public Grocery searchGrocery(Grocery grocery) {
    return groceries.get(grocery.getName());
  }

  public float valueOfExpiredGroceries() {
    float totalValue = 0;
    for (Map.Entry<String, Grocery> entry : groceries.entrySet()) {
      Grocery value = entry.getValue();

      if (value.getExpirationDate().isBefore(LocalDate.now())) {
        totalValue += value.getPrice();
      }
    }
    return totalValue;
  }

  public float valueOfAllGroceries() {
    float totalValue = 0;
    for (Map.Entry<String, Grocery> entry : groceries.entrySet()) {
      Grocery value = entry.getValue();

      totalValue += value.getPrice();
    }
    return totalValue;
  }
}