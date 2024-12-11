package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.menu.StringMenu;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.register.FoodStorage;

/**
 * This is the starting point of the application.
 * The whole application is initialized and started from this class.
 *
 * @author Mathias Erik Nord
 * @version 0.0.1
 */
public class Main {
  /**
   * Where the program is run from.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    StringMenu stringMenu = new StringMenu();
    UserInputHandler inputHandler = new UserInputHandler();
    FoodStorage foodStorage = new FoodStorage();
    CookBook cookBook = new CookBook();

    UserInterface ui = new UserInterface(stringMenu, inputHandler, foodStorage, cookBook);

    ui.init();
    ui.start();
  }
}