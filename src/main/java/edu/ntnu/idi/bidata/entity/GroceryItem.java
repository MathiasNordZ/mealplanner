package edu.ntnu.idi.bidata.entity;

import java.time.LocalDate;

public interface GroceryItem {
  float getQuantity();

  void setQuantity(float quantity);

  String getName();

  LocalDate getExpirationDate();

  float getPrice();

  void setPrice(float price);

  String getUnitOfMeasurement();
}
