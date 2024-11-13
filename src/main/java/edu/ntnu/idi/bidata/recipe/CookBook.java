package edu.ntnu.idi.bidata.recipe;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This is a cookbook class that contains a set of recipes.
 */
public class CookBook {
  private final HashSet<Recipe> recipes = new HashSet<>();

  public CookBook() {
    // Currently empty
  }

  public Set<Recipe> getAllRecipes() {
    return recipes;
  }

  public Recipe getRecipe(Recipe recipe) {
    String errorMessage = "There is no such element.";
    if(!recipes.contains(recipe)) {
      throw new NoSuchElementException(errorMessage);
    }
    return recipe;
  }

  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }
}
