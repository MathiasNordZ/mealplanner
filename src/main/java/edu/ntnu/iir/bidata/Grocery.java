package edu.ntnu.iir.bidata;

import java.time.LocalDate;

/**
 * This class is representing a grocery.
 */
public class Grocery {
  private float quantity;
  private String name;
  private LocalDate expirationDate;
  private String unitOfMeasurement;

  /**
   * This is the constructor for the grocery class.
   *
   * @param quantity This is the quanity of the grocery. It is of the type float,
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
  public Grocery(float quantity, String name, String unitOfMeasurement, int year, int month, int day) {
    this.quantity = quantity;
    this.name = name;
    this.expirationDate = LocalDate.of(year, month, day);
    this.unitOfMeasurement = unitOfMeasurement;
  }


  /**
   * This is a default constructor for grocery class.
   */
  public Grocery() {
    this.quantity = 0;
    this.name = "";
    this.expirationDate = null;
    this.unitOfMeasurement = "";
  }

  public float getQuantity() {
    return quantity;
  }

  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }

  public void printQuantity() {
    System.out.println("The quantity is: " + this.getQuantity());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void printName() {
    System.out.println("Name of the grocery is: " + this.getName());
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public void printExpirationDate() {
    System.out.println("The expiration date is: " + this.getExpirationDate());
  }

  public String getUnitOfMeasurement() {
    return unitOfMeasurement;
  }

  public void setUnitOfMeasurement(String unitOfMeasurement) {
    this.unitOfMeasurement = unitOfMeasurement;
  }

  public void printUnitOfMeasurement() {
    System.out.println("The unit of measurement: " + this.getUnitOfMeasurement());
  }
}
