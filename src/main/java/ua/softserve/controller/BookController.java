package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.softserve.model.Book;
import ua.softserve.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @GetMapping("/list")
    public String getBook(Model model){
        List<Book> books = bookService.listBook();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "books";
    }
}
