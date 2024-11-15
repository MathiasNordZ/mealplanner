package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.entity.Grocery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    Recipe recipe;
    Map<String, Float> ingredients;

    @BeforeEach
    void setUp() {
        LocalDate today = LocalDate.now();
        Grocery tomatoSauce = new Grocery(0.25f, "Tomato Sauce", "liter", 25, today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        Grocery pizzaCrust = new Grocery(0.75f, "Pizza Crust", "kilogram", 75, today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        Grocery topping = new Grocery(0.35f, "Topping", "kilogram", 45, today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        ingredients = new HashMap<>();

        ingredients.put("Tomato Sauce", 0.25f);
        ingredients.put("Pizza Crust", 0.75f);
        ingredients.put("Topping", 0.35f);

        recipe = new Recipe("Pizza", "This is a pizza recipe", "Bake pizza crust, add tomato sauce & topping, bake at 200 deg for 20 min.", ingredients, 2);
    }

    @Test
    void setRecipeNamePositiveTest() {
        recipe.setRecipeName("Homemade pizza with topping");
        assertEquals("Homemade pizza with topping", recipe.getRecipeName());
    }

    @Test
    void setRecipeNameNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> recipe.setRecipeName(""));
    }

    @Test
    void getRecipeDescriptionPositiveTest() {
        assertEquals("This is a pizza recipe", recipe.getRecipeDescription());
    }

    @Test
    void setRecipeDescriptionPositiveTest() {
        recipe.setRecipeDescription("Recipe description");
        assertEquals("Recipe description", recipe.getRecipeDescription());
    }

    @Test
    void setRecipeDescriptionNegativeTest() {
        assertThrows(IllegalArgumentException.class, ()-> recipe.setRecipeDescription(""));
    }

    @Test
    void getCookingInstructionsPositiveTest() {
        assertEquals("Bake pizza crust, add tomato sauce & topping, bake at 200 deg for 20 min.", recipe.getCookingInstructions());
    }

    @Test
    void setCookingInstructionsPositiveTest() {
        recipe.setCookingInstructions("Cooking instruction");
        assertEquals("Cooking instruction", recipe.getCookingInstructions());
    }

    @Test
    void setCookingInstructionsNegativeTest() {
        assertThrows(IllegalArgumentException.class, ()-> recipe.setCookingInstructions(""));
    }

    @Test
    void getIngredientsPositiveTest() {
        assertEquals(ingredients, recipe.getIngredients());
    }

    @Test
    void mapInputValidationPositiveTest() {
        Map<String, Float> validIngredients = new HashMap<>();
        validIngredients.put("Tomato Sauce", 0.25f);

        assertDoesNotThrow(() -> recipe.mapInputValidation(validIngredients));
    }

    @Test
    void mapInputValidationNegativeTest() {
        Map<String, Float> invalidIngredients = new HashMap<>();
        invalidIngredients.put(null, 0.25f);

        assertThrows(IllegalArgumentException.class, () -> recipe.mapInputValidation(invalidIngredients));
    }

    @Test
    void setIngredientsPositiveTest() {
        Map<String, Float> testIngredients = new HashMap<>();
        testIngredients.put("Cheese", 0.25f);
        recipe.setIngredients(testIngredients);

        assertEquals(testIngredients, recipe.getIngredients());
    }

    @Test
    void setIngredientsNegativeTest() {
        Map<String, Float> testIngredients = new HashMap<>();
        testIngredients.put("", 0.25f);

        assertThrows(IllegalArgumentException.class, () -> recipe.setIngredients(testIngredients));
    }

    @Test
    void getAmountOfServingsPositiveTest() {
        recipe.setAmountOfServings(4);

        assertEquals(4, recipe.getAmountOfServings());
    }

    @Test
    void getAmountOfServingsNegativeTest() {
    }

    @Test
    void setAmountOfServingsPositiveTest() {
    }

    @Test
    void setAmountOfServingsNegativeTest() {
    }

    @Test
    void isPossibleToCookPositiveTest() {
    }

    @Test
    void isPossibleToCookNegativeTest() {
    }
}