package edu.ntnu.idi.bidata.application;

/**
 * <p>This is the starting point of the application.
 * The whole application is initialized and started from this class.</p>
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 */
public class Main {
  /**
   * <p>Where the program is run from.</p>
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();

    ui.init();
    ui.start();
  }
}