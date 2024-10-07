package edu.ntnu.idi.bidata;

import java.util.Map;

/**
 * This class is a storage for groceries.
 */
public class FoodStorage {
  private Map<String, Grocery> groceries;

  public FoodStorage() {
    // Currently empty
  }

  public void addGrocery(Grocery grocery) {
    groceries.put(grocery.getName(), grocery);
  }

  public void removeGrocery(String nameOfGrocery) {
    groceries.remove(nameOfGrocery);
  }

  public Grocery searchGrocery(String nameOfGrocery) {
    return groceries.get(nameOfGrocery);
  }
  /*
  public void printAllGroceries() {

  }

  public void printExpiredGroceries() {

  }

  public float valueOfExpiredGroceries() {

  }

  public float valueOfAllGroceries() {

  }
   */
}