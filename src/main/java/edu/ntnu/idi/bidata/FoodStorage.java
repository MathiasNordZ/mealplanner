package edu.ntnu.idi.bidata;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents a storage where instances of Grocery can be stored.
 * The storage can represent a fridge, drawer, freezer etc.
 *
 * @author Mathias Erik Nord
 * @since 30.10.2024
 */
public class FoodStorage {
  private final Map<String, List<Grocery>> groceries = new HashMap<>();

  public FoodStorage() {
    // Currently empty
  }

  /**
   * Mutator method to add an instance of Grocery to FoodStorage.
   *
   * @param providedGrocery Represents the instance of a Grocery.
   */
  public void addGrocery(Grocery providedGrocery) {
    List<Grocery> groceryList = groceries.getOrDefault(providedGrocery.getName(), new ArrayList<>());
    Iterator<Grocery> groceryIterator = groceryList.iterator();

    boolean isFound = false;
    while (groceryIterator.hasNext() && !isFound) {
      Grocery grocery = groceryIterator.next();

      if (grocery.getExpirationDate().equals(providedGrocery.getExpirationDate())) {
        grocery.setQuantity(grocery.getQuantity() + providedGrocery.getQuantity());

        isFound = true;
      }
    }

    if (!isFound) {
      groceryList.add(providedGrocery);
    }
    groceries.put(providedGrocery.getName(), groceryList);
  }

  /**
   * Mutator method to remove an instance of Grocery from FoodStorage.
   *
   * @param providedGrocery Represents the instance of a Grocery.
   */
  public void removeGrocery(Grocery providedGrocery, float quantityToRemove) {
    List<Grocery> groceryList = groceries.get(providedGrocery.getName());
    Iterator<Grocery> groceryIterator = groceryList.iterator();

    boolean isFound = false;
    while (groceryIterator.hasNext() && !isFound) {
      Grocery grocery = groceryIterator.next();

      if (grocery.getExpirationDate().equals(providedGrocery.getExpirationDate())) {
        float updatedQuantity = grocery.getQuantity() - quantityToRemove;
        if (updatedQuantity <= 0) {
          groceryList.remove(grocery);
        } else {
          grocery.setQuantity(updatedQuantity);
        }
        isFound = true;
      }
    }
    if (groceryList.isEmpty()) {
      groceries.remove(providedGrocery.getName());
    }
  }

  /**
   * Accessor method to search for an instance of Grocery in FoodStorage.
   * @param name Represents the name of the grocery to search for.
   * @return Will return the instance that is searched for.
   */
  public Grocery searchGrocery(String name) {
    List<Grocery> groceryList = groceries.get(name);

    if (groceryList != null) {
      for (Grocery grocery : groceryList) {
        if (grocery.getName().equals(name)) {
          return grocery;
        }
      }
    }
    return null;
  }

  /**
   * Accessor method to access the value of expired groceries.
   * @return Will return the total value of expired groceries.
   */
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

  /**
   * Accessor method to access the value of all groceries.
   * @return Will return the total value of all groceries.
   */
  public float valueOfAllGroceries() {
    float totalValue = 0;
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        totalValue += grocery.getPrice();
      }
    }
    return totalValue;
  }

  public List<Grocery> getSortedList() {

    return groceries.values().stream().flatMap(List::stream).sorted().collect(Collectors.toList());
  }
}