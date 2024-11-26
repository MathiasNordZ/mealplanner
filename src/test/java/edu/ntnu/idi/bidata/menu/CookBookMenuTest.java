package edu.ntnu.idi.bidata.menu;

import edu.ntnu.idi.bidata.application.UserInputHandler;
import edu.ntnu.idi.bidata.recipe.CookBook;
import edu.ntnu.idi.bidata.register.FoodStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for <code>CookBookMenu</code>.
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>26.11.2024</b>
 * @version <b>0.0.1</b>
 */
class CookBookMenuTest {
  private UserInputHandler uiInputHandler;
  private CookBookMenu cookBookMenu;
  private CookBook cookBook;
  private FoodStorage foodStorage;

  @BeforeEach
  void setUp() {
    uiInputHandler = new UserInputHandler();
    cookBook = new CookBook();
    foodStorage = new FoodStorage();
    cookBookMenu = new CookBookMenu(uiInputHandler, cookBook, foodStorage);
  }

  @Test
  void cookBookMenuPositiveTest() {
  }

  @Test
  void cookBookMenuNegativeTest() {
  }
}