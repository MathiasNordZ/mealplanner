package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;
import org.graalvm.collections.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * This is the test class for the class Recipe. The test class is supposed to test certain methods of the Recipe class, both with positive and negative values.
 * The test class does follow the Arrange, Act and Assert philosophy, where an object of the class Grocery is created. It is acted upon, and then asserted.
 * </p>
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>15.11.2024</b>
 * @version <b>0.0.1</b>
 */
class RecipeTest {
    private Recipe recipe;
    private Map<String, Pair<Float, String>> ingredients;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    FoodStorage foodStorage;

    /**
     * The method <code>setUp</code>, does create instances of <code>Grocery</code>, adds them to an ingredient map,
     * and creates a recipe based on the groceries.
     * This is done to avoid having to set up instances in every test.
     */
    @BeforeEach
    void setUp() {
        LocalDate today = LocalDate.now();
        String formattedToday = today.format(formatter);
        foodStorage = new FoodStorage();

        Grocery tomatoSauce = new Grocery(0.25f, "Tomato Sauce", "liter", 25, formattedToday);
        Grocery pizzaCrust = new Grocery(0.75f, "Pizza Crust", "kilogram", 75, formattedToday);
        Grocery topping = new Grocery(0.35f, "Topping", "kilogram", 45, formattedToday);

        foodStorage.addGrocery(tomatoSauce);
        foodStorage.addGrocery(pizzaCrust);
        foodStorage.addGrocery(topping);

        ingredients = new HashMap<>();

        ingredients.put("Tomato Sauce", Pair.create(0.25f, "liter"));
        ingredients.put("Pizza Crust", Pair.create(0.75f, "kilogram"));
        ingredients.put("Topping", Pair.create(0.35f, "kilogram"));

        recipe = new Recipe("Pizza", "This is a pizza recipe", "Bake pizza crust, add tomato sauce & topping, bake at 200 deg for 20 min.", ingredients, 2);
    }

    /**
     * Positive test method for <code>setRecipeName</code>.
     * Will set a valid recipe name, and check the correct value actually is being set.
     */
    @Test
    void setRecipeNamePositiveTest() {
        recipe.setRecipeName("Homemade pizza with topping");
        assertEquals("Homemade pizza with topping", recipe.getRecipeName());
    }

    /**
     * Negative test method for <code>setRecipeName</code>.
     * Will set an invalid recipe name, and check that it does throw an exception.
     */
    @Test
    void setRecipeNameNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> recipe.setRecipeName(""));
    }

    /**
     * Positive test method for <code>getRecipeDescription</code>.
     * Will check that the returned value is correct.
     */
    @Test
    void getRecipeDescriptionPositiveTest() {
        assertEquals("This is a pizza recipe", recipe.getRecipeDescription());
    }

    /**
     * Positive test method for <code>setRecipeDescription</code>.
     * Will set a valid recipe, and check that the correct value is being set.
     */
    @Test
    void setRecipeDescriptionPositiveTest() {
        recipe.setRecipeDescription("Recipe description");
        assertEquals("Recipe description", recipe.getRecipeDescription());
    }

    /**
     * Negative test method for <code>setRecipeDescription</code>.
     * Will set an invalid recipe, and check that an exception is thrown.
     */
    @Test
    void setRecipeDescriptionNegativeTest() {
        assertThrows(IllegalArgumentException.class, ()-> recipe.setRecipeDescription(""));
    }

    /**
     * Positive test for <code>getCookingInstructions</code>.
     * Will check that the correct cooking instructions is returned.
     */
    @Test
    void getCookingInstructionsPositiveTest() {
        assertEquals("Bake pizza crust, add tomato sauce & topping, bake at 200 deg for 20 min.", recipe.getCookingInstructions());
    }

    /**
     * Positive test for <code>setCookingInstructions</code>.
     * Will set a valid cooking instruction, and check that the correct value is set.
     */
    @Test
    void setCookingInstructionsPositiveTest() {
        recipe.setCookingInstructions("Cooking instruction");
        assertEquals("Cooking instruction", recipe.getCookingInstructions());
    }

    /**
     * Negative test method for <code>setCookingInstructions</code>.
     * Will set an invalid cooking instruction, and check that an exception is thrown.
     */
    @Test
    void setCookingInstructionsNegativeTest() {
        assertThrows(IllegalArgumentException.class, ()-> recipe.setCookingInstructions(""));
    }

    /**
     * Positive test method for <code>getIngredients</code>.
     * Will check that the method actually returns the correct ingredients.
     */
    @Test
    void getIngredientsPositiveTest() {
        assertEquals(ingredients, recipe.getIngredients());
    }

    /**
     * Positive test method for <code>mapInputValidation</code>.
     * Will check that an exception is not thrown, when the provided parameter is valid.
     */
    @Test
    void mapInputValidationPositiveTest() {
        Map<String, Pair<Float, String>> validIngredients = new HashMap<>();
        validIngredients.put("Tomato Sauce", Pair.create(0.25f, "kilogram"));

        assertDoesNotThrow(() -> recipe.mapInputValidation(validIngredients));
    }

    /**
     * Negative test method for <code>mapInputValidation</code>.
     * Will check that an exception is thrown when the provided parameter is invalid.
     */
    @Test
    void mapInputValidationNegativeTest() {
        Map<String, Pair<Float, String>> invalidIngredients = new HashMap<>();
        invalidIngredients.put(null, Pair.create(0.25f, "kilogram"));

        assertThrows(IllegalArgumentException.class, () -> recipe.mapInputValidation(invalidIngredients));
    }

    /**
     * Positive test method for <code>setIngredients</code>.
     * Will check the ingredients are set correctly.
     */
    @Test
    void setIngredientsPositiveTest() {
        Map<String, Pair<Float, String>> testIngredients = new HashMap<>();
        testIngredients.put("Cheese", Pair.create(0.25f, "kilogram"));
        recipe.setIngredients(testIngredients);

        assertEquals(testIngredients, recipe.getIngredients());
    }

    /**
     * Negative test method for <code>setIngredients</code>.
     * Will check that an exception is thrown when the ingredient to set is invalid.
     */
    @Test
    void setIngredientsNegativeTest() {
        Map<String, Pair<Float, String>> testIngredients = new HashMap<>();
        testIngredients.put("", Pair.create(0.25f, "kilogram"));

        assertThrows(IllegalArgumentException.class, () -> recipe.setIngredients(testIngredients));
    }

    /**
     * Positive test method for <code>getAmountOfServings</code>.
     * Will set the amount, and check that the returned amount is correct.
     */
    @Test
    void getAmountOfServingsPositiveTest() {
        recipe.setAmountOfServings(4);

        assertEquals(4, recipe.getAmountOfServings());
    }

    /**
     * Positive test method for <code>setAmountOfServings</code>.
     * Will set a valid parameter, and check that the correct value is set.
     */
    @Test
    void setAmountOfServingsPositiveTest() {
        recipe.setAmountOfServings(1);

        assertEquals(1, recipe.getAmountOfServings());
    }

    /**
     * Negative test method for <code>setAmountOfServings</code>.
     * Will set an invalid amount of servings, and check that an exception is thrown.
     */
    @Test
    void setAmountOfServingsNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> recipe.setAmountOfServings(-1));
    }

    /**
     * Positive test method for <code>isPossibleToCook</code>.
     * Will check that the method does return correct boolean value.
     */
    @Test
    void isPossibleToCookPositiveTest() {
        assertTrue(recipe.isPossibleToCook(foodStorage));
    }

    /**
     * Negative test method for <code>isPossibleToCook</code>.
     * Will check that an exception is thrown when an illegal parameter is passed.
     */
    @Test
    void isPossibleToCookNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> recipe.isPossibleToCook(null));
    }
}