package ua.softserve.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "user/registration";
    }

    @GetMapping("/")
    public String successAuthorization() {
        if (true) {
            return "redirect:/test1";
        } else return "redirect:/test";
    }
}
