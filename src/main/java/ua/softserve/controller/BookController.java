package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.softserve.dto.book.BookCreateDto;
import ua.softserve.dto.book.BookReadUpdateDto;
import ua.softserve.model.Book;
import ua.softserve.model.PaginationResult;
import ua.softserve.service.AuthorService;
import ua.softserve.service.BookService;
import ua.softserve.service.PaginationResultService;
import ua.softserve.service.QuantityService;

import java.util.List;

@Controller
//@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final QuantityService quantityService;

    private final PaginationResultService paginationResultService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, QuantityService quantityService, PaginationResultService paginationResultService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.quantityService = quantityService;
        this.paginationResultService = paginationResultService;
    }


    @GetMapping("/list")
    public String getBook(Model model, @RequestParam( value = "pageNumber", required = false, defaultValue = "1") int pageNumber){
        PaginationResult<Book> bookPaginationResult = paginationResultService.paginate(pageNumber);
        model.addAttribute("books", bookPaginationResult);
        return "user/books";
    }


    @GetMapping("/getPopUnPopBook")
    public String getPopularAndUnpopularBook(String firstDate, String secondDate, Model model){
        List<Book> popular = bookService.getPopularBookInSelectedPeriod(firstDate, secondDate);
        List<Book> unpopular = bookService.getUnpopularBookInSelectedPeriod(firstDate, secondDate);
        model.addAttribute("popular",popular);
        model.addAttribute("unpopular",unpopular);
        return "user/pop-unpop-books";
    }

    @GetMapping("/search")
    public String getBooksByTitle(String keyword, Model model){
        List<BookReadUpdateDto> books = bookService.findBookByTitle(keyword);
        model.addAttribute("booksReadDto", books);
        return "user/books";
    }
    @GetMapping("/more/{id}")
    public String getMoreInfoAboutBook(@PathVariable("id") long id, Model model){
        BookReadUpdateDto book = bookService.getBook(id);
        //ИЗМЕНИТЬ МЕТОД ПОЛУЧЕНИЯ QUANTITY в DAO сервисе с использованием JOIN
        long quantity = bookService.getCountOfQuantityByBookId(book.getId());
        model.addAttribute("bookReadDto", book);
        model.addAttribute("quantity",quantity);
        return "user/description-of-book";
    }

    @GetMapping("/get/add")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String newBook(@ModelAttribute("bookCreateDto") BookCreateDto bookDto) {
        return "manager/add-book";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("bookReadDto", bookService.getBook(id));
        return "manager/edit-book";
    }

    @PostMapping("/post/add")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String create(@ModelAttribute("bookCreateDto") BookCreateDto bookDto) {
        if (bookDto.getAuthorDto().getName().equals("") || bookDto.getCoAuthorDto().getSurname().equals("")) {
            bookService.addBookWithMainAuthor(bookDto);
        } else {
            bookService.addBookWithMainAuthorAndCoAuthor(bookDto);
        }
        return "redirect:/list";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String update(@ModelAttribute("bookReadDto") BookReadUpdateDto bookDto) {
        return "redirect:/more/{id}";
    }

    @DeleteMapping("/delete/copy/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String deleteOneCopyById(@PathVariable("id") long id) {
        //возможно использовать bookService, а не quantityService!
        quantityService.deleteOneCopyById(id);
        return "redirect:/more/{id}";
    }
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return "redirect:/list";
    }

}