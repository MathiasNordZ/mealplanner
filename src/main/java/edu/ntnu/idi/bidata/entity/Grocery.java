package edu.ntnu.idi.bidata.entity;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * This class represents a grocery with the attributes quantity, price, name,
 * expiration date and unit of measurement.
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>16.10.2024</b>
 * @version 0.1.1
 */
public class Grocery {
  private float quantity;
  private float price;
  private String name;
  private LocalDate expirationDate; // 'YYYY-MM-DD'
  private String unitOfMeasurement; // kilogram, liter, pcs

  /**
   * Constructor for the Grocery class. Initializes the quantity, name, unitOfMeasurement,
   * and expiry date.
   * The constructor uses setters and getters for error handling.
   *
   * @param quantity This is the quantity of the grocery.
   * @param name This is the name of the grocery.
   * @param unitOfMeasurement This is the unit of measurement that the grocery is measured in.
   * @param price This is the price of the grocery.
   * @param expirationDate This is the expiration date of the grocery. Format: 'YYYY-MM-DD'.
   *
   */
  public Grocery(float quantity, String name, String unitOfMeasurement,
                 float price, String expirationDate) {
    setQuantity(quantity);
    setName(name);
    setExpirationDate(expirationDate);
    setUnitOfMeasurement(unitOfMeasurement);
    setPrice(price);
  }

  /**
   * Accessor method for <code>quantity</code>.
   *
   * @return Returns the value of the field, quantity.
   */
  public float getQuantity() {
    return quantity;
  }

  /**
   * Mutator method for <code>quantity</code>.
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
   * Accessor method for <code>name</code>.
   *
   * @return Returns the value of field, name.
   */
  public String getName() {
    return name;
  }

  /**
   * Mutator method for <code>name</code>.
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
   * Accessor method for <code>expirationDate</code>.
   *
   * @return Returns the value of field, expirationDate.
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Mutator method for <code>expirationDate</code>.
   *
   * @param dateString String parameter that will represent the
   *                   expiry date on the format "YYYY-MM-DD".
   *
   * @throws IllegalArgumentException if expiration date is not on the correct format.
   */
  public void setExpirationDate(String dateString) {
    try {
      this.expirationDate = LocalDate.parse(dateString);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Please enter date on the format 'YYYY-MM-DD'.");
    }
  }

  /**
   * Accessor method for <code>unitOfMeasurement</code>.
   *
   * @return Returns the field unitOfMeasurement.
   */
  public String getUnitOfMeasurement() {
    return unitOfMeasurement;
  }

  /**
   * Mutator method for <code>unitOfMeasurement</code>.
   *
   * @param unitOfMeasurement Represents the unitOfMeasurement that the quantity is measured in.
   *                          Either liter or kilogram.
   *
   * @throws IllegalArgumentException if provided parameter does not match
   *                                     the string value "liter" or "kilogram".
   */
  public void setUnitOfMeasurement(String unitOfMeasurement) {
    if (!unitOfMeasurement.equalsIgnoreCase("liter")
            && !unitOfMeasurement.equalsIgnoreCase("kilogram")
            && !unitOfMeasurement.equalsIgnoreCase("pcs")) {
      throw new IllegalArgumentException("Unit of measurement must be liter, kilogram or pcs");
    }
    this.unitOfMeasurement = unitOfMeasurement;
  }

  /**
   * Accessor method for <code>price</code>.
   *
   * @return Returns the field price.
   */
  public float getPrice() {
    return price;
  }

  /**
   * Mutator method for <code>price</code>.
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
}