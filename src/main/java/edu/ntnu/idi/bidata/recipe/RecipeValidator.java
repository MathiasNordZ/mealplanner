package edu.ntnu.idi.bidata.recipe;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class RecipeValidator {
  public RecipeValidator() {

  }

  private static void ingredientValidation(Map<String, SimpleEntry<Float, String>> ingredients, StringBuilder errorMessage) {
    if (ingredients == null) {
      errorMessage.append("The inputted ingredients cannot be null\n");
    } else {
      for (Map.Entry<String, SimpleEntry<Float, String>> entry : ingredients.entrySet()) {
        String ingredientName = entry.getKey();
        SimpleEntry<Float, String> quantityAndUnit = entry.getValue();

        if (ingredientName == null || ingredientName.isBlank()) {
          errorMessage.append("Ingredient name cannot be null or blank.\n");
        }
        if (quantityAndUnit == null || quantityAndUnit.getKey() <= 0 || quantityAndUnit.getValue().isBlank()) {
          errorMessage.append("Quantity cannot be null, equal or less than zero.\n");
        }
      }
    }
  }

  /**
   * Error handling method for the method<code>setIngredients</code>.
   *
   * @param ingredients The Map to validate.
   * @throws IllegalArgumentException if the <code>ingredients</code> is null,
   *                                  if <code>ingredientName</code> is null or blank,
   *                               or if <code>quantity</code> is null, less than or equal to zero.
   */
  public static void mapInputValidation(Map<String, SimpleEntry<Float, String>> ingredients) {
    StringBuilder errorMessage = new StringBuilder();
    ingredientValidation(ingredients, errorMessage);
    if (!errorMessage.isEmpty()) {
      throw new IllegalArgumentException(errorMessage.toString());
    }
  }

  /**
   * Validation method for <code>String</code> inputs.
   *
   * @param stringInput input that is passed from outer method.
   * @throws IllegalArgumentException is thrown if string input is null, blank or empty.
   */
  public static void stringInputValidation(String stringInput) {
    if (stringInput == null || stringInput.isBlank() || stringInput.isEmpty()) {
      throw new IllegalArgumentException("Provided input cannot be null, empty or blank.");
    }
  }

  public static void amountOfServingsValidation(int amountOfServings) {
    if (amountOfServings <= 0) {
      throw new IllegalArgumentException("The inputted amount of servings cannot be less than zero");
    }
  }
}
