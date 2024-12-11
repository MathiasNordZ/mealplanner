package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * This is the test class for the class FoodStorage. The test class is supposed to test certain methods of the FoodStorage class.
 * The test class does follow the Arrange, Act and Assert philosophy, where an object of the class FoodStorage is created. It is acted upon, and then asserted.
 * </p>
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>05.11.2024</b>
 * @version <b>0.0.1</b>
 */
class FoodStorageTest {
  private FoodStorage foodStorage;
  private Grocery milk;
  private Grocery chicken;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private final LocalDate today = LocalDate.now();
  private String formattedToday;

  /**
   * The method <code>setUp</code> will be run before each test.
   * This is to set up an instance of FoodStorage properly before each test.
   */
  @BeforeEach
  void setUp() {
    formattedToday = today.format(formatter);

    final String LITER = "liter";
    final String KILOGRAM = "liter";

    foodStorage = new FoodStorage();
    milk = new Grocery(BigDecimal.valueOf(1), "Milk", LITER, BigDecimal.valueOf(20), formattedToday);
    chicken = new Grocery(BigDecimal.valueOf(1.2), "Chicken", KILOGRAM, BigDecimal.valueOf(120), formattedToday);

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
    foodStorage.removeGrocery("Chicken", BigDecimal.valueOf(0.6));
    List<Grocery> groceries = foodStorage.searchGrocery("Chicken");
    assertFalse(groceries.isEmpty());
    assertEquals(0, BigDecimal.valueOf(0.6).compareTo(groceries.getFirst().getQuantity()));

    foodStorage.removeGrocery("Chicken", BigDecimal.valueOf(0.6));
    assertThrows(NoSuchElementException.class, () -> foodStorage.searchGrocery("Chicken"));
  }

  /**
   * Negative test for the method <code>removeGrocery</code>.
   * Will remove a bigger quantity than the available quantity.
   * @throws IllegalArgumentException should be thrown,
   * because the quantity to remove is bigger than existing quantity.
   */
  @Test
  void removeGroceryNegativeTest() {
    BigDecimal quantity = BigDecimal.valueOf(10);
    BigDecimal negativeValue = BigDecimal.valueOf(-1);
    assertThrows(IllegalArgumentException.class, () -> foodStorage.removeGrocery("Chicken", quantity));
    assertThrows(IllegalArgumentException.class, () -> foodStorage.removeGrocery(null, quantity));
    assertThrows(IllegalArgumentException.class, () -> foodStorage.removeGrocery("", quantity));
    assertThrows(IllegalArgumentException.class, () -> foodStorage.removeGrocery("Chicken", negativeValue));
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

  @Test
  void listOfExpiredGroceriesPositiveTest() {
    String expiredDate = "2023-10-10";
    LocalDate date = LocalDate.parse(expiredDate, formatter);
    Grocery expiredMilk = new Grocery(BigDecimal.valueOf(2), "Milk", "liter", BigDecimal.valueOf(40),
        expiredDate);

    foodStorage.addGrocery(expiredMilk);
    List<Grocery> expiredGroceries = foodStorage.listOfExpiredGroceries(formattedToday);

    assertFalse(expiredGroceries.isEmpty());
    assertEquals("Milk", expiredGroceries.getFirst().getName());
    assertEquals(date, expiredGroceries.getFirst().getExpiryDate());
  }


  /**
   * Negative test for method <code>listOfExpiredGroceries</code>.
   * Will test for invalid parameters of year, month and day.
   * @throws IllegalArgumentException should be thrown,
   * because the year, day and month is set to illegal values.
   */
  @Test
  void listOfExpiredGroceriesNegativeTest() {// Test if invalid year throws correct exception.
    assertThrows(IllegalArgumentException.class, () -> foodStorage.listOfExpiredGroceries("25-12-2024")); // Test if invalid month throws correct exception.
    assertThrows(IllegalArgumentException.class, () -> foodStorage.listOfExpiredGroceries("20231012")); // Test if invalid day throws correct exception.
  }

  /**
   * Positive test method for <code>valueOfAllGroceries</code>
   * Will check that the expected value is equals the actual value.
   */
  @Test
  void valueOfAllGroceriesPositiveTest() {
    assertEquals(0, BigDecimal.valueOf(140).compareTo(foodStorage.valueOfAllGroceries()));
  }

  /**
   * Negative test method for <code>valueOfAllGroceries</code>
   */
  @Test
  void valueOfAllGroceriesNegativeTest() {
    formattedToday = today.format(formatter);
    Grocery ham = new Grocery(BigDecimal.valueOf(0.25), "Ham", "kilogram", BigDecimal.valueOf(25), formattedToday);
    foodStorage.addGrocery(ham);

    assertNotEquals(BigDecimal.valueOf(140), foodStorage.valueOfAllGroceries());
  }

  /**
   * Test to check if the sorted list will return a sorted list.
   */
  @Test
  void getSortedListTest() {
    List<Grocery> sortedGroceryList = foodStorage.getSortedList();

    assertEquals(chicken, sortedGroceryList.getFirst()); // Check if first item is chicken.
    assertEquals(milk, sortedGroceryList.get(1)); // Check if second item is milk.
  }

  @Test
  void valueOfExpiredGroceriesPositiveTest() {
    List<Grocery> expiredGroceries = List.of(
        new Grocery(BigDecimal.valueOf(2), "Salmon", "kilogram", BigDecimal.valueOf(150), "2023-12-10"),
        new Grocery(BigDecimal.valueOf(1), "Salad", "kilogram", BigDecimal.valueOf(25), "2023-12-10")
    );
    BigDecimal totalValue = foodStorage.valueOfExpiredGroceries(expiredGroceries);

    assertEquals(0, BigDecimal.valueOf(175).compareTo(totalValue));
  }

  @Test
  void valueOfExpiredGroceriesNegativeTest() {
    List<Grocery> expiredGroceries = new ArrayList<>();

    assertThrows(NoSuchElementException.class, () -> foodStorage.valueOfExpiredGroceries(expiredGroceries));
    assertThrows(NoSuchElementException.class, () -> foodStorage.valueOfExpiredGroceries(null));
  }

}