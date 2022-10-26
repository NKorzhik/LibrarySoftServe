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


    @PreAuthorize("hasRole('ROLE_READER')")
    @GetMapping("pageReader")
    public String getRequestedBooks(Authentication auth, Model model){
        Optional<User> user = userService.findUserByEmail(auth.getName());
        //чем заменить гет?
        model.addAttribute("list", userService.getRequestedBooks(user.orElseThrow().getId()));
        return "reader/page-reader";
    }

    @PreAuthorize("hasRole('ROLE_READER')")
    @GetMapping("/getBook/{id}")
    public String getRequestInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("request", userService.getRequestById(id));
        return "reader/description-of-request";
    }

    @PreAuthorize("hasRole('ROLE_READER')")
    @GetMapping("/returnBook")
    public String returnBook(){
        return "redirect:/pageReader";
    }

//    @GetMapping("/readerSearch")
//    public String getBooksByTitle(String keyword, Model model){
//        List<HistoryOfRequest> books = userService.searchBook(keyword);
//        model.addAttribute("requested", books);
//        return "reader/page-reader";
//    }


    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("pageManager")
    public String getProfileOfManager(){
        return "manager/page-manager";
    }



}

