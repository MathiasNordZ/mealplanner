package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.menu.cookbook.CookBookMenu;
import edu.ntnu.idi.bidata.menu.grocery.GroceryMenu;
import edu.ntnu.idi.bidata.menu.MainMenu;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.register.GroceryManager;

import java.util.AbstractMap.SimpleEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>This is the interface class. This class will initialize instances required to run the application.
 * It will also initially fill the food storage and cookbook with groceries and recipes by default.</p>
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 */
public class UserInterface {
  private MainMenu mainMenu;

  /**
   * <p>This is the method that initializes instances required to run the application.
   * It will also fill the cookbook and food storage with some default groceries and recipes.</p>
   */
  public void init() {
    UserInputHandler uiInputHandler = new UserInputHandler();
    GroceryManager groceryManager = new GroceryManager(new FoodStorage());
    GroceryMenu groceryMenu = new GroceryMenu(groceryManager, uiInputHandler);
    CookBook cookBook = new CookBook();
    CookBookMenu cookBookMenu = new CookBookMenu(uiInputHandler, cookBook, groceryManager);

    addRecipe(cookBook);
    addGrocery(groceryManager);

    mainMenu = new MainMenu(uiInputHandler, groceryMenu, cookBookMenu);
  }

  /**
   * This is the method that will start the program.
   */
  public void start() {
    mainMenu.mainMenu();
  }

  /**
   * Private method that will initialize an instance of <code>Grocery</code>.
   *
   * @param foodStorage Food storage that grocery will be added to
   * @param quantity Quantity of grocery to add.
   * @param name Name of grocery to add.
   * @param unit Unit of measurement of grocery.
   * @param price Price of grocery.
   * @param expiryDate Expiry date of grocery.
   */
  private void initializeGrocery(GroceryManager groceryManager, float quantity, String name, String unit, int price, String expiryDate) {
    Grocery grocery = new Grocery(quantity, name, unit, price, expiryDate);
    groceryManager.addGrocery(grocery);
  }

  /**
   * Method will call <code>initializeGrocery</code>.
   * @param foodStorage Food storage that grocery should be added to.
   */
  private void addGrocery(GroceryManager groceryManager) {
    initializeGrocery(groceryManager, 1f, "Milk", "liter", 25, "2024-12-31");
    initializeGrocery(groceryManager, 1.25f, "Chicken", "kilogram", 125, "2024-12-10");
    initializeGrocery(groceryManager, 2f, "Rice", "kilogram", 45, "2025-10-30");
    initializeGrocery(groceryManager, 1.5f, "Cola", "liter", 37, "2025-08-20");
  }

  /**
   * Private method that will initialize an instance of <code>Recipe</code>>.
   *
   * @param cookBook Cookbook to add the recipe to.
   * @param recipeName Name of the recipe.
   * @param recipeDescription Description of the recipe.
   * @param cookingInstructions Instructions for the recipe.
   * @param ingredients Ingredients of the recipe.
   * @param amountOfServings Amount of servings.
   */
  private void initializeRecipe(CookBook cookBook, String recipeName, String recipeDescription, String cookingInstructions, Map<String, SimpleEntry<Float, String>> ingredients, int amountOfServings) {
    Recipe recipe = new Recipe(recipeName, recipeDescription, cookingInstructions, ingredients, amountOfServings);
    cookBook.addRecipe(recipe);
  }

  /**
   * Method will call <code>initializeRecipe</code>.
   *
   * @param cookBook Cookbook to add recipe to.
   */
  private void addRecipe(CookBook cookBook) {
    Map<String, SimpleEntry<Float, String>> ingredients = new HashMap<>();
    ingredients.put("Chicken", new SimpleEntry<>(0.75f, "kilogram"));
    ingredients.put("Rice", new SimpleEntry<>(0.5f, "kilogram"));
    initializeRecipe(cookBook, "Chicken and Rice", "This is a chicken and rice dish",
        "Fry chicken in pan, cook rice, serve.", ingredients, 3);
  }
}
