package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/user")
    public String show(ModelMap modelMap, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        modelMap.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/login")
    public String login(Model model, String logout) {
        if (logout != null) {
            model.addAttribute("message", "Logged out.");
        }

        return "login";
    }
}