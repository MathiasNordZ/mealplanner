package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.menu.MainMenu;
import edu.ntnu.idi.bidata.menu.StringMenu;
import edu.ntnu.idi.bidata.menu.cookbook.CookBookMenu;
import edu.ntnu.idi.bidata.menu.cookbook.CookBookMenuMutator;
import edu.ntnu.idi.bidata.menu.cookbook.CookBookMenuPrinter;
import edu.ntnu.idi.bidata.menu.grocery.GroceryMenu;
import edu.ntnu.idi.bidata.menu.grocery.GroceryMenuMutator;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.math.BigDecimal;
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
  private static final String KILOGRAM = "Kilogram";
  private static final String LITER = "Liter";

  private UserInterface() {

  }


  /**
   * This is the method that initializes all the instances required to run the application.
   * It will also fill the cookbook and food storage with some default groceries and recipes.
   *
   * @since 0.0.1
   */
  public void init() {
    StringMenu stringMenu = new StringMenu();
    UserInputHandler uiInputHandler = new UserInputHandler();
    FoodStorage foodStorage = initializeFoodStorage();
    CookBook cookBook = initializeCookBook();

    addRecipe(cookBook);
    addGrocery(foodStorage);

    mainMenu = new MainMenu(uiInputHandler,
        initializeGroceryMenu(uiInputHandler, foodStorage, stringMenu),
        initializeCookBookMenu(uiInputHandler, cookBook, foodStorage, stringMenu));
  }

  private FoodStorage initializeFoodStorage() {
    return new FoodStorage();
  }

  private CookBook initializeCookBook() {
    return new CookBook();
  }

  private GroceryMenu initializeGroceryMenu(UserInputHandler uiInputHandler,
                                            FoodStorage foodStorage, StringMenu stringMenu) {
    GroceryMenuMutator groceryMenuMutator = new GroceryMenuMutator(uiInputHandler);

    return new GroceryMenu(foodStorage, uiInputHandler, stringMenu, groceryMenuMutator);
  }

  private CookBookMenu initializeCookBookMenu(UserInputHandler uiInputHandler, CookBook cookBook,
                                              FoodStorage foodStorage, StringMenu stringMenu) {
    CookBookMenuPrinter cookBookMenuPrinter = new CookBookMenuPrinter();
    CookBookMenuMutator cookBookMenuMutator = new CookBookMenuMutator(uiInputHandler);

    return new CookBookMenu(uiInputHandler, cookBook, foodStorage, stringMenu,
        cookBookMenuMutator, cookBookMenuPrinter);
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
  private void initializeGrocery(FoodStorage foodStorage, BigDecimal quantity,
                                 String name, String unit, BigDecimal price, String expiryDate) {
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
    initializeGrocery(foodStorage, BigDecimal.valueOf(1), "Milk", LITER,
        BigDecimal.valueOf(25), "2024-12-31");
    initializeGrocery(foodStorage, BigDecimal.valueOf(1.25), "Chicken", KILOGRAM,
        BigDecimal.valueOf(125), "2024-12-10");
    initializeGrocery(foodStorage, BigDecimal.valueOf(2), "Rice", KILOGRAM,
        BigDecimal.valueOf(45), "2025-10-30");
    initializeGrocery(foodStorage, BigDecimal.valueOf(1.5), "Cola", LITER,
        BigDecimal.valueOf(37), "2025-08-20");
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
                                Map<String, SimpleEntry<BigDecimal, String>> ingredients,
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
    Map<String, SimpleEntry<BigDecimal, String>> chickenRiceIngredients = new HashMap<>();
    chickenRiceIngredients.put("Chicken", new SimpleEntry<>(BigDecimal.valueOf(0.75), KILOGRAM));
    chickenRiceIngredients.put("Rice", new SimpleEntry<>(BigDecimal.valueOf(0.5), KILOGRAM));
    initializeRecipe(cookBook, "Chicken and rice", "This is a chicken and rice dish",
        "Fry chicken in pan, cook rice, serve.", chickenRiceIngredients, 3);

    Map<String, SimpleEntry<BigDecimal, String>> pastaSalmonIngredients = new HashMap<>();
    pastaSalmonIngredients.put("Pasta", new SimpleEntry<>(BigDecimal.valueOf(0.5), KILOGRAM));
    pastaSalmonIngredients.put("Salmon", new SimpleEntry<>(BigDecimal.valueOf(0.35), KILOGRAM));
    initializeRecipe(cookBook, "Pasta and salmon", "This is a pasta and salmon dish",
        "Boil pasta for 15 minutes, fry salmon in pan.", pastaSalmonIngredients, 2);
  }
}
