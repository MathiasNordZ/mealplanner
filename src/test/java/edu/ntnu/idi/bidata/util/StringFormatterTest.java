package edu.ntnu.idi.bidata.util;

import edu.ntnu.idi.bidata.entity.GroceryItem;
import edu.ntnu.idi.bidata.register.FoodStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringFormatterTest {
  FoodStorage foodStorage;
  StringFormatter stringFormatter;

  @BeforeEach
  public void SetUp() {
    foodStorage = new FoodStorage();
    stringFormatter = new StringFormatter();
  }

  @Test
  void formatGroceriesPositiveTest() {
  }

  @Test
  void formatGroceriesNegativeTest() {
  }

  @Test
  void formatRecipesPositiveTest() {
  }

  @Test
  void formatRecipesNegativeTest() {
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