package edu.ntnu.idi.bidata;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a cookbook class that contains a set of recipes.
 */
public class CookBook {
  private final HashSet<Recipe> recipes = new HashSet<>();

  public CookBook() {
    // Currently empty
  }

  public Set<Recipe> getRecipes() {
    return recipes;
  }

  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }
}
