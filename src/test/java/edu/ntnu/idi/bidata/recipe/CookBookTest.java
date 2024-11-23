package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.entity.Grocery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;



class CookBookTest {
    private CookBook cookBook;
    private Recipe chickenAndRice;
    private Grocery rice;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        LocalDate today = LocalDate.now();
        String formattedToday = today.format(formatter);
        Grocery chicken = new Grocery(0.25f, "Chicken", "kilogram", 85, formattedToday);
        rice = new Grocery(1f, "Rice", "kilogram", 53, formattedToday);

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
        cookBook.addRecipe(chickenAndRice);
        cookBook.removeRecipe(chickenAndRice);
        assertThrows(NoSuchElementException.class, () -> cookBook.getAllRecipes());
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
}