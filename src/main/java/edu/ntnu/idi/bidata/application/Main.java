package edu.ntnu.idi.bidata.application;

/**
 * The main starting point of your application. Let this class create the
 * instance of your main-class that starts your application.
 */
public class Main {
  /**
   * Where the program is run from.
   *
   * @param args ...
   */
  public static void main(String[] args) {
    UserInterface ui = new UserInterface();
    UserInterfaceMenu uiMenu = new UserInterfaceMenu();

    uiMenu.mainMenu();

    //ui.init();
    //ui.start();
  }
}