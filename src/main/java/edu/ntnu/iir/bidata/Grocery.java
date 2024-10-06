package edu.ntnu.iir.bidata;

import java.util.Date;

public class Grocery {
  private float quantity;
  private String name;
  private Date expirationDate;
  private String unitOfMeasurement;

  public Grocery(float quantity, String name, Date expirationDate, String unitOfMeasurement) {
    this.quantity = quantity;
    this.name = name;
    this.expirationDate = expirationDate;
    this.unitOfMeasurement = unitOfMeasurement;
  }

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getUnitOfMeasurement() {
    return unitOfMeasurement;
  }

  public void setUnitOfMeasurement(String unitOfMeasurement) {
    this.unitOfMeasurement = unitOfMeasurement;
  }
}
