package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.BookDto;
import ua.softserve.dto.CreateBookDto;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.service.AuthorService;
import ua.softserve.service.BookService;

import java.util.List;

@Controller
//@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/get/add")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String newBook(@ModelAttribute("book") CreateBookDto bookDto) {
        return "manager/add-book";
    }
    @PostMapping("/post/add")
    public String create(@ModelAttribute("book") CreateBookDto bookDto,
                         BindingResult result) {
        //изменить проверку, т.к значение по умолчанию ""
        if (bookDto.getCoAuthorName() == null || bookDto.getCoAuthorSurname() == null) {
            bookService.addBookWithMainAuthor(bookDto);
        } else bookService.addBookWithMainAuthorAndCoAuthor(bookDto);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getBook(Model model) {
        List<BookDto> books = bookService.listBook();
        model.addAttribute("books", books);
        return "user/books";
    }

    @GetMapping("/more/{id}")
    public String getMoreInfoAboutBook(@PathVariable(value = "id") long id, Model model){
        Book book = bookService.getBook(id);
        long quantity = bookService.getCountOfQuantityByBookId(book.getId());
        model.addAttribute("book", book);
        model.addAttribute("quantity",quantity);
        return "user/description-of-book";
    }
    @RequestMapping("/search")
    public String getBooksByTitle(String keyword, Model model){
        List<BookDto> books = bookService.findBookByTitle(keyword);
        model.addAttribute("books", books);
        return "user/books";
    }

//    @RequestMapping("/search2")
//    public String getBooksByAuthor(String keyword, Model model){
//        List<BookDto> books = bookService.findBookByAuthor(keyword);
//        model.addAttribute("books", books);
//        return "user/books";
//    }

}