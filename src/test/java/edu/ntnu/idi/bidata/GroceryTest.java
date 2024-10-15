package edu.ntnu.idi.bidata;

import org.junit.jupiter.api.*;
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

    assertThrows(IllegalArgumentException.class, () -> grocery.getName());
  }

  @Test
  void setExpirationDate() {
  }

  @Test
  void setUnitOfMeasurement() {
  }

  @Test
  void setPrice() {
  }
}