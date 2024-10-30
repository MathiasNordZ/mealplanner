package edu.ntnu.idi.bidata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a storage for groceries.
 */
public class FoodStorage {
  private final Map<String, List<Grocery>> groceries = new HashMap<>();

  public FoodStorage() {
    // Currently empty
  }

  /**
   * Add method to add grocery to HashMap.
   *
   * @param grocery Instance of a Grocery.
   */
  public void addGrocery(Grocery grocery) {
    List<Grocery> groceryList = groceries.getOrDefault(grocery.getName(), new ArrayList<>());

    boolean isFound = false;
    int i = 0;
    while (i < groceryList.size() && !isFound) {
      Grocery g = groceryList.get(i);

      if (g.getExpirationDate().equals(grocery.getExpirationDate())) {
        g.setQuantity(g.getQuantity() + grocery.getQuantity());

        isFound = true;
      }
      i++;
    }

    if (!isFound) {
      groceryList.add(grocery);
    }
    groceries.put(grocery.getName(), groceryList);
  }

  /**
   * Remove method to remove grocery from HashMap
   *
   * @param grocery Instance of a Grocery.
   */
  public void removeGrocery(Grocery grocery, float quantityToRemove) {
    List<Grocery> groceryList = groceries.get(grocery.getName());

    if (groceryList != null) {
      int i = 0;
      boolean isFound = false;
      while (i < groceryList.size() && !isFound) {
        Grocery g = groceryList.get(i);

        if (g.getExpirationDate().equals(grocery.getExpirationDate())) {
          float updatedQuantity = g.getQuantity() - quantityToRemove;
          if (updatedQuantity <= 0) {
            groceryList.remove(g);
          } else {
            g.setQuantity(updatedQuantity);
          }
          isFound = true;
        }
        i++;
      }

      if (groceryList.isEmpty()) {
        groceries.remove(grocery.getName());
      }
    }
  }

  public Grocery searchGrocery(String name, LocalDate expirationDate) {
    List<Grocery> groceryList = groceries.get(name);

    if (groceryList != null) {
      for (Grocery g : groceryList) {
        if (g.getExpirationDate().equals(expirationDate)) {
          return g;
        }
      }
    }
    return null;
  }

  public float valueOfExpiredGroceries() {
    float totalValue = 0;
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        if (grocery.getExpirationDate().isBefore(LocalDate.now())) {
          totalValue += grocery.getPrice();
        }
      }
    }
    return totalValue;
  }

  public float valueOfAllGroceries() {
    float totalValue = 0;
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        totalValue += grocery.getPrice();
      }
    }
    return totalValue;
  }
}