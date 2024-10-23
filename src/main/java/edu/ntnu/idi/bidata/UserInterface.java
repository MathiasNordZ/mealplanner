package edu.ntnu.idi.bidata;

public class UserInterface {
  public void init() {

  }

  public void start() {
    Grocery chicken = new Grocery(1.5F, "Chicken Breast", "kg", 190, 2024, 10, 31);
    Grocery rice = new Grocery(2F, "Rice", "kg", 100, 2026, 10, 31);
    Grocery broccoli = new Grocery(0.5F, "Broccoli", "kg", 25, 2024, 10, 30);
    Grocery sweetChiliSauce = new Grocery(10, "Sweet Chili Sauce", "dl", 75, 2025, 10, 31);

    chicken.printName();
    chicken.printPrice();
    chicken.printQuantity();
    chicken.printUnitOfMeasurement();
    chicken.printExpirationDate();

    System.out.println("--------------------------");

    rice.printName();
    rice.printPrice();
    rice.printQuantity();
    rice.printUnitOfMeasurement();
    rice.printExpirationDate();

    System.out.println("--------------------------");

    broccoli.printName();
    broccoli.printPrice();
    broccoli.printQuantity();
    broccoli.printUnitOfMeasurement();
    broccoli.printExpirationDate();
  }
}
