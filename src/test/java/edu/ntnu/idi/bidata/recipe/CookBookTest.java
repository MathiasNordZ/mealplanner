package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.entity.Grocery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CookBookTest {
    CookBook cookBook;
    Recipe chickenAndRice;
    Grocery rice;

    @BeforeEach
    void setUp() {
        LocalDate today = LocalDate.now();
        Grocery chicken = new Grocery(0.25f, "Chicken", "kilogram", 85, today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        rice = new Grocery(1f, "Rice", "kilogram", 53, today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        Map<String, Float> ingredients = new HashMap<>();
        ingredients.put("Chicken", 0.25f);
        ingredients.put("Rice", 0.25f);

        String cookingInstructions =
                """
                1. Cut and season chicken.
                2. Boil rice for 15 min.
                3. Cook chicken.
                """;

        chickenAndRice = new Recipe("Chicken & Rice",
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
        assertEquals(chickenAndRice, cookBook.getRecipe(chickenAndRice));
    }

    /**
     * Negative test for <code>getRecipe</code>.
     * Will check that an exception is thrown, when searching for an invalid recipe.
     */
    @Test
    void getRecipeNegativeTest() {
        Recipe invalidRecipe = null;
        assertThrows(NoSuchElementException.class, () -> cookBook.getRecipe(invalidRecipe));
    }

    /**
     * Positive test for <code>addRecipe</code>.
     * Will check that the addRecipe method, actually adds a new recipe.
     */
    @Test
    void addRecipePositiveTest() {
        Map<String, Float> ingredients = new HashMap<>();
        ingredients.put("rice", 0.2f);

        Recipe friedRice = new Recipe("Fried rice", "This is a fried rice recipe", "1. Fry rice", ingredients, 1);
        CookBook riceRecipeBook = new CookBook();

        cookBook.addRecipe(friedRice);

        assertEquals(1, riceRecipeBook.getAllRecipes().size());
    }

    @Test
    void removeRecipe() {
    }
}