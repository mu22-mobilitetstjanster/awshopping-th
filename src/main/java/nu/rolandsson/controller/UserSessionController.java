package nu.rolandsson.controller;

import jakarta.servlet.http.HttpSession;
import nu.rolandsson.model.AuthDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/userSession/*")
public class UserSessionController {

  @GetMapping("login")
  public String showLoginPage() {
    return "loginPage";
  }

  @PostMapping("login")
  public String login(HttpSession session, RedirectAttributes redirect, @ModelAttribute AuthDetails auth) {

    if(session.getAttribute("username") != null) {
      return "redirect:/shoppingList";
    } else {

      //if(auth.getUsername().equals("bob") && auth.getPassword().equals("123")) {
      if (auth.getUsername() != null) {
        session.setMaxInactiveInterval(60 * 30);
        session.setAttribute("username", auth.getUsername());

        return "redirect:/shoppingList";
      } else {
        Object loginAttempts = session.getAttribute("loginAttempts");
        if(loginAttempts == null) {
          loginAttempts = 0;
        }

        session.setAttribute("loginAttempts", (int) loginAttempts + 1);
        redirect.addAttribute("error", "Invalid username or password");
        redirect.addAttribute("status", "Right now server is very busy");

        return "redirect:login";
      }
    }
  }

  @PostMapping("logout")
  public String logout(HttpSession session) throws IOException {
    session.invalidate(); //Invalidate - empty the session
    return "redirect:/index.html";
  }
}
