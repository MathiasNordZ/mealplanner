package edu.ntnu.idi.bidata.menu.grocery;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.util.NoSuchElementException;

public class GrocerMenuMutator {
  private final UserInputHandler uiInputHandler = new UserInputHandler();


  /**
   * This method will prompt the user to enter details, to create a new grocery.
   * If the grocery is created successfully, it will be added to the food storage.
   */
  public void createGrocery(String errorMessage, FoodStorage foodStorage) {
    String nameOfGrocery = uiInputHandler.stringReader("Please enter name of grocery: ");
    float quantityOfGrocery = uiInputHandler
        .floatReader("Please enter quantity of grocery: ");
    String unitOfMeasurement = uiInputHandler
        .unitReader("Please enter unit of measurement (kilogram/liter/pcs): ");
    float priceOfGrocery = uiInputHandler.floatReader("Please enter price of grocery: ");
    String dateOfExpiry = uiInputHandler.dateReader("Please enter expiry date (YYYY-MM-DD): ");

    try {
      Grocery grocery = new Grocery(quantityOfGrocery,
          nameOfGrocery, unitOfMeasurement, priceOfGrocery, dateOfExpiry);
      foodStorage.addGrocery(grocery);
      System.out.println("Grocery was created successfully, and added to storage.");
    } catch (IllegalArgumentException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }

  /**
   * This method will prompt the user for which grocery the user wants to remove,
   * and how much quantity to remove.
   * If the grocery does exist, and the quantity to remove is valid.
   * It will be removed from the food storage.
   */
  public void removeGrocery(String errorMessage, FoodStorage foodStorage) {
    String groceryToRemove = uiInputHandler.stringReader("Name of grocery to remove: ");
    float quantityToRemove = uiInputHandler.floatReader("Quantity to remove: ");

    try {
      foodStorage.removeGrocery(groceryToRemove, quantityToRemove);
      System.out.println("Grocery was removed successfully!");
    } catch (IllegalArgumentException | NoSuchElementException e) {
      System.out.println(errorMessage + e.getMessage());
    }
  }
}
