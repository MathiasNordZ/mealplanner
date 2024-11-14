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
    if (!recipes.contains(recipe)) {
      throw new NoSuchElementException("There is no such element.");
    }
    return recipe;
  }

  public void addRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe to add cannot be null.");
    }
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe to remove cannot be null.");
    }
    recipes.remove(recipe);
  }
}
