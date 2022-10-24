package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.softserve.dto.UserDto;
import ua.softserve.model.User;
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
    public String login(Model model,  UserDto userDto) {
        model.addAttribute("user", userDto);
        return "user/login";
    }

    @PostMapping("/post/login")
    public String signing(@ModelAttribute("user") UserDto userDto) {
        return "redirect:/get/add";
    }

    @GetMapping("/register")
    public String newUser(UserDto userDto, Model model) {
        model.addAttribute("userDto", userDto);
        return "user/registration";
    }

    @PostMapping("/post/register")
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {


//            userDto.setBirthday(LocalDate.of(2000,9,12));
            userService.addUser(userDto);
        return "redirect:/list";
    }

}

