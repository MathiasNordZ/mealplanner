package edu.ntnu.idi.bidata;

import java.time.LocalDate;

/**
 * This class is representing a grocery.
 */
public class Grocery {
  private float quantity;
  private float price;
  private String name;
  private LocalDate expirationDate;
  private String unitOfMeasurement;

  /**
   * This is the constructor for the grocery class.
   *
   * @param quantity This is the quantity of the grocery. It is of the type float,
   *                 because quantity can be a float number and use of the type double,
   *                 is not necessary when it comes to food measurement.
   * @param name This is the name of the grocery. It is of the type String, because a name usually consists of multiple characters.
   * @param unitOfMeasurement This is the unit of measurement used. It is of the type String, because it fits the use of "kg, g, mg, dl, liter" etc.
   * @param year This is the year of expiration for the grocery. It is of the type int, because a year is a whole number.
   * @param month This is the month of expiration for the grocery. It is of the type int, because month is a whole number.
   * @param day This is the day of expiration for the grocery. It is of the type int, because day is a whole number. I have purposely chosen int,
   *            because it is easier to work with than a String, when it comes to calculating dates.
   *
   */
  public Grocery(float quantity, String name, String unitOfMeasurement, float price, int year, int month, int day) {
    setQuantity(quantity);
    setName(name);
    setExpirationDate(LocalDate.of(year, month, day));
    setUnitOfMeasurement(unitOfMeasurement);
    setPrice(price);
  }

  /**
   * Get method for quantity.
   *
   * @return Returns the value of the field, quantity.
   */
  public float getQuantity() {
    return quantity;
  }

  /**
   * Set method to set quantity
   *
   * @param quantity Takes in a parameter float quantity, and passes it to the field, quantity.
   */
  public void setQuantity(float quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity can not have a negative value.");
    }
    this.quantity = quantity;
  }

  /**
   * Print method for quantity, will print the quantity of a Grocery.
   */
  public void printQuantity() {
    System.out.println("The quantity is: " + this.getQuantity());
  }

  /**
   * Get method for name of a Grocery.
   *
   * @return Returns the value of field, name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set method to set name of a Grocery.
   *
   * @param name Takes in parameter String name, and passes it to the field, name.
   */
  public void setName(String name) {
    if (name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("Name can not be blank or empty.");
    }
    this.name = name;
  }

  /**
   * Print method for name, will print the name of a Grocery.
   */
  public void printName() {
    System.out.println("Name of the grocery is: " + this.getName());
  }

  /**
   * Get method for expiration date of a Grocery.
   *
   * @return Returns the value of field, expirationDate.
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Set method for expiration date of a Grocery.
   *
   * @param expirationDate Takes in parameter LocalDate expirationDate, and passes it to the field, expirationDate.
   */
  public void setExpirationDate(LocalDate expirationDate) {
    if (expirationDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("You can not set an expiration date of the past.");
    }
    this.expirationDate = expirationDate;
  }

  /**
   * Print method for expirationDate, will print the expiration date of a Grocery.
   */
  public void printExpirationDate() {
    System.out.println("The expiration date is: " + this.getExpirationDate());
  }

  /**
   * Get method for unit of measurement of a Grocery.
   *
   * @return Returns the field unitOfMeasurement.
   */
  public String getUnitOfMeasurement() {
    return unitOfMeasurement;
  }


  /**
   * Set method for unit of measurement of a Grocery.
   *
   * @param unitOfMeasurement Takes in parameter String unitOfMeasurement and passes it to the field, unitOfMeasurement.
   */
  public void setUnitOfMeasurement(String unitOfMeasurement) {
    if (unitOfMeasurement.isBlank() || unitOfMeasurement.isEmpty()) {
      throw new IllegalArgumentException("UnitOfMeasurement can not be blank or empty.");
    }
    this.unitOfMeasurement = unitOfMeasurement;
  }

  /**
   * Print method for unit of measurement, will print the unit of measurement of a Grocery.
   */
  public void printUnitOfMeasurement() {
    System.out.println("The unit of measurement: " + this.getUnitOfMeasurement());
  }

  /**
   * Get method for price of a Grocery.
   *
   * @return Returns the field price.
   */
  public float getPrice() {
    return price;
  }

  /**
   * Set method for price of a Grocery.
   *
   * @param price Takes in parameter float price and passes it to the field, price.
   */
  public void setPrice(float price) {
    if (price < 0) {
      throw new IllegalArgumentException("The price can not be less than zero.");
    }
    this.price = price;
  }

  /**
   * Print method for price, will print the price of a Grocery.
   */
  public void printPrice() {
    System.out.println("Price of grocery: " + this.getPrice());
  }
}
