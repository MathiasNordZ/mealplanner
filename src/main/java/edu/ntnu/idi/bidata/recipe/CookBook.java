package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;

/**
 * This is a cookbook class that contains a set of recipes.
 *
 * @author <b>Mathias Erik Nord</b>
 * @version <b>0.0.1</b>
 * @since <b>13.11.2024</b>
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
    if (recipes.isEmpty()) {
      throw new NoSuchElementException("No recipes found.");
    }
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

  /**
   * Accessor method that will recommend a recipe,
   * based on what is already in the given foodStorage.
   *
   * @param foodStorage storage to check for groceries.
   * @return Will return a recipe, if there are enough groceries.
   */
  public Recipe recipeRecommendation(FoodStorage foodStorage) {
    if (foodStorage == null) {
      throw new IllegalArgumentException("Food storage cannot be null!");
    }
    for (Recipe recipe : recipes) {
      boolean isPossibleToCook = true;
      for (Map.Entry<String, SimpleEntry<Float, String>> ingredient : recipe.getIngredients().entrySet()) {
        String nameOfIngredient = ingredient.getKey();
        float requiredQuantity = ingredient.getValue().getKey();
        String requiredUnit = ingredient.getValue().getValue();
        if (!foodStorage.isGroceryAvailable(nameOfIngredient, requiredQuantity, requiredUnit)) {
          isPossibleToCook = false;
        }
      }
      if (isPossibleToCook) {
        return recipe;
      }
    }
    throw new NoSuchElementException("There is no recipes that can be made with your groceries.");
  }
}
