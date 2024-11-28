package edu.ntnu.idi.bidata.recipe;

import java.util.AbstractMap.SimpleEntry;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecipeValidatorTest {
  /**
   * Positive test method for <code>mapInputValidation</code>.
   * Will check that an exception is not thrown, when the provided parameter is valid.
   */
  @Test
  void mapInputValidationPositiveTest() {
    Map<String, SimpleEntry<Float, String>> validIngredients = new HashMap<>();
    validIngredients.put("Tomato Sauce", new SimpleEntry<>(0.25f, "kilogram"));

    assertDoesNotThrow(() -> RecipeValidator.mapInputValidation(validIngredients));
  }

  /**
   * Negative test method for <code>mapInputValidation</code>.
   * Will check that an exception is thrown when the provided parameter is invalid.
   */
  @Test
  void mapInputValidationNegativeTest() {
    Map<String, SimpleEntry<Float, String>> invalidIngredients = new HashMap<>();
    invalidIngredients.put(null, new SimpleEntry<>(0.25f, "kilogram"));

    assertThrows(IllegalArgumentException.class, () -> RecipeValidator.mapInputValidation(invalidIngredients));
  }
}
