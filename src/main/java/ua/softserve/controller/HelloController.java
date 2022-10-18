package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.softserve.dao.BookDao;
//import ua.softserve.dao.BookDaoImpl;
import ua.softserve.dao.impl.BookDaoImpl;
import ua.softserve.model.Book;
import ua.softserve.service.BookService;

import java.util.List;

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
