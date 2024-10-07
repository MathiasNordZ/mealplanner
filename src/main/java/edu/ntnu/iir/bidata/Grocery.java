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
   * @param quantity This is the quanity of the grocery.
   * @param name This is the name of the grocery
   * @param unitOfMeasurement This is the unit of measurement used.
   * @param year This is the year of expiration for the grocery.
   * @param month This is the month of expiration for the grocery.
   * @param day This is the day of expiration for the grocery.
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
