package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.UserCreateDto;
import ua.softserve.dto.UserReadDto;
import ua.softserve.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(Model model, UserReadDto userDto) {
        model.addAttribute("userReadDto", userDto);
        return "user/login";
    }
    @GetMapping("/register")
    public String newUser(UserCreateDto userDto, Model model) {
        model.addAttribute("userCreateDto", userDto);
        return "user/registration";
    }

    @PostMapping("/post/register")
    public String addUser(@ModelAttribute("userDto") UserCreateDto userDto) {
        userService.addUser(userDto);
        return "redirect:/list";
    }

}

