package edu.ntnu.idi.bidata;

public class UserInterface {
  private FoodStorage fridge;
  public void init() {
    Grocery chicken = new Grocery(1.5F, "Chicken Breast", "kilogram", 190, 2024, 11, 20);
    Grocery rice = new Grocery(2F, "Rice", "kilogram", 100, 2026, 11, 20);
    Grocery riceTwo = new Grocery(2F, "Rice", "kilogram", 50, 2025, 11, 20);
    Grocery broccoli = new Grocery(0.5F, "Broccoli", "kilogram", 25, 2024, 11, 30);
    fridge = new FoodStorage();

    fridge.addGrocery(rice);
    fridge.addGrocery(riceTwo);
  }

  public void start() {

    //System.out.println(fridge.searchGrocery("Rice"));
    System.out.println(fridge.valueOfAllGroceries());
  }
}
