package edu.ntnu.idi.bidata.application;

/**
 * The main starting point of your application. Let this class create the
 * instance of your main-class that starts your application.
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 */
public class Main {
  /**
   * Where the program is run from.
   *
   * @param args ...
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();

    ui.init();
    ui.start();
  }
}