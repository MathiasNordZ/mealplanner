package edu.ntnu.iir.bidata;

import java.util.Map;

public class Recipe {
  private String dishName;
  private String descriptionOfDish;
  private String cookingInstructions;
  private Map<String, Integer> ingredients;

  FoodStorage foodStorage = new FoodStorage();

  public Recipe(String dishName, String descriptionOfDish, String cookingInstructions, Map<String, Integer> ingredients) {
    this.dishName = dishName;
    this.descriptionOfDish = descriptionOfDish;
    this.cookingInstructions = cookingInstructions;
    this.ingredients = ingredients;
  }

  public Recipe() {
    this.dishName = "";
    this.descriptionOfDish = "";
    this.cookingInstructions = "";
    this.ingredients = null;
  }
  public String getDishName() {
    return dishName;
  }

  public void setDishName(String dishName) {
    this.dishName = dishName;
  }

  public String getDescriptionOfDish() {
    return descriptionOfDish;
  }

  public void setDescriptionOfDish(String descriptionOfDish) {
    this.descriptionOfDish = descriptionOfDish;
  }

  public String getCookingInstructions() {
    return cookingInstructions;
  }

  public void setCookingInstructions(String cookingInstructions) {
    this.cookingInstructions = cookingInstructions;
  }

  public Map<String, Integer> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Map<String, Integer> ingredients) {
    this.ingredients = ingredients;
  }
  /*
  public boolean isPossibleToMake(FoodStorage foodStorage) {

  }
   */
}
