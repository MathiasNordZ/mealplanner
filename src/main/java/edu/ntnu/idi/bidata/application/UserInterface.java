package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    CookBookMenu cookBookMenu = new CookBookMenu();
    mainMenu = new MainMenu(uiInputHandler, groceryMenu, cookBookMenu);
  }

  /**
   * This is the method that will start the program.
   */
  public void start() {
    mainMenu.mainMenu();
  }
}
