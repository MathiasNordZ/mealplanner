package edu.ntnu.idi.bidata.application;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.menu.CookBookMenu;
import edu.ntnu.idi.bidata.menu.GroceryMenu;
import edu.ntnu.idi.bidata.menu.MainMenu;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the interface class, that will take all interaction with the user.
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 */
public class UserInterface {
  private MainMenu mainMenu;

  /**
   * This is the method that initializes the program.
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
   * This is the method that will start the program.
   */
  public void start() {
    mainMenu.mainMenu();
  }

  private void initializeGrocery(FoodStorage foodStorage, float quantity, String name, String unit, int price, String expiryDate) {
    Grocery grocery = new Grocery(quantity, name, unit, price, expiryDate);
    foodStorage.addGrocery(grocery);
  }

  private void addGrocery(FoodStorage foodStorage) {
    initializeGrocery(foodStorage, 1f, "Milk", "liter", 25, "2024-12-31");
    initializeGrocery(foodStorage, 1.25f, "Chicken", "kilogram", 125, "2024-12-10");
    initializeGrocery(foodStorage, 2f, "Rice", "kilogram", 45, "2025-10-30");
    initializeGrocery(foodStorage, 1.5f, "Cola", "liter", 37, "2025-08-20");
  }

  private void initializeRecipe(CookBook cookBook, String recipeName, String recipeDescription, String cookingInstructions, Map<String, Float> ingredients, int amountOfServings) {
    Recipe recipe = new Recipe(recipeName, recipeDescription, cookingInstructions, ingredients, amountOfServings);
    cookBook.addRecipe(recipe);
  }

  private void addRecipe(CookBook cookBook) {
    Map<String, Float> ingredients = new HashMap<>();
    ingredients.put("Chicken", 0.75f);
    ingredients.put("Rice", 0.5f);
    initializeRecipe(cookBook, "Chicken and Rice", "This is a chicken and rice dish",
        "Fry chicken in pan, cook rice, serve.", ingredients, 3);
  }
}
