package edu.ntnu.idi.bidata.util;

public class StringFormatter {
  private StringFormatter() {

  }

  public static String normalizedString(String input) {
    input = input.trim().toLowerCase();
    return Character.toUpperCase(input.charAt(0)) + input.substring(1);
  }
}
