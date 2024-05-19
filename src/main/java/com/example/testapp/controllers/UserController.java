package com.example.testapp.controllers;


import com.example.testapp.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<>();

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        Random random = new Random();
        user.setId(random.nextInt());
        users.add(user);
        return "redirect:/users";
    }
}
