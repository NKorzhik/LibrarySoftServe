package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String newBook(@ModelAttribute("book") CreateBookDto bookDto) {
        return "add-book";
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
        setAuthors(books);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/more/{id}")
    public String getMoreInfoAboutBook(@PathVariable(value = "id") long id, Model model){
        Book book = bookService.getBook(id);
        Author author =  authorService.getAuthor(book.getAuthor().getId());
        Author co_author = authorService.getAuthor(book.getCoAuthors().getId());
        long quantity = bookService.getCountOfQuantityByBookId(book.getId());
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        model.addAttribute("co_author", co_author);
        model.addAttribute("quantity",quantity);
        return "description-of-book";
    }
    @RequestMapping("/search")
    public String getBooksByTitle(String keyword, Model model){
        List<BookDto> books = bookService.findBookByTitle(keyword);
        setAuthors(books);
        model.addAttribute("books", books);
        return "books";
    }
    public List<BookDto> setAuthors(List<BookDto> books){
        for (BookDto book : books) {
            Author author = authorService.getAuthor(book.getAuthor().getId());
            Author coAuthor = authorService.getAuthor(book.getCoAuthors().getId());
            book.setAuthor(author);
            book.setCoAuthors(coAuthor);
        }
        return books;
    }
}