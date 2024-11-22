package edu.ntnu.idi.bidata.application;

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
    CookBookMenu cookBookMenu = new CookBookMenu(uiInputHandler);
    mainMenu = new MainMenu(uiInputHandler, groceryMenu, cookBookMenu);
  }

  /**
   * This is the method that will start the program.
   */
  public void start() {
    mainMenu.mainMenu();
  }
}
