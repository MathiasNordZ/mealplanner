package edu.ntnu.idi.bidata.util;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.List;
import java.util.Set;

/**
 * This class is a formatting class.
 * The job of the class is to format how string outputs should look in the terminal.
 *
 * @author Mathias Erik Nord
 * @version 0.0.1
 */
public class StringFormatter {
  private StringFormatter() {
  }

  /**
   * This method is the template of how a grocery should look when printed.
   *
   * @param groceries The groceries to add to table.
   * @return Will return a table according to the given format.
   * @since 0.0.1
   */
  public static String formatGroceries(List<Grocery> groceries) {
    StringBuilder table = new StringBuilder();
    String format = "| %-20s | %-10s | %-10s | %-15s | %-15s |\n";
    table.append(String.format(format, "Name", "Quantity", "Unit", "Price", "Expiry Date"));
    table.append("----------------------------------------"
        + "----------------------------------------------\n");

    for (Grocery grocery : groceries) {
      table.append(String.format(format, grocery.getName(),
          grocery.getQuantity(), grocery.getUnitOfMeasurement(),
          grocery.getPrice(), grocery.getExpirationDate()));
    }
    return table.toString();
  }

  /**
   * This method is the template of how a recipe should look when printed.
   *
   * @param recipes The recipes to add to table.
   * @return Will return a table according to the given format.
   * @since 0.0.1
   */
  public static String formatRecipes(Set<Recipe> recipes) {
    StringBuilder table = new StringBuilder();
    String format = "| %-20s | %-50s | %-50s | %-30s | %-15s |\n";
    table.append(String.format(format, "Name", "Description", "Instructions",
        "Ingredients", "Servings"));
    table.append("---------------------------------------------------------------------------------"
        + "-------------------------------------------------------------------\n");

    for (Recipe recipe : recipes) {
      table.append(String.format(format, recipe.getRecipeName(),
          recipe.getRecipeDescription(), recipe.getCookingInstructions(),
          recipe.getIngredients(), recipe.getAmountOfServings()));
    }
    return table.toString();
  }

  /**
   * This method will format the <code>getSortedList</code>,
   * according to the <code>formatGroceries</code> template.
   *
   * @param foodStorage The foodStorage to check.
   * @return Will a sorted list, formatted as a table
   * @since 0.0.1
   */
  public static String formatSortedGroceries(FoodStorage foodStorage) {
    return formatGroceries(foodStorage.getSortedList());
  }

  /**
   * This method will format the <code>listOfExpiredGroceries</code>,
   * according to the <code>formatGroceries</code> template.
   *
   * @param foodStorage The foodStorage to check.
   * @param providedExpiryDate Expiry date to sort by.
   * @return Will return list of expired groceries, formatted as table.
   * @since 0.0.1
   */
  public static String formatExpiredGroceries(FoodStorage foodStorage, String providedExpiryDate) {
    return formatGroceries(foodStorage.listOfExpiredGroceries(providedExpiryDate));
  }

  /**
   * This method will format <code>searchGrocery</code>,
   * according to the <code>formatGroceries</code> template.
   *
   * @param foodStorage The foodStorage to check.
   * @param groceryToSearch The grocery to search for.
   * @return Will return the grocery in a formatted table.
   * @since 0.0.1
   */
  public static String formatGrocery(FoodStorage foodStorage, String groceryToSearch) {
    return formatGroceries(foodStorage.searchGrocery(groceryToSearch));
  }

  /**
   * This method will format a recipe according to the <code>formatReicpe</code> template.
   *
   * @param recipe The recipe to format.
   * @return A formatted string representation of the recipe.
   * @since 0.0.1
   */
  public static String formatRecipe(Recipe recipe) {
    return formatRecipes(Set.of(recipe));
  }
}
