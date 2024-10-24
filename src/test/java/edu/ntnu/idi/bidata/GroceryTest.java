package edu.ntnu.idi.bidata;

import org.junit.jupiter.api.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mathias Erik Nord
 * @since 16.10.2024
 *
 * This is the test class for the class Grocery. The test class is supposed to test certain methods of the Grocery class, both with positive and negative values.
 * The test class does follow the Arrange, Act and Assert philosophy, where an object of the class Grocery is created. It is acted upon, and then asserted.
 *
 */

class GroceryTest {
  /**
   * This is a positive test for the setQuantity method.
   * Will set a valid input and make getQuantity return a valid output.
   */
  @Test
  void setQuantityPositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setQuantity(5);

    assertEquals(5, grocery.getQuantity());
  }

  /**
   * This is a negative test for the setQuantity method.
   * This test will set an invalid input, and is expected to throw an IllegalArgumentException.
   */
  @Test
  void setQuantityNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setQuantity(-5));
  }

  /**
   * This is a positive test for the setName method.
   * This test will set a valid input, and is expected to return the correct output when calling getName.
   */
  @Test
  void setNamePositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setName("Chicken");

    assertEquals("Chicken", grocery.getName());
  }

  /**
   * This is a negative test for setName method.
   * This test will set an invaldig input and is expected to throw an IllegalArgumentException.
   */
  @Test
  void setNameNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setName(""));
  }

  /**
   * This is a positive test for setExpirationDate method.
   * This test will set a valid input, and is expected to return the correct output when calling getName.
   */
  @Test
  void setExpirationDatePositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setExpirationDate(LocalDate.of(2024, 12, 24));

    assertEquals(LocalDate.of(2024, 12, 24), grocery.getExpirationDate());
  }

  /**
   * This is a negative test for setExpirationDate method.
   * Will set an invalid input, and is expected to throw an IllegalArgumentException.
   */
  @Test
  void setExpirationDateNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> {
      grocery.setExpirationDate(LocalDate.now().minusDays(1));
    });
  }

  /**
   * This is a positive test for setUnitOfMeasurement method.
   * Will set a valid input, and is excpected to return the correct value when calling getUnitOfMeasurement.
   */
  @Test
  void setUnitOfMeasurementPositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setUnitOfMeasurement("gram");

    assertEquals("gram", grocery.getUnitOfMeasurement());
  }

  /**
   * This is a negative test for setUnitOfMeasurement method.
   * Will set an invalid input, and is expected to throw and IllegalArgumentException.
   */
  @Test
  void setUnitOfMeasurementNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setUnitOfMeasurement(""));
  }

  /**
   * This is a positive test for setPrice method.
   * Will set a valid input, and is expected to return the correct value when calling getPrice.
   */
  @Test
  void setPricePositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setPrice(1250);

    assertEquals(1250, grocery.getPrice());
  }

  /**
   * This is a negative test for setPrice method.
   * Will set an invalid input, and is expected to throw an IllegalArgumentException.
   */
  @Test
  void setPriceNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setPrice(-100));
  }
}