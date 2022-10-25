package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.UserCreateDto;
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
    public String login(Model model, UserCreateDto userCreateDto) {
        //model.addAttribute("user", userDto);
        return "user/login";
    }

    /*@PostMapping("/post/login")
    public String signing(@ModelAttribute("user") UserDto userDto) {
        return "redirect:/list";
    }*/

    @GetMapping("/register")
    public String newUser(UserCreateDto userCreateDto, Model model) {
        model.addAttribute("userCreateDto", userCreateDto);
        return "user/registration";
    }

    @PostMapping("/post/register")
    public String addUser(@ModelAttribute("userCreateDto") UserCreateDto userCreateDto) {
        userService.addUser(userCreateDto);
        return "redirect:/list";
    }

}

