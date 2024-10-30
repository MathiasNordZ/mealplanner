package edu.ntnu.idi.bidata;

import java.time.LocalDate;

/**
 * The main starting point of your application. Let this class create the
 * instance of your main-class that starts your application.
 */
public class Main {
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();

    ui.start();
  }
}