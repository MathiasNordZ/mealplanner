package edu.ntnu.idi.bidata.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Test class for <code>Grocery</code>.
 * This is a unit test class that checks if the <code>Grocery</code> class behaves correctly,
 * in both positive and negative cases.
 *
 * @author Mathias Erik Nord
 * @since 16.10.2024
 * @version 0.0.1
 */

class GroceryTest {
  private Grocery grocery;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  /**
   * Setup method, will set up a clean object before each test.
   */
  @BeforeEach
  void setUp() {
    final BigDecimal quantity = BigDecimal.valueOf(3);
    String name = "Beef";
    String unitOfMeasurement = "kilogram";
    BigDecimal price = BigDecimal.valueOf(750);
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
    BigDecimal value = BigDecimal.valueOf(5);

    grocery.setQuantity(value);

    assertEquals(0, value.compareTo(grocery.getQuantity()));
  }

  /**
   * This is a negative test for the <code>setQuantity</code> method.
   * @throws IllegalArgumentException should be thrown, because this test will set a negative value as quantity.
   */
  @Test
  void setQuantityNegativeTest() {
    BigDecimal value = BigDecimal.valueOf(-5);

    assertThrows(IllegalArgumentException.class, ()-> grocery.setQuantity(value));
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

    grocery.setExpiryDate(formattedDateInFuture);

    assertEquals(dateInFuture, grocery.getExpiryDate());
  }

  /**
   * This is a negative test for <code>setExpirationDate</code> method.
   */
  @Test
  void setExpirationDateNegativeTest() {
    String[] invalidDates = {"2024-16-12", "2024/12/12", "2025.10.01"};
    for (String invalidDate : invalidDates) {
      assertThrows(IllegalArgumentException.class, () -> grocery.setExpiryDate(invalidDate));
    }
  }

  /**
   * This is a positive test for <code>setUnitOfMeasurement</code> method.
   * Will set a valid input, and is expected to return the correct value when calling <code>getUnitOfMeasurement</code>.
   */
  @Test
  void setUnitOfMeasurementPositiveTest() {
    grocery.setUnit("Kilogram");

    assertEquals("Kilogram", grocery.getUnit());
  }

  /**
   * This is a negative test for <code>setUnitOfMeasurement</code> method.
   * @throws IllegalArgumentException should be thrown, because unit of measurement is set to empty.
   */
  @Test
  void setUnitOfMeasurementNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> grocery.setUnit(""));
  }

  /**
   * This is a positive test for <code>setPrice</code> method.
   * Will set a valid input, and is expected to return the correct value when calling <code>getPrice</code>.
   */
  @Test
  void setPricePositiveTest() {
    grocery.setPrice(BigDecimal.valueOf(1250));

    assertEquals(0, grocery.getPrice().compareTo(BigDecimal.valueOf(1250.00)));
  }

  /**
   * This is a negative test for <code>setPrice</code> method.
   */
  @Test
  void setPriceNegativeTest() {
    BigDecimal negativeValue = BigDecimal.ZERO.subtract(BigDecimal.valueOf(100));
    assertThrows(IllegalArgumentException.class, () -> grocery.setPrice(negativeValue));
  }
}