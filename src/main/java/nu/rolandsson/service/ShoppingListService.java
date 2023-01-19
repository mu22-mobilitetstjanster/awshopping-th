package nu.rolandsson.service;

import nu.rolandsson.model.ShoppingItem;
import nu.rolandsson.model.ShoppingList;
import nu.rolandsson.repository.ShoppingListRepository;

public class ShoppingListService {

  private ShoppingListRepository listRepository;

  public ShoppingListService() {
    this.listRepository = new ShoppingListRepository();
  }

  public ShoppingList getShoppingList(String username) {
    ShoppingList list = listRepository.getShoppingList(username);

    if(list == null) {
      //listRepository.createNew(username);
      list = new ShoppingList(username);
    }

    return list;
  }

  public void addShoppingItem(String username, ShoppingItem item) {
    listRepository.addItem(username, item);
  }
}
