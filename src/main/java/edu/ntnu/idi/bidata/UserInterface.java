package edu.ntnu.idi.bidata;

import edu.ntnu.idi.bidata.entity.Grocery;
import edu.ntnu.idi.bidata.register.FoodStorage;

public class UserInterface {
  private FoodStorage fridge;
  public void init() {
    Grocery milk = new Grocery(2F, "Milk", "liter", 15, 2024, 11, 20);
    Grocery milkTwo = new Grocery(1.75F, "Milk", "liter", 25, 2024, 11, 20);
    Grocery milkThree = new Grocery(1F, "Milk", "liter", 25, 2024, 11, 30);
    Grocery milkFour = new Grocery(1.75F, "Milk", "liter", 25, 2024, 11, 30);

    fridge = new FoodStorage();

    fridge.addGrocery(milk);
    fridge.addGrocery(milkTwo);
    fridge.addGrocery(milkThree);
    fridge.addGrocery(milkFour);
  }

  public void start() {

    System.out.println(fridge.searchGrocery("milk"));
  }
}
