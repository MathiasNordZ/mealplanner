package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;

import java.util.List;
import java.util.NoSuchElementException;

public class FoodStorageValidator {

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
  static void validateInputs(String groceryToRemove, float quantityToRemove) {
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
  static void validateGroceryList(List<Grocery> groceryList) {
    if (groceryList == null || groceryList.isEmpty()) {
      throw new NoSuchElementException("The grocery list is empty.");
    }
  }

  public static void validateGrocery(Grocery providedGrocery) {
    if (providedGrocery == null) {
      throw new IllegalArgumentException("The provided grocery cannot be null.");
    }
  }

  /**
   * Test
   * @param name
   */
  public static void validateString(String name) {
    if (name == null || name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("Provided name cannot be null, empty or blank.");
    }
  }
}
