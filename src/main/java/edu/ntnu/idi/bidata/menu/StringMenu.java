package edu.ntnu.idi.bidata.menu;

/**
 * The <code>StringMenu</code> class provides methods to print various menus to the console.
 * This class includes methods to print the main menu, cookbook menu, and grocery menu.
 * Each method prints a predefined menu structure to the console.</p>
 *
 * @author Mathias Erik Nord
 * @version 0.0.1
 * @since 27.11.2024
 */
public class StringMenu {

  /**
   * Constructs a new <code>StringMenu</code> object.
   * This constructor is empty as no initialization is required.
   */
  public StringMenu() {
    // Empty
  }

  /**
   * Prints the main menu to the console.
   * The main menu includes options to navigate to the grocery menu,
   * cookbook menu, or exit the application.
   */
  public void printMainMenu() {
    final String mainMenu = """
          
          You may cancel an operation at any time, by typing 'cancel'.
          
          [1] - Grocery Menu.
          [2] - Cookbook Menu.
          [0] - Exit.
          """;
    System.out.println(mainMenu);
  }

  /**
   * Prints the cookbook menu to the console.
   * The cookbook menu includes options to create a recipe,
   * remove a recipe, print recipes, print a specific recipe,
   * get a recipe recommendation, or go back to the previous menu.
   */
  public void printCookbookMenu() {
    final String cookbookMenu = """
      
        You may cancel an operation at any time, by typing 'cancel'.
        
        [1] - Create recipe.
        [2] - Remove recipe.
        [3] - Print recipes.
        [4] - Print recipe.
        [5] - Recipe recommendation.
        [0] - Go back.
        """;
    System.out.println(cookbookMenu);
  }

  /**
   * Prints the grocery menu to the console.
   * The grocery menu includes options to create a new grocery,
   * remove a grocery, search for a grocery, list all groceries,
   * list expired groceries, get the value of all groceries, or go back to the previous menu.
   */
  public void printGroceryMenu() {
    final String groceryMenu = """
          
          You may cancel an operation at any time, by typing 'cancel'.
          
          [1] - Create a new grocery.
          [2] - Remove grocery.
          [3] - Search for grocery.
          [4] - List of all groceries.
          [5] - List of expired groceries.
          [6] - Value of all groceries.
          [0] - Go Back.
          """;
    System.out.println(groceryMenu);
  }
}
