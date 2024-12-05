package edu.ntnu.idi.bidata.recipe;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Utility class for validating recipe-related inputs.
 * Class contains static methods for validating ingredients, strings and number of servings.
 * The class can not be instantiated, therefore constructor is private.
 *
 * @author Mathias Erik Nord
 * @since 28.11.2024
 * @version 0.0.1
 */
public class RecipeValidator {
  private RecipeValidator() {
    // Private constructor to prevent instantiation.
  }

  /**
   * Validates the ingredients map and appends error messages to the StringBuilder.
   *
   * @param ingredients The map of the ingredients to validate.
   * @param errorMessage The StringBuilder to append messages to.
   * @since 0.0.1
   */
  private static void ingredientValidation(Map<String, SimpleEntry<BigDecimal, String>> ingredients,
                                           StringBuilder errorMessage) {
    if (ingredients == null) {
      errorMessage.append("The inputted ingredients cannot be null\n");
    } else {
      for (Map.Entry<String, SimpleEntry<BigDecimal, String>> entry : ingredients.entrySet()) {
        String ingredientName = entry.getKey();
        SimpleEntry<BigDecimal, String> quantityAndUnit = entry.getValue();

        validateIngredientName(errorMessage, ingredientName);
        validateQuantityAndUnit(errorMessage, quantityAndUnit);
      }
    }
  }

  /**
   * Validates the quantity and unit of an ingredient and appends potential.
   * error message to StringBuilder
   *
   * @param errorMessage The StringBuilder to append error message to.
   * @param quantityAndUnit The SimpleEntry containing the quantity and unit to validate.
   * @since 0.0.1
   */
  private static void validateQuantityAndUnit(StringBuilder errorMessage,
                                              SimpleEntry<BigDecimal, String> quantityAndUnit) {
    if (quantityAndUnit == null) {
      errorMessage.append("Quantity and unit cannot be null");
      return;
    }
    if (quantityAndUnit.getKey() == null || quantityAndUnit.getKey().compareTo(BigDecimal.ZERO) <= 0) {
      errorMessage.append("Quantity cannot be null, less than or equal to zero.");
    }
    if (quantityAndUnit.getValue() == null || quantityAndUnit.getValue().isBlank()) {
      errorMessage.append("Unit of measurement cannot be null, empty or blank.");
    }
  }

  /**
   * Validates the ingredient name and appends potential error message to StringBuilder.
   *
   * @param errorMessage The StringBuilder to append error messages to.
   * @param ingredientName The name of the ingredient to validate.
   */
  private static void validateIngredientName(StringBuilder errorMessage, String ingredientName) {
    if (ingredientName == null || ingredientName.isBlank()) {
      errorMessage.append("Ingredient name cannot be null or blank.\n");
    }
  }

  /**
   * Validates the ingredients map.
   *
   * @param ingredients The map of ingredients to validate.
   * @throws IllegalArgumentException if the ingredients map is null,
   *                                  contains null or blank ingredient names,
   *                                  or contains quantities that are null,
   *                                  less than or equal to zero.
   * @since 0.0.1
   */
  public static void mapInputValidation(Map<String, SimpleEntry<BigDecimal, String>> ingredients) {
    StringBuilder errorMessage = new StringBuilder();
    ingredientValidation(ingredients, errorMessage);
    if (!errorMessage.isEmpty()) {
      throw new IllegalArgumentException(errorMessage.toString());
    }
  }

  /**
   * Validates the provided string parameter.
   *
   * @param stringInput input that is passed from outer method.
   * @throws IllegalArgumentException is thrown if string input is null, blank or empty.
   * @since 0.0.1
   */
  public static void stringInputValidation(String stringInput) {
    if (stringInput == null || stringInput.isBlank() || stringInput.isEmpty()) {
      throw new IllegalArgumentException("Provided input cannot be null, empty or blank.");
    }
  }

  /**
   * Validates the number of servings.
   *
   * @param amountOfServings The number of servings to validate.
   * @throws IllegalArgumentException if the number of servings is less than, or equal to zero.
   * @since 0.0.1
   */
  public static void amountOfServingsValidation(int amountOfServings) {
    if (amountOfServings <= 0) {
      throw new
          IllegalArgumentException("The inputted amount of servings cannot be less than zero");
    }
  }
}
