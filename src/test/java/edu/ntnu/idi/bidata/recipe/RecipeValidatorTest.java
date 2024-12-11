package edu.ntnu.idi.bidata.recipe;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the <code>Recipe</code> validators.
 *
 * @author Mathias Erik Nord
 * @version 1.0.0
 */
class RecipeValidatorTest {
  /**
   * Positive test method for <code>mapInputValidation</code>.
   * Will check that an exception is not thrown, when the provided parameter is valid.
   */
  @Test
  void mapInputValidationPositiveTest() {
    Map<String, SimpleEntry<BigDecimal, String>> validIngredients = new HashMap<>();
    validIngredients.put("Tomato Sauce", new SimpleEntry<>(BigDecimal.valueOf(0.25), "kilogram"));

    assertDoesNotThrow(() -> RecipeValidator.mapInputValidation(validIngredients));
  }

  /**
   * Negative test method for <code>mapInputValidation</code>.
   * Will check that an exception is thrown when the provided parameter is invalid.
   */
  @Test
  void mapInputValidationNegativeTest() {
    Map<String, SimpleEntry<BigDecimal, String>> invalidIngredients = new HashMap<>();
    invalidIngredients.put(null, new SimpleEntry<>(BigDecimal.valueOf(0.25), "kilogram"));

    assertThrows(IllegalArgumentException.class, () -> RecipeValidator.mapInputValidation(invalidIngredients));
  }
}
