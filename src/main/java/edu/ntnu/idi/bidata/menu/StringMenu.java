package edu.ntnu.idi.bidata.menu;

/**
 * <p>The <b>StringMenu</b> class provides methods to print various menus to the console.
 * This class includes methods to print the main menu, cookbook menu, and grocery menu.
 * Each method prints a predefined menu structure to the console.</p>
 *
 * @author <p><b>Mathias Erik Nord</b></p>
 * @version <p><b>0.0.1</b></p>
 * @since <p><b>27.11.2024</b></p>
 */
public class StringMenu {

  /**
   * <p>Constructs a new <code>StringMenu</code> object.
   * This constructor is empty as no initialization is required.</p>
   */
  public StringMenu() {
    // empty
  }

  /**
   * <p>Prints the main menu to the console.
   * The main menu includes options to navigate to the grocery menu, cookbook menu, or exit the application.</p>
   */
  public void printMainMenu() {
    final String mainMenu = """
          [1] - Grocery Menu.
          [2] - Cookbook Menu.
          [0] - Exit.
          """;
    System.out.println(mainMenu);
  }

  /**
   * <p>Prints the cookbook menu to the console.
   * The cookbook menu includes options to create a recipe, remove a recipe, print recipes, print a specific recipe,
   * get a recipe recommendation, or go back to the previous menu.</p>
   */
  public void printCookbookMenu() {
    final String cookbookMenu = """
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
   * <p>Prints the grocery menu to the console.
   * The grocery menu includes options to create a new grocery, remove a grocery, search for a grocery, list all groceries,
   * list expired groceries, get the value of all groceries, or go back to the previous menu.</p>
   */
  public void printGroceryMenu() {
    final String groceryMenu = """
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
