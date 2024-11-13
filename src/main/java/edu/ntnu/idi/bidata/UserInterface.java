package edu.ntnu.idi.bidata;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInterface {
  private FoodStorage fridge;
  private CookBook cookBook;
  private Recipe recipe;
  private Recipe recipeTwo;

  public void init() {
    Grocery chicken = new Grocery(2F, "Chicken", "kilogram", 150, 2024, 11, 20);
    Grocery rice = new Grocery(1F, "Rice", "kilogram", 20, 2026, 11, 20);

    fridge = new FoodStorage();
    cookBook = new CookBook();

    fridge.addGrocery(chicken);
    fridge.addGrocery(rice);

    Map<String, Float> ingredients = new HashMap<>();
    ingredients.put("Chicken", 2.1F);
    ingredients.put("Rice", 1F);

    recipe = new Recipe("Chicken & Rice", "Description", "Instructions", ingredients, 4);
    recipeTwo = new Recipe("Chicken & Rice", "Description", "Instructions", ingredients, 4);

    cookBook.addRecipe(recipe);
    cookBook.addRecipe(recipeTwo);

  }

  public void start() {
    System.out.println(recipe.isPossibleToCook(fridge));
  }
}
