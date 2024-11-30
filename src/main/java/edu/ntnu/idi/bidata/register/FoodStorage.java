package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static edu.ntnu.idi.bidata.register.FoodStorageValidator.*;

/**
 * This class represents a storage where instances of Grocery can be stored.
 * The storage can represent a fridge, drawer, freezer etc.
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>05.11.2024</b>
 * @version <b>0.0.1</b>
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
   * Mutator method to add an instance of <code>Grocery</code> to <code>FoodStorage</code>.
   *
   * @param providedGrocery Represents the instance of a Grocery.
   */
  public void addGrocery(Grocery providedGrocery) {
    validateGrocery(providedGrocery);
    List<Grocery> groceryList = groceries.getOrDefault(providedGrocery.getName(),
        new ArrayList<>());
    Iterator<Grocery> groceryIterator = groceryList.iterator();

    boolean isFound = false;
    isFound = isFound(providedGrocery, groceryIterator, isFound);

    if (!isFound) {
      groceryList.add(providedGrocery);
    }
    groceries.put(providedGrocery.getName(), groceryList);
  }

  /**
   * Extracted while loop from <code>addGrocery</code>.
   * This will reduce the complexity of the method, and improve modularity.
   *
   * @param providedGrocery Represents the instance of a Grocery.
   * @param groceryIterator Will iterate through the grocery list.
   * @param isFound Will flag if the grocery is found.
   * @return Will return a boolean if the grocery already exists or not.
   */
  private boolean isFound(Grocery providedGrocery, Iterator<Grocery> groceryIterator, boolean isFound) {
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
                                     float quantityToRemove, List<Grocery> groceryList) {
    List<Grocery> itemsToRemove = new ArrayList<>();
    groceryList.forEach(grocery -> removeGroceryLogic(groceryToRemove, quantityToRemove, grocery, itemsToRemove));
    groceryList.removeAll(itemsToRemove);
  }

  /**
   * Extracted from removeFromGroceryList
   *
   *
   * @param groceryToRemove
   * @param quantityToRemove
   * @param grocery
   * @param itemsToRemove
   */
  private void removeGroceryLogic(String groceryToRemove, float quantityToRemove, Grocery grocery, List<Grocery> itemsToRemove) {
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

    List<Grocery> groceryList = groceries.entrySet().stream()
        .filter(entry -> entry.getKey().equalsIgnoreCase(groceryToRemove))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("Grocery does not exist!"));

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
  public List<Grocery> searchGrocery(String name) {
    validateString(name);
    List<Grocery> searchedGrocery = new ArrayList<>();
    for (List<Grocery> groceryList : groceries.values()) {
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
   *
   * @return Will return the total value of expired groceries.
   */
  public float valueOfExpiredGroceries(List<Grocery> expiredGroceries) {
    validateGroceryList(expiredGroceries);
    return expiredGroceries.stream()
        .map(Grocery::getPrice)
        .reduce(0f, Float::sum);
  }

  /**
   * Accessor method for expired groceries.
   * Will return a list of groceries that expires before given date.
   *
   * @param providedExpiryDate Date to check if grocery expires before.
   * @return Will return a list of expired groceries.
   */
  public List<Grocery> listOfExpiredGroceries(String providedExpiryDate) {
    try {
      LocalDate expiryDate = LocalDate.parse(providedExpiryDate);
      List<Grocery> expiredGroceries = new ArrayList<>();
      expiredGroceries(expiryDate, expiredGroceries);

      return expiredGroceries;
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Please enter date on format 'YYYY-MM-DD'.");
    }
  }

  /**
   * Extracted from listOfExpiredGroceries.
   * @param expiryDate
   * @param expiredGroceries
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
   * Accessor method to access the value of all groceries.
   *
   * @return Will return the total value of all groceries.
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
   * Accessor method to get a sorted list of all groceries.
   * The groceries are sorted alphabetically by their name.
   *
   * @return Will return a list of all groceries sorted by name.
   */
  public List<Grocery> getSortedList() {
    List<Grocery> allGroceries = groceries.values().stream()
        .flatMap(List::stream)
        .toList();

    if (allGroceries.isEmpty()) {
      throw new NoSuchElementException("The grocery list is empty!");
    }

    return allGroceries.stream()
        .sorted(Comparator.comparing(Grocery::getName))
        .toList();
  }

  /**
   * Accessor method to check if there is enough quantity of a specified grocery.
   *
   * @param nameOfIngredient Name of ingredient to check for.
   * @param requiredQuantity The required quantity of ingredient.
   * @return Will return a boolean, true if available and false if not.
   */
  public boolean isGroceryAvailable(String nameOfIngredient, float requiredQuantity, String unitOfMeasurement) {
    for (List<Grocery> groceryList : groceries.values()) {
      for (Grocery grocery : groceryList) {
        if (grocery.getName().equals(nameOfIngredient) &&
            grocery.getQuantity() >= requiredQuantity &&
            grocery.getUnitOfMeasurement().equalsIgnoreCase(unitOfMeasurement)) {
          return true;
        }
      }
    }
    return false;
  }
}