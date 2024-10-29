package edu.ntnu.idi.bidata;

public class UserInterface {
  public void init() {

  }

  public void start() {
    Grocery chicken = new Grocery(1.5F, "Chicken Breast", "kg", 190, 2024, 10, 31);
    Grocery rice = new Grocery(2F, "Rice", "kg", 100, 2026, 10, 31);
    Grocery broccoli = new Grocery(0.5F, "Broccoli", "kg", 25, 2024, 10, 30);

    System.out.println(chicken.getName());
  }
}
