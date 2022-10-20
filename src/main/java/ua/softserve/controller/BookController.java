package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.BookDto;
import ua.softserve.dto.BookCreateDto;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.model.Quantity;
import ua.softserve.service.AuthorService;
import ua.softserve.service.BookService;
import ua.softserve.service.QuantityService;

import java.util.List;

@Controller
//@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final QuantityService quantityService;

    @Autowired
    public BookController(BookService bookService,
                          AuthorService authorService,
                          QuantityService quantityService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.quantityService = quantityService;
    }

    @GetMapping("/get/add")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String newBook(@ModelAttribute("book") BookCreateDto bookDto) {
        return "manager/add-book";
    }
    @PostMapping("/post/add")
    public String create(@ModelAttribute("book") BookCreateDto bookDto,
                         BindingResult result) {
        if (bookDto.getAuthorDto().getName().equals("") || bookDto.getCoAuthorDto().getSurname().equals("")) {
            bookService.addBookWithMainAuthor(bookDto);
        } else {
            bookService.addBookWithMainAuthorAndCoAuthor(bookDto);
        }
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getBook(Model model) {
        List<BookDto> books = bookService.listBook();
        model.addAttribute("books", books);
        return "user/books";
    }

    @GetMapping("/more/{id}")
    public String getMoreInfoAboutBook(@PathVariable("id") long id, Model model){
        Book book = bookService.getBook(id);
        //ИЗМЕНИТЬ МЕТОД ПОЛУЧЕНИЯ QUANTITY в DAO сервисе с использованием JOIN
        long quantity = bookService.getCountOfQuantityByBookId(book.getId());
        model.addAttribute("book", book);
        model.addAttribute("quantity",quantity);
        return "user/description-of-book";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOneCopyById(@PathVariable("id") long id) {
        quantityService.deleteOneCopyById(id);
        return "redirect:/more/{id}";
    }
    @RequestMapping("/search")
    public String getBooksByTitle(String keyword, Model model){
        List<BookDto> books = bookService.findBookByTitle(keyword);
        model.addAttribute("books", books);
        return "user/books";
    }


    /*public List<BookDto> setAuthors(List<BookDto> books){
        for (BookDto book : books) {
            Author author = authorService.getAuthor(book.getAuthor().getId());
            Author coAuthor = authorService.getAuthor(book.getCoAuthors().getId());
            book.setAuthor(author);
            book.setCoAuthors(coAuthor);
        }
        return books;
    }*/
}