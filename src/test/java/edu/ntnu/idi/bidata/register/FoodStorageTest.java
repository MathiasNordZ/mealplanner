package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodStorageTest {
  private FoodStorage foodStorage;
  private Grocery milk;
  private Grocery chicken;
  private Grocery cheese;
  private Grocery soda;

  @BeforeEach
  void setUp() {
    LocalDate today = LocalDate.now();
    int year = today.getYear();
    int month = today.getMonthValue();
    int day = today.getDayOfMonth();
    String LITER = "liter";
    String KILOGRAM = "liter";

    foodStorage = new FoodStorage();
    milk = new Grocery(1f, "Milk", LITER, 20, year, month, day);
    chicken = new Grocery(1.2f, "Chicken", KILOGRAM, 120, year, month, day);
    cheese = new Grocery(0.75f, "Cheese", KILOGRAM, 85, year, month, day);
    soda = new Grocery(0.33f, "Coca Cola", LITER, 18, year, month, day);
  }

  @Test
  void addGrocery() {
    foodStorage.addGrocery(milk);

    List<Grocery> groceries = foodStorage.searchGrocery("Milk");
    assertFalse(groceries.isEmpty());
    assertEquals("Milk", groceries.getFirst().getName());
  }

  @Test
  void removeGrocery() {
    foodStorage.removeGrocery(milk, 1);

    List<Grocery> groceries = foodStorage.searchGrocery("Milk");
    assertThrows();
  }

  @Test
  void searchGrocery() {
  }

  @Test
  void valueOfExpiredGroceries() {
  }

  @Test
  void listOfExpiredGroceries() {
  }

  @Test
  void valueOfAllGroceries() {
  }

  @Test
  void getSortedList() {
  }
}