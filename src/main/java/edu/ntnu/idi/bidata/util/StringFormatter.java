package edu.ntnu.idi.bidata.util;

/**
 * Utility class for string formatting operations.
 * Provides methods to normalize strings by trimming whitespace,
 * converting to lowercase, and capitalizing the first letter.
 *
 * @author Mathias Erik Nord
 * @version 1.0.0
 */
public class StringFormatter {
  /**
   * Reset color to default.
   */
  public static final String RESET = "\033[0m";
  /**
   * Set color of string to red.
   */
  public static final String RED = "\033[0;31m";
  /**
   * Set color of string to green.
   */
  public static final String GREEN = "\033[0;32m";

  private StringFormatter() {

  }

  /**
   * String normalization method.
   * Formats the string by trimming whitespace,
   * converting text to lowercase and capitalizing the first letter.
   *
   * @param input The input to format.
   * @return Formatted string.
   */
  public static String normalizedString(String input) {
    input = input.trim().toLowerCase();
    return Character.toUpperCase(input.charAt(0)) + input.substring(1);
  }


}
