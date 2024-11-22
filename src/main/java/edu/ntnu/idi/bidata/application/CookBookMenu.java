package edu.ntnu.idi.bidata.application;

public class CookBookMenu {
  UserInputHandler uiInputHandler;

  private enum CookBookCommand {
    CREATE_RECIPE(1),
    REMOVE_RECIPE(2),
    BACK(0);

    private final int value;

    CookBookCommand(int value) {
      this.value = value;
    }

    private static CookBookCommand fromValue(int value) {
      for (CookBookCommand command : CookBookCommand.values()) {
        if (command.value == value) {
          return command;
        }
      }
      throw new IllegalArgumentException("The command value is invalid" + value);
    }
  }

  private void cookBookMenu() {
    CookBookCommand command = null;

    do {
      System.out.println("""
      [1] - Create recipe.
      [2] - Remove recipe.
      [3] - Print recipes.
      [4] - Print recipe.
      [5] - Recipe recommendation.
      [0] - Go back.
      """);

      int commandValue = uiInputHandler.intReader("Enter your command: ");

      try {
        command = CookBookCommand.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command." + e.getMessage());
        continue;
      }

      switch (command) {
        //case CookBookCommand.CREATE_RECIPE -> createRecipe();
        default -> System.out.println("Invalid command.");
      }
    } while (command != CookBookCommand.BACK);
  }
}
