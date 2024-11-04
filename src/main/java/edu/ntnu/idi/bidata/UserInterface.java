package edu.ntnu.idi.bidata;

public class UserInterface {
  private FoodStorage fridge;
  public void init() {
    Grocery rice = new Grocery(2F, "Rice", "kilogram", 100, 2026, 11, 20);
    Grocery riceTwo = new Grocery(5F, "Rice", "kilogram", 300, 2026, 11, 20);
    Grocery apple = new Grocery(1F, "Apple", "kilogram", 20, 2024, 11, 20);
    Grocery carrot = new Grocery(1F, "Carrot", "kilogram", 20, 2024, 11, 20);
    fridge = new FoodStorage();

    fridge.addGrocery(rice);
    fridge.addGrocery(riceTwo);
    fridge.addGrocery(apple);
    fridge.addGrocery(carrot);
  }

  public void start() {

    System.out.println(fridge.getSortedList());
  }
}
