package edu.ntnu.idi.bidata.util;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringFormatterTest {
  FoodStorage foodStorage;
  StringFormatter stringFormatter;
  Grocery apple, banana;

  @BeforeEach
  public void SetUp() {
    foodStorage = new FoodStorage();
    stringFormatter = new StringFormatter();

    banana = new Grocery(2, "Banana", "pcs", 10, "2024-12-13");
    apple = new Grocery(10, "Apple", "pcs", 25, "2024-12-12");
  }

  @Test
  void formatSortedGroceriesPositiveTest() {
  }

  @Test
  void formatSortedGroceriesNegativeTest() {
  }

  @Test
  void formatExpiredGroceriesPositiveTest() {
  }

  @Test
  void formatExpiredGroceriesNegativeTest() {
  }

  @Test
  void formatGroceryPositiveTest() {
  }

  @Test
  void formatGroceryNegativeTest() {
  }

  @Test
  void formatRecipePositiveTest() {
  }
}