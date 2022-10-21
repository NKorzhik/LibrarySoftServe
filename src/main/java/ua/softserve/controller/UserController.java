package ua.softserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.softserve.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login(Model model, User user) {
        model.addAttribute("user", user);
        return "user/login";
    }

    @PostMapping
    public String login(@ModelAttribute("user") User user) {
        return "redirect:/get/add";
    }
}

