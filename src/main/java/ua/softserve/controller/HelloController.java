package ua.softserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/test1")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/test")
    public String sayTest() {
        return "test";
    }
}
