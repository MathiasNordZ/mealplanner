package edu.ntnu.idi.bidata.application;

/**
 * This is the starting point of the application.
 * The whole application is initialized and started from this class.
 *
 * @author Mathias Erik Nord
 * @version 0.0.1
 */
public class Main {
  /**
   * Where the program is run from.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();

    ui.init();
    ui.start();
  }
}