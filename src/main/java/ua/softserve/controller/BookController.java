package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @GetMapping("/list")
    public String getBook(Model model){
        List<Book> books = bookService.listBook();
//        List<Author> authors = new ArrayList<>();
//        for (Book book : books) {
//            authors.add(authorService.getAuthor(book.getAuthor().getId()));
//        }
        model.addAttribute("books", books);
        //model.addAttribute("authors",authors);
        return "books";
    }


    @GetMapping("/more/{id}")
    public String getMoreInfoAboutBook(@PathVariable(value = "id") long id ,Model model){
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




    @PostMapping()
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "books";
    }
}