package edu.ntnu.idi.bidata.menu;

public class StringMenu {
  public StringMenu() {
    // empty
  }

  public void printMainMenu() {
    final String mainMenu = """
          [1] - Grocery Menu.
          [2] - Cookbook Menu.
          [0] - Exit.
          """;
    System.out.println(mainMenu);
  }

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
