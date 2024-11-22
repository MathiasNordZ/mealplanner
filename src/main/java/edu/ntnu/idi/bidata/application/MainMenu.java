package edu.ntnu.idi.bidata.application;

/**
 * @author Mathias Erik Nord
 * @version 0.0.1
 */
public class MainMenu {
  private final UserInputHandler uiInputHandler;


public MainMenu(UserInputHandler uiInputHandler) {
  this.uiInputHandler = uiInputHandler;
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
      System.out.println("""
          [1] - Grocery Menu.
          [2] - Cookbook Menu.
          [0] - Exit.
          """);
      int commandValue = uiInputHandler.intReader("Enter command: ");

      try {
        command = MainCommands.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        case MainCommands.GROCERY_MENU -> groceryMenu();
        case MainCommands.COOKBOOK_MENU -> cookBookMenu();
        default -> System.out.println("Invalid command.");
      }

    } while (command != MainCommands.EXIT);
  }

}
