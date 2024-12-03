package com.project_sql.online_shop.controllers;

import com.project_sql.online_shop.dtos.UserDto;
import com.project_sql.online_shop.services.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String authenticateUser(@RequestParam("login") String login,
      @RequestParam("password") String password,
      HttpSession session, Model model) {
    boolean isAuthenticated = userService.authenticate(login, password);

    if (isAuthenticated) {
      session.setAttribute("login", login);
      return "redirect:/api/users/home";
    } else {
      model.addAttribute("error", "Invalid login or password.");
      return "login";
    }
  }

  @GetMapping("/home")
  public String homePage(Model model) {
    List<UserDto> users = userService.getAllUsers();
    model.addAttribute("users", users);
    return "home";
  }

  @PostMapping("/new-user")
  public String createUser(@RequestParam("username") String username,
      @RequestParam("login") String login,
      @RequestParam("password") String password) {
    userService.addUser(new UserDto(null, username, login, password));
    return "redirect:/api/users/home";
  }

  @PutMapping("/{id}/edit")
  public String updateUser(@PathVariable("id") Long id,
      @RequestParam("username") String username,
      @RequestParam("login") String login,
      @RequestParam("password") String password) {
    userService.updateUser(id, new UserDto(id, username, login, password));
    return "redirect:/api/users/home";
  }

  @DeleteMapping("/{id}/delete")
  public String deleteUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);
    return "redirect:/api/users/home";
  }
}
