package edu.ntnu.idi.bidata.application;

import java.util.Scanner;

/**
 * <p>The <code>UserInputHandler</code> class handles input operations for the user.
 * It provides methods to read strings, integers, and floats from the user.</p>
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 */
public class UserInputHandler {
  private final Scanner scanner = new Scanner(System.in);

  /**
   * <p>Constructor for <code>UserInputHandler</code>.
   * Initializes a scanner instance to read inputs from user.
   * Constructor is empty, because there is no additional initialization required.</p>
   * @since 0.0.1
   */
  public UserInputHandler() {
    // The constructor is empty, because there is no additional initialization required.
  }

  /**
   * <p>Reads a string input from the user.</p>
   *
   * @param prompt The message to display to prompt the user before reading input.
   * @return String input from the user.
   * @since 0.0.1
   */
  public String stringReader(String prompt) {
    System.out.println(prompt);
    return scanner.nextLine();
  }

  /**
   * <p>Reads an integer input from the user.
   * If the input is not a valid integer,
   * it will re-prompt until there is a valid input.</p>
   *
   * @param prompt The message to display to the user before reading input.
   * @return Integer input from the user.
   * @since 0.0.1
   */
  public int intReader(String prompt) {
    int value = 0;
    boolean validInput = false;
    while (!validInput) {
      System.out.println(prompt);
      if (scanner.hasNextInt()) {
        value = scanner.nextInt();
        scanner.nextLine();
        validInput = true;
      } else {
        System.out.println("You provided and invalid input! Please enter a valid whole number.");
        scanner.next();
      }
    }
    return value;
  }

  /**
   * <p>Reads a float input from the user.
   * If the input is not a valid float,
   * it will re-prompt until the input is valid.</p>
   *
   * @param prompt The message to display to the user before reading input.
   * @return Float input from the user.
   * @since 0.0.1
   */
  public float floatReader(String prompt) {
    float value = 0;
    boolean validInput = false;
    while (!validInput) {
      System.out.println(prompt);
      if (scanner.hasNextFloat()) {
        value = scanner.nextFloat();
        scanner.nextLine();
        validInput = true;
      } else {
        System.out.println("You provided and invalid input! Please enter a valid number.");
        scanner.next();
      }
    }
    return value;
  }

  /**
   * <p>Reads the date input from user.
   * Will re-prompt if user enters an invalid input.</p>
   *
   * @param prompt The prompt message.
   * @return Will return the inputted date of expiry.
   * @since 0.0.1
   */
  public String dateReader(String prompt) {
    String dateOfExpiry = "";
    boolean isDateCorrect = false;
    while (!isDateCorrect) {
      dateOfExpiry = stringReader(prompt);
      if (dateOfExpiry.matches("\\d{4}-\\d{2}-\\d{2}")) { // Will check that it matches the correct format.
        isDateCorrect = true;
      } else {
        System.out.println("You provided an invalid date! Pleas use format 'YYYY-MM-DD'.");
      }
    }
    return dateOfExpiry;
  }

  /**
   * <p>Reads the unit of measurement input from user.
   * Will re-prompt is user enters wrong unit format.</p>
   *
   * @param prompt The prompt message.
   * @return Will return the inputted unit.
   * @since 0.0.1
   */
  public String unitReader(String prompt) {
    String unitOfMeasurement = "";
    boolean isUnitCorrect = false;
    while (!isUnitCorrect) {
      unitOfMeasurement = stringReader(prompt);
      if (unitOfMeasurement.equalsIgnoreCase("kilogram")
          || unitOfMeasurement.equalsIgnoreCase("liter")
          || unitOfMeasurement.equalsIgnoreCase("pcs")) {
        isUnitCorrect = true;
      } else {
        System.out.println("You provided an invalid unit of measurement! Please use (kilogram/liter/pcs).");
      }
    }
    return unitOfMeasurement;
  }
}
