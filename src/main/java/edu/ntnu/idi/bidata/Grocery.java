package edu.ntnu.idi.bidata;

import java.time.LocalDate;

/**
 *
 * @author Mathias Erik Nord
 * @since 16.10.2024
 *
 * This class is representing an entinity class.
 * * The entinity/grocery has a given quantity, price, name, expiry date and unit of measurement.
 *
 * The role of this class is to represent an entinity, more specificly a grocery. The class is holding the quantity, price,
 * name, expiration date and unit of measurement of a grocery.
 *
 * The field quantity is of the datatype float, because the quantity
 * of a grocery does not necessarily have to be a whole number. It is neither necessary to use the datatype double, because you do not
 * need the extra precision of double, in this usecase.
 *
 * The field price is of the datatype float, because the price
 * of a grocery does not necessarily have to be a whole number. It is neither necessary to use the datatype double, because you do not
 * need the extra precision of double, in this usecase.
 *
 * The field name is of the datatype String, because a name is usually multiple characters.
 *
 * The field expirationDate is of the type LocalDate. LocalDate is a Class that is used for implementing dates. It contains a lot of useful methods
 *    for manipulating dates, comparing dates, setting dates etc. Using LocalDate rather than a String is better, because it gives more room for
 *    comparing dates, and manipulating dates.
 *
 * The field unitOfMeasurement is of the type String. This is because you can choose units such as "kg, g, dl" Units can be more than one character,
 *    hence why it does make more sense to use datatype String than datatype char.
 */
public class Grocery {
  private float quantity;
  private float price;
  private String name;
  private LocalDate expirationDate;
  private String unitOfMeasurement;

  /**
   * This is the constructor for the grocery class. When creating an instance of the class, you are required to initally set the values,
   * quantity, name, unitOfMeasurement, price, and expriy date. All these fields are fundamental for the program to work as intended
   * according to the project requirements. All values are set initially, but it is possible to use the
   * provided getters and setters to adjust the value of the fields after the instanciation.
   *
   * The error handling when setting the fields in the constructor is handled by the set methods.
   * Please refer to the setters for more information about error handling.
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
   * This is to prevent the field quantity to be negative, because a negative quantity should not be possible.
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
   * If the provided parameter is blank or is empty, an IllegalArgumentException will be thrown.
   * This is to prevent the field name to be invalid or empty.
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
   * If the provided parameter contains a date that is in the past, an IllegalArgumentException.
   * This is because you should not be able to add expired products to your storage.
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
   * If the provided parameter is blank or empty, and IllegalArgumentException will be thrown.
   * This is to prevent the field unitOfMeasurement to be invalid or empty.
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

  /**
   * Print method for price, will print the price of a Grocery.
   */
  public void printPrice() {
    System.out.println("Price of grocery: " + this.getPrice());
  }
}
