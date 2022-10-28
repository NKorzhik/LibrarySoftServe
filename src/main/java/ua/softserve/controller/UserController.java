package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.BookReadUpdateDto;
import ua.softserve.dto.UserCreateDto;
import ua.softserve.dto.UserReadDto;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.User;
import ua.softserve.service.UserService;

import java.util.List;
import java.util.Optional;

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


//    @GetMapping("/readerSearch")
//    public String getBooksByTitle(String keyword, Model model){
//        List<HistoryOfRequest> books = userService.searchBook(keyword);
//        model.addAttribute("requested", books);
//        return "reader/page-reader";
//    }


}

