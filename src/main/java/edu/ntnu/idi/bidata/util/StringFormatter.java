package edu.ntnu.idi.bidata.util;

/**
 * Utility class for string formatting operations.
 * Provides methods to normalize strings by trimming whitespace,
 * converting to lowercase, and capitalizing the first letter.
 */
public class StringFormatter {
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
