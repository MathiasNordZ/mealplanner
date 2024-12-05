package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Utility class for validating inputs related to <code>FoodStorage</code>.
 * The provided methods will be able to validate the groceries and the belonging attributes.
 */
public class FoodStorageValidator {

  private FoodStorageValidator() {
    // Private constructor to prevent instantiation.
  }

  /**
   * Validates the input for removing a grocery.
   * Checks if the provided grocery name is not null or empty.
   * This method was inspired by GitHub Copilot, to help reduce the cognitive complexity that
   *                                                                SonarLint was throwing.
   *
   * @param groceryToRemove The name of the grocery to remove,
   * @param quantityToRemove The quantity that is going to be removed.
   *
   * @throws IllegalArgumentException if the grocery name is null or empty,
   *                                  or if the quantity to remove is less than or equal to zero.
   */
  static void validateInputs(String groceryToRemove, BigDecimal quantityToRemove) {
    String errorMessage;
    if (groceryToRemove == null || groceryToRemove.isBlank() || quantityToRemove.compareTo(BigDecimal.ZERO) <= 0) {
      if (groceryToRemove == null || groceryToRemove.isBlank()) {
        errorMessage = "The provided grocery cannot be null or empty.";
      } else {
        errorMessage = "The quantity to remove cannot be less than or equal to zero.";
      }
      throw new IllegalArgumentException(errorMessage);
    }
  }

  /**
   * Validates the grocery list.
   * Checks if the grocery list is not null or empty.
   * This method was inspired by GitHub Copilot, to help reduce the cognitive complexity that
   *                                                                SonarLint was throwing.
   *
   * @param groceryList The list of groceries to validate.
   * @throws NoSuchElementException if there is no elements in the list.
   */
  public static void validateGroceryList(List<Grocery> groceryList) {
    if (groceryList == null || groceryList.isEmpty()) {
      throw new NoSuchElementException("The grocery was not found!");
    }
  }

  /**
   * Validates a grocery instance.
   * Checks if the provided grocery is not null.
   *
   * @param providedGrocery the grocery to validate.
   * @throws IllegalArgumentException if the provided grocery is null.
   */
  public static void validateGrocery(Grocery providedGrocery) {
    if (providedGrocery == null) {
      throw new IllegalArgumentException("The provided grocery cannot be null.");
    }
  }

  /**
   * Validates a string.
   * Checks if the provided string is not null, empty or blank.
   *
   * @param providedString the string to validate.
   * @throws IllegalArgumentException if the provided string is null, empty or blank.
   */
  public static void validateString(String providedString) {
    if (providedString == null || providedString.isBlank()) {
      throw new IllegalArgumentException("Provided string cannot be null, empty or blank.");
    }
  }
}
