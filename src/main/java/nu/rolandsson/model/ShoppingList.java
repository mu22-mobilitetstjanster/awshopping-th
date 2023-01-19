package nu.rolandsson.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

  private List<ShoppingItem> itemList;
  private String owner;

  public ShoppingList(String owner) {
    this.owner = owner;
    this.itemList = new ArrayList<>();
  }

  public String getOwner() {
    return owner;
  }

  public List<ShoppingItem> getItemList() {
    return itemList;
  }
}
