package edu.ntnu.idi.bidata;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GroceryTest {
  @Test
  void setQuantityPositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setQuantity(5);

    assertEquals(5, grocery.getQuantity());
  }

  @Test
  void setQuantityNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setQuantity(-5));
  }

  @Test
  void setNamePositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setName("Chicken");

    assertEquals("Chicken", grocery.getName());
  }

  @Test
  void setNameNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setName(""));
  }

  @Test
  void setExpirationDatePositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setExpirationDate(LocalDate.of(2024, 12, 24));

    assertEquals(LocalDate.of(2024, 12, 24), grocery.getExpirationDate());
  }

  @Test
  void setExpirationDateNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setExpirationDate(LocalDate.now().minusDays(1)));
  }

  @Test
  void setUnitOfMeasurementPositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setUnitOfMeasurement("gram");

    assertEquals("gram", grocery.getUnitOfMeasurement());
  }

  @Test
  void setUnitOfMeasurementNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setUnitOfMeasurement(""));
  }

  @Test
  void setPricePositiveTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setPrice(1250);

    assertEquals(1250, grocery.getPrice());
  }

  @Test
  void setPriceNegativeTest() {
    Grocery grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    assertThrows(IllegalArgumentException.class, () -> grocery.setPrice(-100));
  }
}