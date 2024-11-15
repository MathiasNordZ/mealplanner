package edu.ntnu.idi.bidata.recipe;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This is a cookbook class that contains a set of recipes.
 */
public class CookBook {
  private final HashSet<Recipe> recipes = new HashSet<>();

  /**
   * Constructor for the class <code>CookBook</code>.
   * The constructor is empty, because the idea is to start with an empty book,
   * and add recipes to the book as you go.
   */
  public CookBook() {
    // Currently empty
  }

  /**
   * Accessor method for <code>recipes</code>.
   *
   * @return Will return all recipes.
   */
  public Set<Recipe> getAllRecipes() {
    return recipes;
  }

  /**
   * Accessor method for <code>recipe</code>.
   *
   * @param recipe The recipe to retrieve.
   * @return Will return the given recipe, if it does exist.
   * @throws NoSuchElementException will be thrown if the recipe does not exist.
   */
  public Recipe getRecipe(Recipe recipe) {
    if (!recipes.contains(recipe)) {
      throw new NoSuchElementException("There is no such element.");
    }
    return recipe;
  }

  /**
   * Mutator method for <code>recipe</code>.
   * Will add a recipe to the cookbook.
   *
   * @param recipe recipe to be added.
   */
  public void addRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe to add cannot be null.");
    }
    recipes.add(recipe);
  }

  /**
   * Mutator method for <code>recipe</code>.
   * Will remove the given recipe from the cookbook.
   *
   * @param recipe recipe to be removed.
   */
  public void removeRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe to remove cannot be null.");
    }
    recipes.remove(recipe);
  }
}
