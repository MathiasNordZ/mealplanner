package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceMenu {
  private List<Grocery> groceries = new ArrayList<>();
  private final Scanner scanner = new Scanner(System.in);
  private FoodStorage foodStorage = new FoodStorage();
  private final String groceryCommand = """
      [1] - Create a new grocery.
      [2] - Remove grocery.
      [3] - Search for grocery.
      [4] - List of all groceries.
      [5] - List of expired groceries.
      [0] - Go Back.
      """;
  private final String cookBookCommand = """
      [1] - Create recipe.
      [2] - Remove recipe.
      [3] - Print recipes.
      [4] - Print recipe.
      [5] - Recipe recommendation.
      [0] - Go back.
      """;
private final String mainMenuCommand = """
    [1] - Grocery Menu.
    [2] - Cookbook Menu.
    [0] - Exit.
    """;

  private enum MainCommands {
    GROCERY_MENU(1),
    COOKBOOK_MENU(2),
    EXIT(0);

    private final int value;
    // TODO make setter method for value.
    MainCommands(int value) {
      this.value = value;
    }

    private int getValue() {
      return value;
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
   * Inspired by world of zuul.
   */
  private enum GroceryCommand {
    CREATE_NEW_GROCERY(1),
    REMOVE_GROCERY(2),
    SEARCH_FOR_GROCERY(3),
    LIST_OF_ALL_GROCERIES(4),
    LIST_OF_EXPIRED_GROCERIES(5),
    BACK(0);

    private final int value;

    GroceryCommand(int value) {
      this.value = value;
    }

    private int getValue() {
      return value;
    }

    private static GroceryCommand fromValue(int value) {
      for (GroceryCommand command : GroceryCommand.values()) {
        if (command.value == value) {
          return command;
        }
      }
      throw new IllegalArgumentException("The command value is invalid" + value);
    }
  }

  public void mainMenu() {
    MainCommands command = null;

    do {
      System.out.println("""
          [1] - Grocery Menu.
          [2] - Cookbook Menu.
          [0] - Exit.
          """);
      int commandValue = scanner.nextInt();
      scanner.nextLine();

      try {
        command = MainCommands.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command.");
        continue;
      }

      switch (command) {
        case MainCommands.GROCERY_MENU -> groceryMenu();
        default -> System.out.println("Invalid command.");
      }

    } while (command != MainCommands.EXIT);
  }

  public void groceryMenu() {
    GroceryCommand command = null;

    do {
      System.out.println(groceryCommand);
      int commandValue = scanner.nextInt();
      scanner.nextLine();

      try {
        command = GroceryCommand.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command.");
        continue;
      }

      switch (command) {
        case GroceryCommand.CREATE_NEW_GROCERY -> createNewGrocery();
        case GroceryCommand.BACK -> System.out.println("Exiting");
        default -> System.out.println("Invalid command.");
      }
    } while (command != GroceryCommand.BACK);
  }

  private void createNewGrocery() {
    System.out.println("Enter name of grocery");
    String nameOfGrocery = scanner.nextLine();

    System.out.println("Quantity of grocery");
    float quantityOfGrocery = scanner.nextFloat();
    scanner.nextLine();

    System.out.println("Enter unit of measurement");
    String unitOfMeasurement = scanner.nextLine();

    System.out.println("Enter price of grocery");
    float priceOfGrocery = scanner.nextFloat();
    scanner.nextLine();

    System.out.println("Enter expiry date of grocery");
    String dateOfExpiry = scanner.nextLine();

    try {
      Grocery grocery = new Grocery(quantityOfGrocery, nameOfGrocery, unitOfMeasurement, priceOfGrocery, dateOfExpiry);
      groceries.add(grocery);
      System.out.println("Grocery was created successfully.");
    } catch (IllegalArgumentException e) {
      System.out.println("An error occured: " + e.getMessage());
    }
  }
}
