package edu.ntnu.idi.bidata;

import java.time.LocalDate;

/**
 * This class represents a grocery with the attributes quantity, price, name,
 * expiration date and unit of measurement.
 *
 * @author Mathias Erik Nord
 * @since 16.10.2024
 */
public class Grocery {
  private float quantity;
  private float price;
  private String name;
  private LocalDate expirationDate;
  private String unitOfMeasurement; // kilogram, liter

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
   * Accessor method for <code>quantity</code>
   *
   * @return Returns the value of the field, quantity.
   */
  public float getQuantity() {
    return quantity;
  }

  /**
   * Mutator method for <code>quantity</code>
   *
   * @param quantity Represents the quantity as the datatype float.
   *
   * @throws IllegalArgumentException if quantity is less than zero,
   *                                  because a negative quantity is not possible.
   */
  public void setQuantity(float quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity can not have a negative value.");
    }
    this.quantity = quantity;
  }

  /**
   * Accessor method for <code>name</code>
   *
   * @return Returns the value of field, name.
   */
  public String getName() {
    return name;
  }

  /**
   * Mutator method for <code>name</code>
   *
   * @param name Represents the name of a Grocery.
   *
   * @throws IllegalArgumentException if name is blank or empty.
   */
  public void setName(String name) {
    if (name == null || name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("Name can not be blank or empty.");
    }
    this.name = name;
  }

  /**
   * Accessor method for <code>expirationDate</code>
   *
   * @return Returns the value of field, expirationDate.
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Mutator method for <code>expirationDate</code>
   *
   * @param expirationDate Takes in parameter LocalDate expirationDate,
   *                       and passes it to the field, expirationDate.
   *
   * @throws IllegalArgumentException if expiration date is a date in the past.
   *                                  This is because you should not create an expired grocery.
   */
  public void setExpirationDate(LocalDate expirationDate) {
    if (expirationDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("You can not set an expiration date of the past.");
    }
    this.expirationDate = expirationDate;
  }

  /**
   * Accessor method for <code>unitOfMeasurement</code>
   *
   * @return Returns the field unitOfMeasurement.
   */
  public String getUnitOfMeasurement() {
    return unitOfMeasurement;
  }

  /**
   * Mutator method for <code>unitOfMeasurement</code>
   *
   * @param unitOfMeasurement Represents the unitOfMeasurement that the quantity is measured in.
   *                          Either liter or kilogram.
   *
   * @throws IllegalArgumentException if provided parameter does not match the string value "liter" or "kilogram".
   *                                  Case of the parameter does not matter.
   */
  public void setUnitOfMeasurement(String unitOfMeasurement) {
    if (!unitOfMeasurement.equalsIgnoreCase("liter") && !unitOfMeasurement.equalsIgnoreCase("kilogram")) {
      throw new IllegalArgumentException("Unit of measurement must be liter or kilogram");
    }
    this.unitOfMeasurement = unitOfMeasurement;
  }

  /**
   * Accessor method for <code>price</code>
   *
   * @return Returns the field price.
   */
  public float getPrice() {
    return price;
  }

  /**
   * Mutator method for <code>price</code>
   *
   * @param price Takes in parameter float price and passes it to the field, price.
   *
   * @throws IllegalArgumentException if the provided parameter is less than zero.
   *                                  This is to prevent a negative price.
   */
  public void setPrice(float price) {
    if (price < 0) {
      throw new IllegalArgumentException("The price can not be less than zero.");
    }
    this.price = price;
  }

  /**
   * This method will override, such that when you return an object, you will be returned the fields
   * of the object as a string. Instead of getting the memory id of the grocery. Ex. Grocery@5b2133b1
   *
   * @return Will return fields of the object as a String.
   */
  @Override
  public String toString() {
    return "Name : " + name +
            ", Price : " + price +
            ", Quantity : " + quantity +
            ", Unit of measurement : " + unitOfMeasurement +
            ", Expiration date : " + expirationDate;
  }
}
