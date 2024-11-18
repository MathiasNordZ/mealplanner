package edu.ntnu.idi.bidata.entity;

import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
  private Grocery grocery;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @BeforeEach
  void setUp() {
    float quantity = 3f;
    String name = "Beef";
    String unitOfMeasurement = "kilogram";
    float price = 750f;
    LocalDate today = LocalDate.now();
    String formattedToday = today.format(formatter);

    grocery = new Grocery(quantity, name, unitOfMeasurement, price, formattedToday);
  }

  /**
   * This is a positive test for the <code>setQuantity</code> method.
   * Will set a valid input and make <code>getQuantity</code> return a valid output.
   */
  @Test
  void setQuantityPositiveTest() {

    grocery.setQuantity(5);

    assertEquals(5, grocery.getQuantity());
  }

  /**
   * This is a negative test for the <code>setQuantity</code> method.
   * @throws IllegalArgumentException should be thrown, because this test will set a negative value as quantity.
   */
  @Test
  void setQuantityNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> grocery.setQuantity(-5));
  }

  /**
   * This is a positive test for the <code>setName</code> method.
   * This test will set a valid input, and is expected to return the correct output when calling <code>getName</code>.
   */
  @Test
  void setNamePositiveTest() {
    grocery.setName("Chicken");

    assertEquals("Chicken", grocery.getName());
  }

  /**
   * This is a negative test for <code>setName</code> method.
   * @throws IllegalArgumentException should be thrown, because this test will set an empty string as name.
   */
  @Test
  void setNameNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> grocery.setName(""));
  }

  /**
   * This is a positive test for <code>setExpirationDate</code> method.
   * This test will set a valid input, and is expected to return the correct output when calling <code>getExpirationDate</code>.
   */
  @Test
  void setExpirationDatePositiveTest() {
    LocalDate dateInFuture = LocalDate.now().plusDays(1);
    String formattedDateInFuture = dateInFuture.format(formatter);

    grocery.setExpirationDate(formattedDateInFuture);

    assertEquals(dateInFuture, grocery.getExpirationDate());
  }

  /**
   * This is a negative test for <code>setExpirationDate</code> method.
   * @throws IllegalArgumentException should be thrown, because this test will set an invalid date format.
   */
  @Test
  void setExpirationDateNegativeTest() {
    String invalidDate = "10/12/2024";
    assertThrows(IllegalArgumentException.class, () -> grocery.setExpirationDate(invalidDate));
  }

  /**
   * This is a positive test for <coded>setUnitOfMeasurement</coded> method.
   * Will set a valid input, and is expected to return the correct value when calling <code>getUnitOfMeasurement</code>.
   */
  @Test
  void setUnitOfMeasurementPositiveTest() {
    grocery.setUnitOfMeasurement("kilogram");

    assertEquals("kilogram", grocery.getUnitOfMeasurement());
  }

  /**
   * This is a negative test for <code>setUnitOfMeasurement</code> method.
   * @throws IllegalArgumentException should be thrown, because unit of measurement is set to empty.
   */
  @Test
  void setUnitOfMeasurementNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> grocery.setUnitOfMeasurement(""));
  }

  /**
   * This is a positive test for <code>setPrice</code> method.
   * Will set a valid input, and is expected to return the correct value when calling <code>getPrice</code>.
   */
  @Test
  void setPricePositiveTest() {
    grocery.setPrice(1250);

    assertEquals(1250, grocery.getPrice());
  }

  /**
   * This is a negative test for <code>setPrice</code> method.
   * @throws IllegalArgumentException should be thrown, because the price is set to negative.
   */
  @Test
  void setPriceNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> grocery.setPrice(-100));
  }
}