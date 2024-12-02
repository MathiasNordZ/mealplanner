package edu.ntnu.idi.bidata.register;

import static edu.ntnu.idi.bidata.register.FoodStorageValidator.validateGrocery;
import static edu.ntnu.idi.bidata.register.FoodStorageValidator.validateGroceryList;
import static edu.ntnu.idi.bidata.register.FoodStorageValidator.validateInputs;
import static edu.ntnu.idi.bidata.register.FoodStorageValidator.validateString;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.util.GroceryFormatter;

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
 * This class represents a storage where instances of <code>Grocery</code>> can be stored.
 * The storage can represent a fridge, drawer, freezer etc.
 *
 * @author Mathias Erik Nord
 * @since 05.11.2024
 * @version 0.0.1
 *
 */
public class FoodStorage {
  private final Map<String, List<Grocery>> groceries = new HashMap<>();

  /**
   * Constructor for <code>FoodStorage</code>.
   * Constructor is empty, because the idea is to have an empty storage,
   * that will be filled with instances of Grocery, with the according methods.
   */
  public FoodStorage() {
    // Currently empty
  }

  /**
   * Adds an instance of <code>Grocery</code> to the storage.
   * If the grocery already exists with the same expiration date, it updates the quantity and price.
   *
   * @param providedGrocery The instance of a <code>Grocery</code> to add.
   * @throws IllegalArgumentException if the provided grocery is invalid.
   */
  public void addGrocery(Grocery providedGrocery) {
    validateGrocery(providedGrocery);
    String normalizedGroceryName = GroceryFormatter.normalizedString(providedGrocery.getName());
    List<Grocery> groceryList = groceries.getOrDefault(normalizedGroceryName,
        new ArrayList<>());
    Iterator<Grocery> groceryIterator = groceryList.iterator();

    boolean isFound = false;
    boolean found = isFound(providedGrocery, groceryIterator, isFound);

    if (!found) {
      groceryList.add(providedGrocery);
    }
    groceries.put(providedGrocery.getName(), groceryList);
  }

  /**
   * Checks if the given grocery is found in the storage.
   *
   * @param providedGrocery The instance of <code>Grocery</code> to check.
   * @param groceryIterator The iterator for the grocery list.
   * @param isFound The flag indicating if the grocery is found.
   * @return <code>true</code> if the grocery is found, <code>false</code> if not found.
   */
  private boolean isFound(Grocery providedGrocery, Iterator<Grocery> groceryIterator,
                          boolean isFound) {
    while (groceryIterator.hasNext() && !isFound) {
      Grocery grocery = groceryIterator.next();

      if (grocery.getExpirationDate().equals(providedGrocery.getExpirationDate())) {
        grocery.setQuantity(grocery.getQuantity() + providedGrocery.getQuantity());
        grocery.setPrice(grocery.getPrice() + providedGrocery.getPrice());

        isFound = true;
      }
    }
    return isFound;
  }

  /**
   * Removes the specified quantity of the provided grocery, from the list.
   * This method was inspired by GitHub Copilot, to help reduce the cognitive complexity that
   *                                                                                                      SonarLint was throwing.
   *
   * @param groceryToRemove The grocery to remove.
   * @param quantityToRemove The quantity to remove.
   * @param groceryList The list to remove groceries from.
   * @throws IllegalArgumentException if the quantity to remove is higher than available quantity.
   */
  private void removeGroceryFromList(String groceryToRemove,
                                     float quantityToRemove, List<Grocery> groceryList) {
    float remainingToRemove = quantityToRemove;
    Iterator<Grocery> groceryIterator = groceryList.iterator();
    remainingToRemove = removalLogic(groceryToRemove, groceryIterator, remainingToRemove);

    if (remainingToRemove > 0) {
      throw new IllegalArgumentException("You are trying to remove a higher quantity than available.");
    }
  }

  /**
   * The removal logic when removing a grocery.
   *
   * @param groceryToRemove The grocery to remove.
   * @param groceryIterator The iterator that iterates the groceries.
   * @param remainingToRemove The remaining quantity to remove.
   * @return The remaining quantity.
   */
  private float removalLogic(String groceryToRemove, Iterator<Grocery> groceryIterator, float remainingToRemove) {
    while (groceryIterator.hasNext() && remainingToRemove > 0) {
      Grocery grocery = groceryIterator.next();

      if (grocery.getName().equalsIgnoreCase(groceryToRemove)) {
        if (grocery.getQuantity() <= remainingToRemove) {
          remainingToRemove -= grocery.getQuantity();
          groceryIterator.remove();
        } else {
          float pricePerUnit = grocery.getPrice() / grocery.getQuantity();
          grocery.setQuantity(grocery.getQuantity() - remainingToRemove);
          grocery.setPrice(pricePerUnit * grocery.getQuantity());
          remainingToRemove = 0;
        }
      }
    }
    return remainingToRemove;
  }

  /**
   * Removes a specified grocery from the storage.
   *
   * @param groceryToRemove The name of the grocery to remove.
   * @param quantityToRemove The quantity to remove.
   * @throws IllegalArgumentException if the inputs are invalid.
   * @throws NoSuchElementException if the grocery list is empty.
   */
  public void removeGrocery(String groceryToRemove, float quantityToRemove) {
    validateInputs(groceryToRemove, quantityToRemove);
    String normalizedGroceryName = GroceryFormatter.normalizedString(groceryToRemove);

    List<Grocery> groceryList = groceries.entrySet().stream()
        .filter(entry -> entry.getKey().equalsIgnoreCase(normalizedGroceryName))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElse(new ArrayList<>());

    validateGroceryList(groceryList);

    removeGroceryFromList(groceryToRemove, quantityToRemove, groceryList);

    if (groceryList.isEmpty()) {
      groceries.remove(groceryToRemove);
    }
  }

  /**
   * Method that searches for an instance of <code>Grocery</code> in the storage.
   *
   * @param nameOfGrocery The name of the grocery to search for.
   * @return List of groceries that matches the search.
   * @throws IllegalArgumentException if the input name is invalid.
   */
  public List<Grocery> searchGrocery(String nameOfGrocery) {
    validateString(nameOfGrocery);
    List<Grocery> searchedGrocery = new ArrayList<>();
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        if (grocery.getName().equalsIgnoreCase(nameOfGrocery)) {
          searchedGrocery.add(grocery);
        }
      }
    }
    return List.copyOf(searchedGrocery);
  }

  /**
   * Calculates the total value of expired groceries.
   *
   * @param expiredGroceries The list of expired groceries.
   * @return The total value of expired groceries.
   * @throws NoSuchElementException the grocery list is empty
   */
  public float valueOfExpiredGroceries(List<Grocery> expiredGroceries) {
    validateGroceryList(expiredGroceries);
    return expiredGroceries.stream()
        .map(Grocery::getPrice)
        .reduce(0f, Float::sum);
  }

  /**
   * Lists the groceries that expire before the given date.
   *
   * @param providedExpiryDate The date to check against.
   * @return List of expired groceries.
   * @throws IllegalArgumentException if the date format is invalid.
   */
  public List<Grocery> listOfExpiredGroceries(String providedExpiryDate) {
    try {
      LocalDate expiryDate = LocalDate.parse(providedExpiryDate);
      List<Grocery> expiredGroceries = new ArrayList<>();
      expiredGroceries(expiryDate, expiredGroceries);

      return List.copyOf(expiredGroceries);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Please enter date on format 'YYYY-MM-DD'.");
    }
  }

  /**
   * Helper method to find the expired groceries.
   *
   * @param expiryDate The expiry date to check against.
   * @param expiredGroceries The list of expired groceries.
   */
  private void expiredGroceries(LocalDate expiryDate, List<Grocery> expiredGroceries) {
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        if (grocery.getExpirationDate().isBefore(expiryDate)) {
          expiredGroceries.add(grocery);
        }
      }
    }
  }

  /**
   * Calculates the total value of all groceries in storage.
   *
   * @return The total value of all groceries.
   */
  public float valueOfAllGroceries() {
    try {
      return groceries.values().stream()
          .filter(groceryList -> groceryList != null && !groceryList.isEmpty())
          .flatMap(List::stream)
          .map(Grocery::getPrice)
          .reduce(0f, Float::sum);
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * Get method to get a sorted list of all groceries in storage.
   * Sorted alphabetically by name
   *
   * @return Sorted list of groceries.
   * @throws NoSuchElementException if the grocery list is empty.
   */
  public List<Grocery> getSortedList() {
    List<Grocery> allGroceries = groceries.values().stream()
        .flatMap(List::stream)
        .toList();

    if (allGroceries.isEmpty()) {
      throw new NoSuchElementException("The grocery list is empty!");
    }

    return List.copyOf(allGroceries.stream()
        .sorted(Comparator
            .comparing(Grocery::getName)).toList());
  }

  /**
   * Checks if there is enough quantity of a specified grocery.
   *
   * @param nameOfIngredient The name of the ingredient to check.
   * @param requiredQuantity The required quantity of ingredient.
   * @return <code>true</code> if the required quantity is available, <code>false</code> otherwise.>
   */
  public boolean isGroceryAvailable(String nameOfIngredient, float requiredQuantity,
                                    String unitOfMeasurement) {
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        if (grocery.getName().equals(nameOfIngredient)
            && grocery.getQuantity() >= requiredQuantity
            && grocery.getUnitOfMeasurement().equalsIgnoreCase(unitOfMeasurement)) {
          return true;
        }
      }
    }
    return false;
  }
}