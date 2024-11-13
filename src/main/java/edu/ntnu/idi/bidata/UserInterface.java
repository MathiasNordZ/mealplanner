package edu.ntnu.idi.bidata;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.*;

/**
 * This is the interface class, that will take all interaction with the user.
 */
public class UserInterface {
  private FoodStorage fridge;
  private CookBook cookBook;

  private List<Grocery> createGroceries() {
    List<Grocery> groceries = new ArrayList<>();

    groceries.add(new Grocery(1F, "Beef", "kilogram", 250F, 2024, 12, 10));
    groceries.add(new Grocery(1.75F, "Potato", "kilogram", 53F, 2024, 12, 31));
    groceries.add(new Grocery(0.2F, "Bernaise", "liter", 65F, 2024, 12, 20));
    groceries.add(new Grocery(0.75F, "Salmon", "kilogram", 179F, 2024, 12, 12));
    groceries.add(new Grocery(0.5F, "Pasta", "kilogram", 58F, 2025, 8, 10));
    groceries.add(new Grocery(0.25F, "Spinach", "kilogram", 25F, 2024, 12, 10));
    groceries.add(new Grocery(1F, "Milk", "liter", 35F, 2024, 12, 10));

    return groceries;
  }

  private List<Recipe> createRecipes() {
    List<Recipe> recipes = new ArrayList<>();

    Map<String, Float> ingredientsOne = new HashMap<>();
    ingredientsOne.put("Beef", 0.75F);
    ingredientsOne.put("Potatoes", 0.5F);
    ingredientsOne.put("Bernaise", 0.20F);
    String cookingInstructions =
              """
              1. Cook beef.
              2. Cook potatoes.
              3. Make bernaise.
              4. Mash potatoes.
              """;
    recipes.add(new Recipe("Beef & Potato with Bernaise", "This is a dinner with grilled beef, mashed potatoes and bernaise sauce.", cookingInstructions, ingredientsOne, 4));

    String cookingInstructionsTwo =
            """
            1. Cook salmon.
            2. Cook pasta.
            3. Wash spinach.
            """;
    Map<String, Float> ingredientsTwo = new HashMap<>();
    ingredientsTwo.put("Salmon", 0.50F);
    ingredientsTwo.put("Pasta", 0.5F);
    ingredientsTwo.put("Spinach", 0.20F);
    recipes.add(new Recipe("Salmon with pasta and spinach", "This is a dinner with salmon, pasta and spinach.", cookingInstructionsTwo, ingredientsTwo, 4));

    return recipes;
  }

  /**
   * This is the method that initializes the program.
   */
  public void init() {
    fridge = new FoodStorage();
    cookBook = new CookBook();

    List<Grocery> groceries = createGroceries();
    for (Grocery grocery : groceries) {
      fridge.addGrocery(grocery);
    }

    List<Recipe> recipes = createRecipes();
    for (Recipe recipe : recipes) {
      cookBook.addRecipe(recipe);
    }
  }

  /**
   * This is the method that will start the program.
   */
  public void start() {
    System.out.println(cookBook.getAllRecipes());
  }
}
