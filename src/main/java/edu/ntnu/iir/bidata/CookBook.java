package edu.ntnu.iir.bidata;

import java.util.List;

/**
 * This is a cookbook class that contains a set of recipes.
 */
public class CookBook {
  private List<Recipe> recipes;

  public CookBook() {
    // Currently empty
  }

  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public List<Recipe> recipeSuggestion(FoodStorage foodStorage) {
    return recipes;
  }

}
