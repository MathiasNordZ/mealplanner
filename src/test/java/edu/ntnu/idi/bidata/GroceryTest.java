package edu.ntnu.idi.bidata;

import static org.junit.jupiter.api.Assertions.*;

class GroceryTest {
  @org.junit.jupiter.api.BeforeAll
  static void init() {
    Grocery grocery;
  }

  @org.junit.jupiter.api.Test
  void setQuantityPositiveTest() {
    grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setQuantity(5);

    assertEquals(5, grocery.getQuantity());
  }

  @org.junit.jupiter.api.Test
  void setQuantityNegativeTest() {
    grocery = new Grocery(3, "Beef", "kg", 750, 2024, 10, 25);

    grocery.setQuantity(-5);

    assertEquals(5, grocery.getQuantity());
  }

}