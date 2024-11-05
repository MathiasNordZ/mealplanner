package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;

import java.time.LocalDate;
import java.util.*;

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
    String errorMessage;
    if (providedGrocery == null) {
      errorMessage = "The provided grocery cannot be null.";
      throw new IllegalArgumentException(errorMessage);
    }

    List<Grocery> groceryList = groceries.getOrDefault(providedGrocery.getName(), new ArrayList<>());
    Iterator<Grocery> groceryIterator = groceryList.iterator();

    boolean isFound = false;
    while (groceryIterator.hasNext() && !isFound) {
      Grocery grocery = groceryIterator.next();

      if (grocery.getExpirationDate().equals(providedGrocery.getExpirationDate())) {
        grocery.setQuantity(grocery.getQuantity() + providedGrocery.getQuantity());
        grocery.setPrice(grocery.getPrice() + providedGrocery.getPrice());

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
    String errorMessage;
    if (providedGrocery == null || quantityToRemove <= 0) {
      if (providedGrocery == null) {
        errorMessage = "The provided grocery cannot be null.";
      } else {
        errorMessage = "The quantity to remove cannot be less than or equal to zero.";
      }
      throw new IllegalArgumentException(errorMessage);
    }

    List<Grocery> groceryList = groceries.get(providedGrocery.getName());
    Iterator<Grocery> groceryIterator = groceryList.iterator();

    boolean isFound = false;
    while (groceryIterator.hasNext() && !isFound) {
      Grocery grocery = groceryIterator.next();

      if (grocery.getExpirationDate().equals(providedGrocery.getExpirationDate())) {
        float updatedQuantity = grocery.getQuantity() - quantityToRemove;
        float updatedPrice = grocery.getPrice() * (updatedQuantity / grocery.getQuantity());
        if (updatedQuantity <= 0) {
          groceryList.remove(grocery);
        } else {
          grocery.setQuantity(updatedQuantity);
          grocery.setPrice(updatedPrice);
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
   * If there are two instances with different expiry dates, it will be returned as two instances in a list.
   * If the two instances has the same expiry date, they will be combined.
   * @param name Represents the name of the grocery to search for.
   * @return Will return the instance(s) that is searched for.
   */
  public List<Grocery> searchGrocery(String name) {
    List<Grocery> searchedGrocery = new ArrayList<>();
    for(List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        if (grocery.getName().equalsIgnoreCase(name)) {
          searchedGrocery.add(grocery);
        }
      }
    }
    return searchedGrocery;
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
   * Accessor method for expired groceries.
   * Will return a list of groceries that expires before given date.
   * @param year Year of expiration.
   * @param month Month of expiration.
   * @param day Day of expiration.
   * @return Will return a list of expired groceries.
   */
  public List<Grocery> listOfExpiredGroceries(int year, int month, int day) {
    List<Grocery> expiredGroceries = new ArrayList<>();
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        if (grocery.getExpirationDate().isBefore(LocalDate.of(year, month, day))) {
          expiredGroceries.add(grocery);
        }
      }
    }
    return expiredGroceries;
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

  /**
   * Accessor method to get a sorted list of all groceries.
   * The groceries are sorted alphabetically by their name.
   *
   * @return Will return a list of all groceries sorted by name.
   */
  public List<Grocery> getSortedList() {
    return groceries.values().stream()
        .flatMap(List::stream)
        .sorted(Comparator.comparing(Grocery::getName))
        .toList();
  }
}