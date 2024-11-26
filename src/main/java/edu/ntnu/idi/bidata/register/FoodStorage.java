package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.GroceryItem;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This class represents a storage where instances of Grocery can be stored.
 * The storage can represent a fridge, drawer, freezer etc.
 *
 * @author Mathias Erik Nord
 * @since 30.10.2024
 */
public class FoodStorage {
  private final Map<String, List<GroceryItem>> groceries = new HashMap<>();

  /**
   * Constructor for <code>FoodStorage</code>.
   * Constructor is empty, because the idea is to have an empty storage,
   * that will be filled with instances of Grocery, with the according methods.
   */
  public FoodStorage() {
    // Currently empty
  }

  /**
   * Mutator method to add an instance of <code>Grocery</code> to <code>FoodStorage</code>.
   *
   * @param providedGrocery Represents the instance of a Grocery.
   */
  public void addGrocery(GroceryItem providedGrocery) {
    String errorMessage;
    if (providedGrocery == null) {
      errorMessage = "The provided grocery cannot be null.";
      throw new IllegalArgumentException(errorMessage);
    }

    List<GroceryItem> groceryList = groceries.getOrDefault(providedGrocery.getName(),
        new ArrayList<>());
    Iterator<GroceryItem> groceryIterator = groceryList.iterator();

    boolean isFound = false;
    while (groceryIterator.hasNext() && !isFound) {
      GroceryItem grocery = groceryIterator.next();

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
   * Validation method for <code>removeGrocery</code>.
   * Will validate the input, to check if the provided grocery is not null,
   * and that the quantity to remove is not less than or equal to zero.
   * This method was inspired by GitHub Copilot, to help reduce the cognitive complexity that
   *                                                                SonarLint was throwing.
   *
   * @param groceryToRemove The grocery that the quantity should be removed from.
   * @param quantityToRemove The quantity that is going to be removed.
   *
   * @throws IllegalArgumentException if the quantityToRemove is less than or equal to zero,
   *                                                          or the provided grocery is null.
   */
  private void validateInputs(String groceryToRemove, float quantityToRemove) {
    String errorMessage;
    if (groceryToRemove == null || groceryToRemove.isBlank() || quantityToRemove <= 0) {
      if (groceryToRemove == null || groceryToRemove.isBlank()) {
        errorMessage = "The provided grocery cannot be null or empty.";
      } else {
        errorMessage = "The quantity to remove cannot be less than or equal to zero.";
      }
      throw new IllegalArgumentException(errorMessage);
    }
  }

  /**
   * Validation method for <code>removeGrocery</code>.
   * Will validate the input to check if the groceryList is null or empty.
   * This method was inspired by GitHub Copilot, to help reduce the cognitive complexity that
   *                                                                SonarLint was throwing.
   *
   * @param groceryList List to check.
   * @throws NoSuchElementException if there is no elements in the list.
   */
  private void validateGroceryList(List<GroceryItem> groceryList) {
    if (groceryList == null || groceryList.isEmpty()) {
      throw new NoSuchElementException("The grocery list is empty.");
    }
  }

  /**
   * Mutator method for <code>removeGrocery</code>.
   * Will remove the specified quantity of the provided grocery, from the list.
   * This method was inspired by GitHub Copilot, to help reduce the cognitive complexity that
   *                                                                SonarLint was throwing.
   *
   * @param groceryToRemove The grocery to remove.
   * @param quantityToRemove The quantity to remove.
   * @param groceryList The list of where the grocery lives.
   * @throws IllegalArgumentException if the quantity to remove
   *                                  is higher than the available quantity.
   */
  private void removeGroceryFromList(String groceryToRemove,
                                     float quantityToRemove, List<GroceryItem> groceryList) {
    List<GroceryItem> itemsToRemove = new ArrayList<>();
    groceryList.forEach(grocery -> {
      if (grocery.getName().equalsIgnoreCase(groceryToRemove)) {
        float updatedQuantity = grocery.getQuantity() - quantityToRemove;
        if (updatedQuantity < 0) {
          throw new IllegalArgumentException("You are trying to remove a higher quantity, "
              + "than what is available.");
        } else if (updatedQuantity == 0) {
          itemsToRemove.add(grocery);
        } else {
          float pricePerUnit = grocery.getPrice() / grocery.getQuantity();
          grocery.setQuantity(updatedQuantity);
          grocery.setPrice(pricePerUnit * updatedQuantity);
        }
      }
    });
    groceryList.removeAll(itemsToRemove);
  }

  /**
   * Mutator method that will remove a specified grocery from the groceries HashMap.
   * This method has been broken into smaller methods to reduce the cognitive complexity.
   * The concept was inspired by GitHub Copilot.
   *
   * @param groceryToRemove The grocery to remove.
   * @param quantityToRemove The quantity to remove.
   */
  public void removeGrocery(String groceryToRemove, float quantityToRemove) {
    validateInputs(groceryToRemove, quantityToRemove);

    List<GroceryItem> groceryList = groceries.get(groceryToRemove);
    validateGroceryList(groceryList);

    removeGroceryFromList(groceryToRemove, quantityToRemove, groceryList);

    if (groceryList.isEmpty()) {
      groceries.remove(groceryToRemove);
    }
  }

  /**
   * Accessor method to search for an instance of Grocery in FoodStorage.
   * If there are two instances with different expiry dates,
   * it will be returned as two instances in a list.
   * If the two instances has the same expiry date, they will be combined.
   *
   * @param name Represents the name of the grocery to search for.
   * @return Will return the instance(s) that is searched for.
   */
  public List<GroceryItem> searchGrocery(String name) {
    if (name == null || name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("Provided name cannot be null, empty or blank.");
    }

    List<GroceryItem> searchedGrocery = new ArrayList<>();
    for (List<GroceryItem> groceryList : groceries.values()) {
      for (GroceryItem grocery : groceryList) {
        if (grocery.getName().equalsIgnoreCase(name)) {
          searchedGrocery.add(grocery);
        }
      }
    }
    return searchedGrocery;
  }

  /**
   * Accessor method to access the value of expired groceries.
   *
   * @return Will return the total value of expired groceries.
   */
  public float valueOfExpiredGroceries() {
    float totalValue = 0;
    for (List<GroceryItem> groceryList : groceries.values()) {
      for (GroceryItem grocery : groceryList) {
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
   *
   * @param providedExpiryDate Date to check if grocery expires before.
   * @return Will return a list of expired groceries.
   */
  public List<GroceryItem> listOfExpiredGroceries(String providedExpiryDate) {
    try {
      LocalDate expiryDate = LocalDate.parse(providedExpiryDate);
      List<GroceryItem> expiredGroceries = new ArrayList<>();

      for (List<GroceryItem> groceryList : groceries.values()) {
        for (GroceryItem grocery : groceryList) {
          if (grocery.getExpirationDate().isBefore(expiryDate)) {
            expiredGroceries.add(grocery);
          }
        }
      }
      return expiredGroceries;
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Please enter date on format 'YYYY-MM-DD'.");
    }
  }

  /**
   * Accessor method to access the value of all groceries.
   *
   * @return Will return the total value of all groceries.
   */
  public float valueOfAllGroceries() {
    float totalValue = 0;
    for (List<GroceryItem> groceryList : groceries.values()) {
      for (GroceryItem grocery : groceryList) {
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
  public List<GroceryItem> getSortedList() {
    List<GroceryItem> allGroceries = groceries.values().stream()
        .flatMap(List::stream)
        .toList();

    if (allGroceries.isEmpty()) {
      throw new NoSuchElementException("The grocery list is empty!");
    }

    return allGroceries.stream()
        .sorted(Comparator.comparing(GroceryItem::getName))
        .toList();
  }

  /**
   * Accessor method to check if there is enough quantity of a specified grocery.
   *
   * @param nameOfIngredient Name of ingredient to check for.
   * @param requiredQuantity The required quantity of ingredient.
   * @return Will return a boolean, true if available and false if not.
   */
  public boolean isGroceryAvailable(String nameOfIngredient, float requiredQuantity) {
    List<GroceryItem> groceryList = groceries.get(nameOfIngredient);

    if (groceryList == null) {
      return false;
    }
    float totalQuantity = 0;
    for (GroceryItem grocery : groceryList) {
      totalQuantity += grocery.getQuantity();
      if (totalQuantity >= requiredQuantity) {
        return true;
      }
    }
    return false;
  }
}