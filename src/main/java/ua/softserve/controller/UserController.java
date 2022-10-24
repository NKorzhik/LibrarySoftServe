package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.UserDto;
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
    public String login() {
        return "user/login";
    }


    @GetMapping("/register")
    public String newUser(UserDto userDto, Model model) {
        model.addAttribute("userDto", userDto);
        return "user/registration";
    }

    @PostMapping("/post/register")
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/list";
    }

}

