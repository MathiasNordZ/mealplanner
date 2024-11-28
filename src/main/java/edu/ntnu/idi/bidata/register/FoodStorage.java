package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.entity.Grocery;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static edu.ntnu.idi.bidata.register.FoodStorageValidator.*;

/**
 * This class represents a storage where instances of Grocery can be stored.
 * The storage can represent a fridge, drawer, freezer etc.
 *
 * @author <b>Mathias Erik Nord</b>
 * @since <b>05.11.2024</b>
 * @version <b>0.0.1</b>
 *
 */
public class FoodStorage {
  private final Map<String, List<Grocery>> groceries = new HashMap<>();

  /**
   * Constructor for <code>FoodStorage</code>.
   * Constructor is empty, because the idea is to have an empty storage,
   * that will be filled with instances of Grocery, with the according methods.
   */
  public FoodStorage() {
    // Currently empty
  }

  public Map<String, List<Grocery>> getGroceries() {
    return groceries;
  }
}