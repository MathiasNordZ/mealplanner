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
  private final String groceryCommands = """
      1. Create a new grocery.
      2. Search for grocery.
      3. Add grocery to storage.
      4. Remove grocery.
      5. List of groceries.
      6. Expired groceries.
      7. Value of groceries.
      8. Create recipe.
      9. Available groceries.
      10. Add recipe to cookbook.
      11. Recipe recommendation.
      0. Back.
      """;

  private enum MainCommands {
    GROCERY_MENU(1),
    FOODSTORAGE_MENU(2),
    RECIPE_MENU(3),
    COOKBOOK_MENU(4),
    EXIT(0);

    private final int value;
    //TODO make setter method for value.
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
  private enum GroceryCommands {
    CREATE_NEW_GROCERY(1),
    BACK(0);


    private final int value;

    GroceryCommands(int value) {
      this.value = value;
    }

    private int getValue() {
      return value;
    }

    private static GroceryCommands fromValue(int value) {
      for (GroceryCommands command : GroceryCommands.values()) {
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
          1. Grocery Menu
          2. Foodstorage Menu
          3. Recipe Menu
          4. Cookbook Menu
          0. Exit
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
    GroceryCommands command = null;

    do {
      System.out.println(groceryCommands);
      int commandValue = scanner.nextInt();
      scanner.nextLine();

      try {
        command = GroceryCommands.fromValue(commandValue);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid command.");
        continue;
      }

      switch (command) {
        case GroceryCommands.CREATE_NEW_GROCERY -> createNewGrocery();
        case GroceryCommands.BACK -> System.out.println("Exiting");
        default -> System.out.println("Invalid command.");
      }
    } while (command != GroceryCommands.BACK);
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
