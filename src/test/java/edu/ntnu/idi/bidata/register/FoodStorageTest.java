package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mathias Erik Nord
 * @since 05.11.2024
 *
 * This is the test class for the class FoodStorage. The test class is supposed to test certain methods of the FoodStorage class.
 * The test class does follow the Arrange, Act and Assert philosophy, where an object of the class FoodStorage is created. It is acted upon, and then asserted.
 *
 */
class FoodStorageTest {
  private FoodStorage foodStorage;
  private Grocery milk;
  private Grocery expiredMilk;
  private Grocery chicken;

  LocalDate today;

  /**
   * The method <code>setUp</code> will be run before each test.
   * This is to set up an instance of FoodStorage properly before each test.
   */
  @BeforeEach
  void setUp() {
    today = LocalDate.now();
    int year = today.getYear();
    int month = today.getMonthValue();
    int day = today.getDayOfMonth();

    String LITER = "liter";
    String KILOGRAM = "liter";

    foodStorage = new FoodStorage();
    milk = new Grocery(1f, "Milk", LITER, 20, year, month, day);
    chicken = new Grocery(1.2f, "Chicken", KILOGRAM, 120, year, month, day);

    foodStorage.addGrocery(chicken);
    foodStorage.addGrocery(milk);
  }

  /**
   * This is a positive test for the <code>addGrocery</code> method.
   * Will set a valid input and should return name of the added grocery.
   */
  @Test
  void addGroceryPositiveTest() {
    foodStorage.addGrocery(milk);

    List<Grocery> groceries = foodStorage.searchGrocery("Milk");
    assertFalse(groceries.isEmpty());
    assertEquals("Milk", groceries.getFirst().getName());
  }

  /**
   * This is a negative test for the <code>addGrocery</code> method.
   * @throws IllegalArgumentException should be thrown,
   * because the grocery that will be added is set to null.
   */
  @Test
  void addGroceryNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> foodStorage.addGrocery(null));
  }

  /**
   * Positive test for the method <code>removeGrocery</code>.
   * Will remove a grocery that does exist in <code>foodStorage</code>
   */
  @Test
  void removeGroceryPositiveTest() {
    foodStorage.removeGrocery(chicken, 1.2f);

    List<Grocery> groceries = foodStorage.searchGrocery("Chicken");
    assertFalse(groceries.contains(chicken));
  }

  /**
   * Negative test for the method <code>removeGrocery</code>.
   * Will remove a bigger quantity than the available quantity.
   * @throws IllegalArgumentException should be thrown,
   * because the quantity to remove is bigger than existing quantity.
   */
  @Test
  void removeGroceryNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> foodStorage.removeGrocery(chicken, 10f));
  }

  /**
   * Positive test for the method <code>searchGrocery</code>.
   * Will search for a grocery that does exist in <code>foodStorage</code>.
   */
  @Test
  void searchGroceryPositiveTest() {
    List<Grocery> groceries = foodStorage.searchGrocery("Chicken");

    assertEquals("Chicken", groceries.getFirst().getName());
  }

  /**
   * Negative test for the method <code>searchGrocery</code>.
   * Will search for a grocery that does not exist in <code>foodStorage</code>.
   * @throws IllegalArgumentException should be thrown,
   * because the grocery searched for does not exist.
   */
  @Test
  void searchGroceryNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> foodStorage.searchGrocery(""));
  }
  /*
  @Test
  void listOfExpiredGroceriesPositiveTest() {
    LocalDate expiredDate = today.minusDays(1);
    expiredMilk = new Grocery(2f, "Milk", "liter", 40,
        expiredDate.getYear(), expiredDate.getMonthValue(), expiredDate.getDayOfMonth());

    foodStorage.addGrocery(expiredMilk);
    List<Grocery> expiredGroceries = foodStorage.listOfExpiredGroceries(today.getYear(),
        today.getMonthValue(), today.getDayOfMonth());
    assertEquals("Milk", expiredGroceries.getFirst().getName());
  }
 */

  /**
   * Negative test for method <code>listOfExpiredGroceries</code>.
   * Will test for invalid parameters of year, month and day.
   * @throws IllegalArgumentException should be thrown,
   * because the year, day and month is set to illegal values.
   */
  @Test
  void listOfExpiredGroceriesNegativeTest() {
    assertThrows(IllegalArgumentException.class, () -> foodStorage.listOfExpiredGroceries(1980, 10, 25)); // Test if invalid year throws correct exception.
    assertThrows(IllegalArgumentException.class, () -> foodStorage.listOfExpiredGroceries(2023, -1, 25)); // Test if invalid month throws correct exception.
    assertThrows(IllegalArgumentException.class, () -> foodStorage.listOfExpiredGroceries(2023, 10, -1)); // Test if invalid day throws correct exception.
  }

  /**
   * Positive test method for <code>valueOfAllGroceries</code>
   * Will check that the expected value is equals the actual value.
   */
  @Test
  void valueOfAllGroceriesPositiveTest() {
    assertEquals(140, foodStorage.valueOfAllGroceries());
  }
}