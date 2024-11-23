package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.menu.CookBookMenu;
import edu.ntnu.idi.bidata.menu.GroceryMenu;
import edu.ntnu.idi.bidata.menu.MainMenu;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.register.FoodStorage;

/**
 * This is the interface class, that will take all interaction with the user.
 */
public class UserInterface {
  private MainMenu mainMenu;

  /**
   * This is the method that initializes the program.
   */
  public void init() {
    UserInputHandler uiInputHandler = new UserInputHandler();
    FoodStorage foodStorage = new FoodStorage();
    GroceryMenu groceryMenu = new GroceryMenu(foodStorage, uiInputHandler);
    CookBook cookBook = new CookBook();
    CookBookMenu cookBookMenu = new CookBookMenu(uiInputHandler, cookBook, foodStorage);

    Grocery milk = new Grocery(1f, "Milk", "liter", 25, "2024-12-31");
    Grocery chicken = new Grocery(1.25f, "Chicken", "kilogram", 125, "2024-12-10");
    Grocery rice = new Grocery(2f, "Rice", "kilogram", 45, "2025-10-30");
    Grocery cola = new Grocery(1.5f, "Cola", "liter", 37, "2025-08-20");

    foodStorage.addGrocery(milk);
    foodStorage.addGrocery(chicken);
    foodStorage.addGrocery(rice);
    foodStorage.addGrocery(cola);

    mainMenu = new MainMenu(uiInputHandler, groceryMenu, cookBookMenu);
  }

  /**
   * This is the method that will start the program.
   */
  public void start() {
    mainMenu.mainMenu();
  }
}
