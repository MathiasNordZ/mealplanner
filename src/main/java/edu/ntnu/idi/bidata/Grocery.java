package edu.ntnu.idi.bidata;

import java.time.LocalDate;

/**
 * This class represents a grocery with the attributes quantity, price, name,
 *    expiration date and unit of measurement.
 *
 * @author Mathias Erik Nord
 * @since 16.10.2024
 */
public class Grocery {
  private float quantity;
  private float price;
  private String name;
  private LocalDate expirationDate;
  private String unitOfMeasurement; // kg, l

  /**
   * Constructor for the Grocery class. Initializes the quantity, name, unitOfMeasurement,
   * and expiry date.
   * The constructor uses setters and getters for error handling.
   *
   * @param quantity This is the quantity of the grocery.
   * @param name This is the name of the grocery.
   * @param unitOfMeasurement This is the unit of measurement that the grocery is measured in.
   * @param year This is the year of expiration for the grocery.
   * @param month This is the month of expiration for the grocery.
   * @param day This is the day of expiration for the grocery.
   *
   */
  public Grocery(float quantity, String name, String unitOfMeasurement,
                 float price, int year, int month, int day) {
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
   * Set method to set quantity of a Grocery.
   * If the provided parameter is less than zero,
   * an IllegalArgumentException will be thrown.
   * This is to prevent the field quantity to be negative,
   * because a negative quantity should not be possible.
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
   * Get method for name of a Grocery.
   *
   * @return Returns the value of field, name.
   */
  public String getName() {
    return name;
  }

  /**
   * Mutates <code>name</code>
   *
   * @param name Represents the name of a Grocery.
   * @throws IllegalArgumentException if name is blank or empty.
   */
  public void setName(String name) {
    if (name == null || name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("Name can not be blank or empty.");
    }
    this.name = name;
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
   * If the provided parameter contains a date that is in the past, an IllegalArgumentException.
   * This is because you should not be able to add expired products to your storage.
   *
   * @param expirationDate Takes in parameter LocalDate expirationDate,
   *                       and passes it to the field, expirationDate.
   */
  public void setExpirationDate(LocalDate expirationDate) {
    if (expirationDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("You can not set an expiration date of the past.");
    }
    this.expirationDate = expirationDate;
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
   * If the provided parameter is blank or empty, and IllegalArgumentException will be thrown.
   * This is to prevent the field unitOfMeasurement to be invalid or empty.
   *
   * @param unitOfMeasurement Takes in parameter String unitOfMeasurement and
   *                          passes it to the field, unitOfMeasurement.
   */
  public void setUnitOfMeasurement(String unitOfMeasurement) {
    if (!unitOfMeasurement.equalsIgnoreCase("liter") && !unitOfMeasurement.equalsIgnoreCase("kilogram")) {
      throw new IllegalArgumentException("Unit of measurement must be liter or kilogram");
    }
    this.unitOfMeasurement = unitOfMeasurement;
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
   * If the provided parameter is less than zero, an IllegalArgumentException will be thrown.
   * This is to prevent a negative price, because a negative price is not possible.
   *
   * @param price Takes in parameter float price and passes it to the field, price.
   */
  public void setPrice(float price) {
    if (price < 0) {
      throw new IllegalArgumentException("The price can not be less than zero.");
    }
    this.price = price;
  }
}
