package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.register.FoodStorage;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * The <code>CookBook</code> class represents a collection of recipes.
 * It provides methods to add, remove and retrieve recipes.
 *
 * @author Mathias Erik Nord
 * @version 0.0.1
 * @since 13.11.2024
 */
public class CookBook {
  private final Set<Recipe> recipes;

  /**
   * Constructor for the class <code>CookBook</code>.
   * It will initialize and empty set of recipes.
   */
  public CookBook() {
    this.recipes = new HashSet<>();
  }

  /**
   * Accessor method for <code>recipes</code>.
   *
   * @return A set of all recipes.
   * @throws NoSuchElementException if no recipes are found.
   */
  public Set<Recipe> getAllRecipes() {
    if (recipes.isEmpty()) {
      throw new NoSuchElementException("No recipes found.");
    }
    return Set.copyOf(recipes); // Use of copyOf inspired by CoPilot.
  }

  /**
   * Accessor method for <code>recipe</code>.
   *
   * @param recipeName The name of the recipe to retrieve.
   * @return The given recipe, if it does exist.
   * @throws NoSuchElementException will be thrown if the recipe does not exist.
   */
  public Recipe getRecipe(String recipeName) {
    for (Recipe recipe : recipes) {
      if (recipe.getRecipeName().equalsIgnoreCase(recipeName)) {
        return recipe;
      }
    }
    throw new NoSuchElementException("The recipe does not exist!");
  }

  /**
   * Mutator method for <code>recipe</code>.
   * Will add a recipe to the cookbook.
   *
   * @param recipe recipe to be added.
   * @throws IllegalArgumentException if the recipe provided is null.
   */
  public void addRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe to add cannot be null.");
    }
    for (Recipe existingRecipe : recipes) {
      if (existingRecipe.getRecipeName().equalsIgnoreCase(recipe.getRecipeName())) {
        throw new IllegalArgumentException("There is already a recipe with the same name!");
      }
    }
    recipes.add(recipe);
  }

  /**
   * Mutator method for <code>recipe</code>.
   * Will remove the given recipe from the cookbook.
   *
   * @param recipeName Name of recipe to be removed.
   * @throws IllegalArgumentException if the provided recipe name is null or empty.
   * @throws NoSuchElementException if the recipe to remove does not exist.
   */
  public void removeRecipe(String recipeName) {
    if (recipeName == null || recipeName.trim().isEmpty()) {
      throw new IllegalArgumentException("Recipe name cannot be null or empty.");
    }
    Recipe recipeToRemove = getRecipe(recipeName);
    recipes.remove(recipeToRemove);
  }

  /**
   * Accessor method that will recommend a recipe,
   * based on what is already in the given foodStorage.
   * Improvement inspired by CoPilot.
   *
   * @param foodStorage storage to check for groceries.
   * @return Will return a recipe, if there are enough groceries.
   * @throws IllegalArgumentException if the provided food storage is null.
   * @throws NoSuchElementException if there is no recipe to make from the groceries.
   */
  public Recipe recipeRecommendation(FoodStorage foodStorage) {
    if (foodStorage == null) {
      throw new IllegalArgumentException("Food storage cannot be null!");
    }
    return recipes.stream()
        .filter(recipe -> matchRecipeToGrocery(foodStorage, recipe))
        .findFirst()
        .orElseThrow(() ->
            new NoSuchElementException("There is no recipes that"
                + " can be made with your groceries."));
  }

  /**
   * Method checks if a given recipe can be made with the available groceries in the food storage.
   *
   * @param foodStorage The food storage to check for available groceries.
   * @param recipe The recipe to match with the groceries.
   * @return <code>true</code> if possible to make, and <code>false</code> if not possible.
   */
  public boolean matchRecipeToGrocery(FoodStorage foodStorage, Recipe recipe) {
    for (Map.Entry<String, SimpleEntry<Float, String>> ingredient :
        recipe.getIngredients().entrySet()) {
      String nameOfIngredient = ingredient.getKey();
      float requiredQuantity = ingredient.getValue().getKey();
      String requiredUnit = ingredient.getValue().getValue();
      if (!foodStorage.isGroceryAvailable(nameOfIngredient, requiredQuantity, requiredUnit)) {
        return false;
      }
    }
    return true;
  }
}
