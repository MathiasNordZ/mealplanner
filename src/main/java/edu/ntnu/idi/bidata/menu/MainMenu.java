package edu.ntnu.idi.bidata.menu;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.menu.cookbook.CookBookMenu;
import edu.ntnu.idi.bidata.menu.grocery.GroceryMenu;

/**
 * This class represents the main menu of the application.
 * It allows the user to navigate to the grocery menu or the cookbook menu.
 *
 * @author Mathias Erik Nord
 * @version 1.0.0
 * @since 22.11.2024
 */
public class MainMenu {
  private final UserInputHandler inputHandler;
  private final GroceryMenu groceryMenu;
  private final CookBookMenu cookBookMenu;
  private final StringMenu stringMenu = new StringMenu();

  /**
   * Constructor for the <code>MainMenu</code> class.
   *
   * @param inputHandler The user input handler.
   * @param groceryMenu The grocery menu.
   * @param cookBookMenu The cookbook menu.
   */
  public MainMenu(UserInputHandler inputHandler, GroceryMenu groceryMenu,
                  CookBookMenu cookBookMenu) {
    this.inputHandler = inputHandler;
    this.groceryMenu = groceryMenu;
    this.cookBookMenu = cookBookMenu;
  }

  /**
   * This is an enum that does represent the commands of the main menu.
   */
  private enum MainCommands {
    GROCERY_MENU(1),
    COOKBOOK_MENU(2),
    EXIT(0);

    private final int value;

    MainCommands(int value) {
      this.value = value;
    }

    private static MainCommands fromValue(int value) {
      for (MainCommands command : MainCommands.values()) {
        if (command.value == value) {
          return command;
        }
      }
      throw new IllegalArgumentException("The command value is invalid" + value);
    }
  }

  /**
   * This method will display the main menu and handle the input of the main menu.
   */
  public void mainMenu() {
    MainCommands command = null;

    do {
      stringMenu.printMainMenu();
      int commandValue;

      try {
        commandValue = inputHandler.intReader("Enter command: ");
        command = MainCommands.fromValue(commandValue);
        commandHandler(command);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    } while (command != MainCommands.EXIT);
  }

  private void commandHandler(MainCommands command) {
    switch (command) {
      case MainCommands.GROCERY_MENU -> groceryMenu.groceryMenu();
      case MainCommands.COOKBOOK_MENU -> cookBookMenu.cookBookMenu();
      case MainCommands.EXIT -> System.out.println("Exiting application.\n");
      default -> System.out.println("Invalid command.\n");
    }
  }

}
