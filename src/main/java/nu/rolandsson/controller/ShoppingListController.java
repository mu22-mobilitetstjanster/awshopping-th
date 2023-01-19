package nu.rolandsson.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import nu.rolandsson.model.ShoppingItem;
import nu.rolandsson.model.ShoppingList;
import nu.rolandsson.service.ShoppingListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/shoppingList")
public class ShoppingListController {

  private ShoppingListService listService;

  public ShoppingListController() {
    listService = new ShoppingListService();
  }

  @GetMapping
  protected String showShoppingList(Model model, HttpSession session) {
    String username = (String) session.getAttribute("username");

    ShoppingList userShoppingList = listService.getShoppingList(username);

    model.addAttribute("items", userShoppingList.getItemList());

    return "shoppingListPage";
  }

  @PostMapping
  public String addItem(HttpSession session, @ModelAttribute ShoppingItem shoppingItem) {
    String username = (String) session.getAttribute("username");

    listService.addShoppingItem(username, shoppingItem);

    return "redirect:/shoppingList";
  }
}
