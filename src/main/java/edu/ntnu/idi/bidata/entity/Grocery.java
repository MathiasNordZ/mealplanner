package edu.ntnu.idi.bidata.entity;

import edu.ntnu.idi.bidata.util.GroceryFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * This class represents a grocery with the attributes quantity, price, name,
 * expiration date and unit of measurement.
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>16.10.2024</b>
 * @version <b>0.0.1</b>
 */
public class Grocery {
  private BigDecimal quantity;
  private BigDecimal price;
  private String name;
  private LocalDate expirationDate; // 'YYYY-MM-DD'
  private String unitOfMeasurement; // kilogram, liter, pcs

  /**
   * Constructor for the Grocery class. Initializes the quantity, name, unitOfMeasurement,
   * and expiry date.
   * The constructor uses setters and getters to reduce duplication.
   *
   * @param quantity This is the quantity of the grocery.
   * @param name This is the name of the grocery.
   * @param unitOfMeasurement This is the unit of measurement that the grocery is measured in.
   * @param price This is the price of the grocery.
   * @param expirationDate This is the expiration date of the grocery. Format: 'YYYY-MM-DD'.
   *
   */
  public Grocery(BigDecimal quantity, String name, String unitOfMeasurement,
                 BigDecimal price, String expirationDate) {
    setQuantity(quantity);
    setName(name);
    setExpirationDate(expirationDate);
    setUnitOfMeasurement(unitOfMeasurement);
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
    this.name = GroceryFormatter.normalizedString(name);
  }

  /**
   * Accessor method for <code>expirationDate</code>.
   *
   * @return the value of expirationDate.
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Mutator method for <code>expirationDate</code>.
   *
   * @param dateString String parameter that will represent the
   *                   expiry date on the format 'YYYY-MM-DD'.
   *
   * @throws IllegalArgumentException if expiration date is not on the correct format.
   */
  public void setExpirationDate(String dateString) {
    try {
      this.expirationDate = LocalDate.parse(dateString);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("You have entered an invalid date!");
    }
  }

  /**
   * Accessor method for <code>unitOfMeasurement</code>.
   *
   * @return the field unitOfMeasurement.
   */
  public String getUnitOfMeasurement() {
    return unitOfMeasurement;
  }

  /**
   * Mutator method for <code>unitOfMeasurement</code>.
   *
   * @param unitOfMeasurement the unit of measurement that the quantity is measured in.
   *                          Either liter, kilogram or pcs.
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
    this.unitOfMeasurement = GroceryFormatter.normalizedString(unitOfMeasurement);
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