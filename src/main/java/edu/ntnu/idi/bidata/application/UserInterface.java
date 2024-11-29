package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.menu.MainMenu;
import edu.ntnu.idi.bidata.menu.cookbook.CookBookMenu;
import edu.ntnu.idi.bidata.menu.grocery.GroceryMenu;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the <code>UserInterface</code> class.
 *
 * <p>This class will initialize instances required to run the application.</p>
 *
 * <p>It will also initially fill the food storage
 * and cookbook with groceries and recipes by default.</p>
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 */
public class UserInterface {
  private MainMenu mainMenu;
  private static final String KILOGRAM = "kilogram";

  /**
   * This is the method that initializes all the instances required to run the application.
   * It will also fill the cookbook and food storage with some default groceries and recipes.
   *
   * @since 0.0.1
   */
  public void init() {
    UserInputHandler uiInputHandler = new UserInputHandler();
    FoodStorage foodStorage = new FoodStorage();
    GroceryMenu groceryMenu = new GroceryMenu(foodStorage, uiInputHandler);
    CookBook cookBook = new CookBook();
    CookBookMenu cookBookMenu = new CookBookMenu(uiInputHandler, cookBook, foodStorage);

    addRecipe(cookBook);
    addGrocery(foodStorage);

    mainMenu = new MainMenu(uiInputHandler, groceryMenu, cookBookMenu);
  }

  /**
   * This is the method that will start the whole application.
   *
   * @since 0.0.1
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
   * @since 0.0.1
   */
  private void initializeGrocery(FoodStorage foodStorage, float quantity,
                                 String name, String unit, int price, String expiryDate) {
    Grocery grocery = new Grocery(quantity, name, unit, price, expiryDate);
    foodStorage.addGrocery(grocery);
  }

  /**
   * Method that will call <code>initializeGrocery</code>.
   * This is to initialize a given set of groceries to start the application with.
   *
   * @param foodStorage Food storage that grocery should be added to.
   * @since 0.0.1
   */
  private void addGrocery(FoodStorage foodStorage) {
    initializeGrocery(foodStorage, 1f, "Milk", "liter", 25, "2024-12-31");
    initializeGrocery(foodStorage, 1.25f, "Chicken", KILOGRAM, 125, "2024-12-10");
    initializeGrocery(foodStorage, 2f, "Rice", KILOGRAM, 45, "2025-10-30");
    initializeGrocery(foodStorage, 1.5f, "Cola", "liter", 37, "2025-08-20");
  }

  /**
   * Method that will initialize an instance of <code>Recipe</code>.
   *
   * @param cookBook Cookbook to add the recipe to.
   * @param recipeName Name of the recipe.
   * @param recipeDescription Description of the recipe.
   * @param cookingInstructions Instructions for the recipe.
   * @param ingredients Ingredients of the recipe.
   * @param amountOfServings Amount of servings.
   * @since 0.0.1
   */
  private void initializeRecipe(CookBook cookBook, String recipeName,
                                String recipeDescription, String cookingInstructions,
                                Map<String, SimpleEntry<Float, String>> ingredients,
                                int amountOfServings) {
    Recipe recipe = new Recipe(recipeName, recipeDescription,
        cookingInstructions, ingredients, amountOfServings);
    cookBook.addRecipe(recipe);
  }

  /**
   * Method will call <code>initializeRecipe</code>.
   * This is to initialize given recipes by default when starting the program.
   *
   * @param cookBook Cookbook to add recipe to.
   * @since 0.0.1
   */
  private void addRecipe(CookBook cookBook) {
    Map<String, SimpleEntry<Float, String>> chickenRiceIngredients = new HashMap<>();
    chickenRiceIngredients.put("Chicken", new SimpleEntry<>(0.75f, KILOGRAM));
    chickenRiceIngredients.put("Rice", new SimpleEntry<>(0.5f, KILOGRAM));
    initializeRecipe(cookBook, "Chicken and Rice", "This is a chicken and rice dish",
        "Fry chicken in pan, cook rice, serve.", chickenRiceIngredients, 3);

    Map<String, SimpleEntry<Float, String>> pastaSalmonIngredients = new HashMap<>();
    chickenRiceIngredients.put("Pasta", new SimpleEntry<>(0.5f, KILOGRAM));
    chickenRiceIngredients.put("Salmon", new SimpleEntry<>(0.35f, KILOGRAM));
    initializeRecipe(cookBook, "Pasta and Salmon", "This is a pasta and salmon dish",
        "Boil pasta for 15 minutes, fry salmon in pan.", pastaSalmonIngredients, 2);
  }
}
