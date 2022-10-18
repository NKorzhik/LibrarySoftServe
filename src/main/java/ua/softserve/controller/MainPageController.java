package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.softserve.model.Book;
import ua.softserve.service.BookService;

import java.util.List;

@Controller
public class MainPageController {
    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String getBook(Model model){
        List<Book> books = bookService.listBook();
        model.addAttribute("books", books);
        return "books";
    }
}
