package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.user.UserCreateDto;
import ua.softserve.dto.user.UserLoginDto;
import ua.softserve.dto.user.UserReadDto;
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
    public String login(Model model, UserLoginDto userDto) {
        model.addAttribute("userLoginDto", userDto);
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


//    @GetMapping("/readerSearch")
//    public String getBooksByTitle(String keyword, Model model){
//        List<HistoryOfRequest> books = userService.searchBook(keyword);
//        model.addAttribute("requested", books);
//        return "reader/page-reader";
//    }


}

