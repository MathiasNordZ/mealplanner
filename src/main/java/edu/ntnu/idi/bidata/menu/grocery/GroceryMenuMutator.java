package edu.ntnu.idi.bidata.menu.grocery;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

/**
 * This class provides methods to mutate groceries in the food storage.
 *
 * @version 0.0.1
 * @since 28.11.2024
 */
public class GroceryMenuMutator {
  private final UserInputHandler inputHandler;

  /**
   * Constructor for GroceryMenuMutator.
   *
   * @param inputHandler The input handler.
   */
  public GroceryMenuMutator(UserInputHandler inputHandler) {
    this.inputHandler = inputHandler;
  }

  /**
   * Prompts the user to enter details to create a new grocery.
   * If grocery is created successfully, it will be added to the food storage.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param foodStorage The food storage the grocery will be added to.
   */
  public void createGrocery(String errorMessage, FoodStorage foodStorage) {
    try {
      Grocery grocery = promptGroceryDetails();
      foodStorage.addGrocery(grocery);
      System.out.println("Grocery was created successfully, and added to storage.");
    } catch (IllegalArgumentException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Prompts the user for the grocery to remove and the quantity to remove.
   * If the grocery exists and the quantity to remove is valid,
   * it will be removed from food storage.
   *
   * @param errorMessage The error message to display in case of an exception.
   * @param foodStorage The food storage where the grocery should be removed from.
   */
  public void removeGrocery(String errorMessage, FoodStorage foodStorage) {
    String groceryToRemove = inputHandler.stringReader("Name of grocery to remove: ");
    BigDecimal quantityToRemove = inputHandler.decimalReader("Quantity to remove: ");

    try {
      foodStorage.removeGrocery(groceryToRemove, quantityToRemove);
      System.out.println("Grocery was removed successfully!");
    } catch (IllegalArgumentException | NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * Extracted method from <code>createGrocery</code>.
   * Prompts the user to enter details for a new grocery and returns the new Grocery object.
   *
   * @return The created Grocery object.
   * @since 0.0.1
   */
  private Grocery promptGroceryDetails() {
    String nameOfGrocery = inputHandler.stringReader("Please enter name of grocery: ");
    BigDecimal quantityOfGrocery = inputHandler
        .decimalReader("Please enter quantity of grocery: ");
    String unitOfMeasurement = inputHandler
        .unitReader("Please enter unit of measurement (kilogram/liter/pcs): ");
    BigDecimal priceOfGrocery = inputHandler.decimalReader("Please enter price of grocery: ");
    String dateOfExpiry = inputHandler.dateReader("Please enter expiry date (YYYY-MM-DD): ");

    return new Grocery(quantityOfGrocery, nameOfGrocery, unitOfMeasurement,
        priceOfGrocery, dateOfExpiry);
  }
}
