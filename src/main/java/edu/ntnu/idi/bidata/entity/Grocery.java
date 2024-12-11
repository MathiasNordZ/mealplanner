package edu.ntnu.idi.bidata.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * This class represents a grocery with the attributes quantity, price, name,
 * expiration date and unit of measurement.
 *
 * @author Mathias Erik Nord
 * @since 16.10.2024
 * @version 0.0.1
 */
public class Grocery {
  private BigDecimal quantity; // Quantity, ex 2.0
  private BigDecimal price; // Price of grocery
  private String name; // Name of grocery
  private LocalDate expiryDate; // 'YYYY-MM-DD'
  private String unit; // kilogram, liter, pcs

  /**
   * Constructor for the Grocery class. Initializes the quantity, name, unit,
   * and expiry date.
   * The constructor uses setters and getters to reduce duplication.
   *
   * @param quantity This is the quantity of the grocery.
   * @param name This is the name of the grocery.
   * @param unit This is the unit of measurement that the grocery is measured in.
   * @param price This is the price of the grocery.
   * @param expiryDate This is the expiration date of the grocery. Format: 'YYYY-MM-DD'.
   *
   */
  public Grocery(BigDecimal quantity, String name, String unit,
                 BigDecimal price, String expiryDate) {
    setQuantity(quantity);
    setName(name);
    setExpiryDate(expiryDate);
    setUnit(unit);
    setPrice(price);
  }

  /**
   * Accessor method for <code>quantity</code>.
   *
   * @return the value of the field, quantity.
   */
  public BigDecimal getQuantity() {
    return quantity;
  }

  /**
   * Mutator method for <code>quantity</code>.
   *
   * @param quantity Represents the quantity of a grocery.
   *
   * @throws IllegalArgumentException if quantity is less than zero,
   *                                  because a negative quantity is not possible.
   */
  public void setQuantity(BigDecimal quantity) {
    if (quantity.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Quantity can not have a negative value.");
    }
    this.quantity = quantity.setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * Accessor method for <code>name</code>.
   *
   * @return The value of name.
   */
  public String getName() {
    return name;
  }

  /**
   * Mutator method for <code>name</code>.
   *
   * @param name the name of a Grocery.
   *
   * @throws IllegalArgumentException if name is null, blank or empty.
   */
  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name can not be null, blank or empty.");
    }
    this.name = name;
  }

  /**
   * Accessor method for <code>expirationDate</code>.
   *
   * @return the value of expirationDate.
   */
  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  /**
   * Mutator method for <code>expiryDate</code>.
   *
   * @param dateInput String parameter that will represent the
   *                   expiry date on the format 'YYYY-MM-DD'.
   *
   * @throws IllegalArgumentException if expiration date is not on the correct format.
   */
  public void setExpiryDate(String dateInput) {
    try {
      this.expiryDate = LocalDate.parse(dateInput);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("You have entered an invalid date!");
    }
  }

  /**
   * Accessor method for <code>unit</code>.
   *
   * @return the value of field unit.
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Mutator method for <code>unit</code>.
   *
   * @param unit the unit of measurement that the quantity is measured in.
   *                          Either liter, kilogram or pcs.
   *
   * @throws IllegalArgumentException if provided parameter does not match
   *                                     the string value "liter" or "kilogram".
   */
  public void setUnit(String unit) {
    if (!unit.equalsIgnoreCase("liter")
            && !unit.equalsIgnoreCase("kilogram")
            && !unit.equalsIgnoreCase("pcs")) {
      throw new IllegalArgumentException("Unit of measurement must be liter, kilogram or pcs");
    }
    this.unit = unit;
  }

  /**
   * Accessor method for <code>price</code>.
   *
   * @return the price of the grocery.
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * Mutator method for <code>price</code>.
   *
   * @param price parameter price that is passed to the field price.
   *
   * @throws IllegalArgumentException if the provided parameter is less than zero.
   *                                  This is to prevent a negative price.
   */
  public void setPrice(BigDecimal price) {
    if (price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("The price can not be less than zero.");
    }
    this.price = price.setScale(2, RoundingMode.HALF_UP);
  }
}