package edu.ntnu.iir.bidata;

import java.util.Date;

public class UserInterface {

  public UserInterface() {
  }

  public void start() {
    Grocery beef = new Grocery(5, "Beef", "kg", 2024, 10, 7);
    Grocery potato = new Grocery(3, "Potato", "kg", 2024, 10, 7);
    Grocery bernaise = new Grocery(3, "Bernaise","dl", 2024, 10, 7);


    beef.printName();
    beef.printExpirationDate();
    beef.printQuantity();
    beef.printUnitOfMeasurement();
    System.out.println("-------------------------");
    potato.printName();
    potato.printExpirationDate();
    potato.printQuantity();
    potato.printUnitOfMeasurement();
    System.out.println("-------------------------");
    bernaise.printName();
    bernaise.printExpirationDate();
    bernaise.printQuantity();
    bernaise.printUnitOfMeasurement();
  }

  public void init() {
    // Currently empty
  }
}
