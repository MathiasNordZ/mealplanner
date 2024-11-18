package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceMenu {
  private final String groceryCommands = """
      1. To create a new grocery.
      2. Exit
      """;

  private final int CREATE_NEW_GROCERY = 1;
  private final int EXIT = 2;

  private List<Grocery> groceries = new ArrayList<>();
  private final Scanner scanner = new Scanner(System.in);

  public void groceryMenu() {
    int command;

    do {
      System.out.println(groceryCommands);
      command = scanner.nextInt();
      scanner.nextLine();

      switch (command) {
        case CREATE_NEW_GROCERY -> createNewGrocery();
        case EXIT -> System.out.println("Exiting");
        default -> System.out.println("Invalid command.");
      }
    } while (command != EXIT);
  }

  private void createNewGrocery() {
    System.out.println("Enter name of grocery");
    String nameOfGrocery = scanner.nextLine();

    System.out.println("Quantity of grocery");
    float quantityOfGrocery = scanner.nextFloat();

    System.out.println("Enter unit of measurement");
    String unitOfMeasurement = scanner.nextLine();

    System.out.println("Enter price of grocery");
    float priceOfGrocery = scanner.nextFloat();

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
