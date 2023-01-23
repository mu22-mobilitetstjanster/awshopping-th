package nu.rolandsson.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import nu.rolandsson.exception.InvalidPasswordException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/userSession/*")
public class UserSessionController {


  @ExceptionHandler(InvalidPasswordException.class)
  public String handleInvalidPassword(InvalidPasswordException ex, HttpSession session, HttpServletRequest req) {

    Object loginAttempts = session.getAttribute("loginAttempts");
    if(loginAttempts == null) {
      loginAttempts = 0;
    }

    session.setAttribute("loginAttempts", (int) loginAttempts + 1);

    return "redirect:login?error=" + ex.getMessage();
  }


  @GetMapping("login")
  public String getLoginPage() {
    return "loginPage";
  }

  @PostMapping("login")
  public String login(HttpSession session, RedirectAttributes redirect, @RequestParam String username, @RequestParam String password) {

    if(session.getAttribute("username") != null) {
      return "redirect:/shoppingList";
    } else {

      if(username.equals("bob")) {
        if(password.equals("123")) {
          //if (username != null) {
          session.setMaxInactiveInterval(60 * 30);
          session.setAttribute("username", username);

          return "redirect:/shoppingList";
        } else {
          throw new InvalidPasswordException("Invalid password attempt", username);
        }
      }
      return "redirect:login";
    }
  }

  @PostMapping("logout")
  public String logout(HttpSession session) throws IOException {
    session.invalidate(); //Invalidate - empty the session
    return "redirect:/index.html";
  }
}
