package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for <code>CookBook</code>.
 *
 * @author Mathias Erik Nord
 * @since 24.11.2024
 * @version 0.0.1
 */
class CookBookTest {
    private CookBook cookBook;
    private Recipe chickenAndRice;
    private Grocery rice;
    private Grocery chicken;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        LocalDate today = LocalDate.now();
        String formattedToday = today.format(formatter);
        chicken = new Grocery(BigDecimal.valueOf(0.25), "Chicken", "kilogram", BigDecimal.valueOf(85), formattedToday);
        rice = new Grocery(BigDecimal.valueOf(1), "Rice", "kilogram", BigDecimal.valueOf(53), formattedToday);

        Map<String, SimpleEntry<BigDecimal, String>> ingredients = new HashMap<>();
        ingredients.put("Chicken", new SimpleEntry<>(BigDecimal.valueOf(0.25), "kilogram"));
        ingredients.put("Rice", new SimpleEntry<>(BigDecimal.valueOf(0.25), "kilogram"));

        String cookingInstructions =
                """
                1. Cut and season chicken.
                2. Boil rice for 15 min.
                3. Cook chicken.
                """;

        chickenAndRice = new Recipe("Chicken and Rice",
                "This is a plain chicken & recipe", cookingInstructions,
                ingredients, 2);

        cookBook = new CookBook();
        cookBook.addRecipe(chickenAndRice);
    }

    /**
     * Positive test for <code>getAllRecipes</code>.
     * Will check that it does return the correct amount of recipes.
     */
    @Test
    void getAllRecipesPositiveTest() {
        assertEquals(1, cookBook.getAllRecipes().size());
    }

    /**
     * Positive test for <code>getRecipe</code>
     * Will check that the correct recipe is returned.
     */
    @Test
    void getRecipePositiveTest() {
        assertEquals(chickenAndRice, cookBook.getRecipe(chickenAndRice.getRecipeName()));
    }

    /**
     * Negative test for <code>getRecipe</code>.
     * Will check that an exception is thrown, when searching for an invalid recipe.
     */
    @Test
    void getRecipeNegativeTest() {
        assertThrows(NoSuchElementException.class, () -> cookBook.getRecipe(null));
    }

    /**
     * Positive test for <code>addRecipe</code>.
     * Will check that the addRecipe method, actually adds a new recipe.
     */
    @Test
    void addRecipePositiveTest() {
        Map<String, SimpleEntry<BigDecimal, String>> ingredients = new HashMap<>();
        ingredients.put("rice", new SimpleEntry<>(BigDecimal.valueOf(0.2), "kilogram"));
        Recipe friedRice = new Recipe("Fried rice", "This is a fried rice recipe", "1. Fry rice", ingredients, 1);

        cookBook.addRecipe(friedRice);

        assertEquals(2, cookBook.getAllRecipes().size());
    }

    /**
     * Negative test for <code>addRecipe</code>.
     * Will check that the <code>addRecipe</code> method throws an exception,
     * when the recipe to add is null.
     */
    @Test
    void addRecipeNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> cookBook.addRecipe(null));
    }

    /**
     * Positive test for <code>removeRecipe</code>.
     * Will check that <code>removeRecipe</code> does remove the given grocery
     */
    @Test
    void removeRecipePositiveTest() {
        cookBook.removeRecipe(chickenAndRice.getRecipeName());
        String groceryName = chickenAndRice.getRecipeName();
        assertThrows(NoSuchElementException.class, ()-> cookBook.getRecipe(groceryName));
    }

    /**
     * Negative test for <code>removeGrocery</code>.
     * Will check that the <code>removeRecipe</code> throws an exception,
     * when the recipe to remove is null.
     */
    @Test
    void removeRecipeNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> cookBook.removeRecipe(null));
    }

    /**
     * Positive test for <code>recipeRecommendation</code>.
     * Will assert that the method will recommend a recipe with available groceries.
     */
    @Test
    void recipeRecommendationPositiveTest() {
        FoodStorage foodStorage = new FoodStorage();
        foodStorage.addGrocery(rice);
        foodStorage.addGrocery(chicken);

        Recipe recommendedRecipe = cookBook.recipeRecommendation(foodStorage);

        assertEquals(chickenAndRice, recommendedRecipe);
    }

    /**
     * Negative test for <code>recipeRecommendation</code>.
     * Will assert that <code>IllegalArgumentException</code> is thrown if <code>foodStorage</code> is <coded>null</coded>.
     * Will assert that <code>NoSuchElementException</code> is thrown if <code>foodStorage</code> is empty.
     */
    @Test
    void recipeRecommendationNegativeTest() {
        FoodStorage foodStorage = new FoodStorage();

        assertThrows(IllegalArgumentException.class, () -> cookBook.recipeRecommendation(null));
        assertThrows(NoSuchElementException.class, () -> cookBook.recipeRecommendation(foodStorage));
    }
}