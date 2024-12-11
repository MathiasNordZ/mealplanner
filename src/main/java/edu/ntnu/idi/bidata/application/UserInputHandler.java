package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.util.StringFormatter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The <code>UserInputHandler</code> class handles input operations for the user.
 * It provides methods to read strings, integers, and floats from the user.
 *
 * @author Mathias Erik Nord
 * @version 0.0.1
 */
public class UserInputHandler {
  private final Scanner scanner;

  /**
   * Constructor for <code>UserInputHandler</code>.
   * Initializes a scanner instance to read inputs from user.
   *
   * @since 0.0.1
   */
  public UserInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Reads a string input from the user.
   *
   * @param prompt The message to display to prompt the user before reading input.
   * @return String input from the user.
   * @since 0.0.1
   */
  public String stringReader(String prompt) {
    System.out.println(prompt);
    String input = scanner.nextLine();
    cancelOperation(input);
    return StringFormatter.normalizedString(input);
  }

  /**
   * Reads an integer input from the user.
   * If the input is not a valid integer,
   * it will re-prompt until there is a valid input.
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
      String input = scanner.nextLine();
      cancelOperation(input);
      try {
        value = Integer.parseInt(input);
        if (value >= 0) {
          validInput = true;
        } else {
          System.out.println("Input must be 0 or greater.");
        }
      } catch (NumberFormatException e) {
        System.out.println("You provided an invalid input! Please enter a valid whole number.");
      }
    }
    return value;
  }

  /**
   * Reads a decimal input from the user.
   * If the input is not a valid decimal,
   * it will re-prompt until the input is valid.
   *
   * @param prompt The message to display to the user before reading input.
   * @return Decimal input from the user.
   * @since 0.0.1
   */
  public BigDecimal decimalReader(String prompt) {
    BigDecimal value = BigDecimal.ZERO;
    boolean validInput = false;
    while (!validInput) {
      System.out.println(prompt);
      String input = scanner.nextLine();
      cancelOperation(input);
      try {
        value = new BigDecimal(input);
        if (value.compareTo(BigDecimal.ZERO) > 0) {
          validInput = true;
        } else {
          System.out.println("Input must be greater than 0! Please try again.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input! Please enter a valid number.");
      }
    }
    return value;
  }

  /**
   * Reads the date input from user.
   * Will re-prompt if user enters an invalid input.
   * Refactored to be more versatile than regex, helped by CoPilot.
   *
   * @param prompt The prompt message.
   * @return Will return the inputted date of expiry.
   * @since 0.0.1
   */
  public String dateReader(String prompt) {
    LocalDate expiryDate = null;
    boolean isDateCorrect = false;
    while (!isDateCorrect) {
      String input = stringReader(prompt);
      cancelOperation(input);
      try {
        expiryDate = LocalDate.parse(input);
        isDateCorrect = true;
      } catch (DateTimeParseException e) {
        System.out.println("You provided an invalid date! Please use format 'YYYY-MM-DD'.");
      }
    }
    return expiryDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
  }

  /**
   * Reads the unit of measurement input from user.
   * Will re-prompt if user enters wrong unit format.
   *
   * @param prompt The prompt message.
   * @return Will return the inputted unit.
   * @since 0.0.1
   */
  public String unitReader(String prompt) {
    String unit = "";
    boolean isUnitCorrect = false;
    while (!isUnitCorrect) {
      unit = stringReader(prompt);
      cancelOperation(unit);
      if (unit.equalsIgnoreCase("kilogram")
          || unit.equalsIgnoreCase("liter")
          || unit.equalsIgnoreCase("pcs")) {
        isUnitCorrect = true;
      } else {
        System.out.println("You provided an invalid unit of measurement! "
            + "Please use (kilogram/liter/pcs).");
      }
    }
    return StringFormatter.normalizedString(unit);
  }

  /**
   * Method that will listen for 'cancel'.
   *
   * @param input The input to check.
   */
  private void cancelOperation(String input) {
    if (input.equalsIgnoreCase("Cancel")) {
      throw new IllegalArgumentException("Operation cancelled!");
    }
  }
}
