package edu.ntnu.idi.bidata.application;

import java.util.Scanner;

/**
 * <p>The UserInputHandler class handles input and output operations for the user interface.
 * It provides methods to read strings, integers, and floats from the user,
 * and to print messages to the console.</p>
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 */
public class UserInputHandler {
  private final Scanner scanner = new Scanner(System.in);

  /**
   * Constructor for <code>UserInputHandler</code>.
   * Initializes a scanner instance to read inputs from user.
   */
  public UserInputHandler() {
    // Empty
  }

  /**
   * Reads a string input from the user.
   *
   * @param prompt the message to display to the user before reading input
   * @return the string input from the user
   */
  public String stringReader(String prompt) {
    System.out.println(prompt);
    return scanner.nextLine();
  }

  /**
   * Reads an integer input from the user.
   * If the input is not a valid integer, an IllegalArgumentException is thrown.
   *
   * @param prompt the message to display to the user before reading input
   * @return the integer input from the user
   */
  public int intReader(String prompt) {
    int value = 0;
    boolean validInput = false;
    while (!validInput) {
      System.out.println(prompt);
      if (scanner.hasNextInt()) {
        value = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        validInput = true;
      } else {
        System.out.println("You provided and invalid input! Please enter a valid whole number.");
        scanner.next(); // consume the invalid input
      }
    }
    return value;
  }

  /**
   * Reads a float input from the user.
   * If the input is not a valid float, an IllegalArgumentException is thrown.
   *
   * @param prompt the message to display to the user before reading input
   * @return the float input from the user
   */
  public float floatReader(String prompt) {
    float value = 0;
    boolean validInput = false;
    while (!validInput) {
      System.out.println(prompt);
      if (scanner.hasNextFloat()) {
        value = scanner.nextFloat();
        scanner.nextLine(); // consume the newline character
        validInput = true;
      } else {
        System.out.println("You provided and invalid input! Please enter a valid number.");
        scanner.next(); // consume the invalid input
      }
    }
    return value;
  }

  public String dateReader(String prompt) {
    String dateOfExpiry = "";
    boolean isDateCorrect = false;
    while (!isDateCorrect) {
      dateOfExpiry = stringReader(prompt);
      if (dateOfExpiry.matches("\\d{4}-\\d{2}-\\d{2}")) { // Will check that it matches the correct format.
        isDateCorrect = true;
      }
    }
    return dateOfExpiry;
  }
}
