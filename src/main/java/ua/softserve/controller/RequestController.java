package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.softserve.dto.request.RequestReadDto;
import ua.softserve.model.User;
import ua.softserve.service.BookService;
import ua.softserve.service.RequestService;
import ua.softserve.service.UserService;

import java.util.Optional;

@Controller
//@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public RequestController(RequestService requestService,
                             BookService bookService,
                             UserService userService) {
        this.requestService = requestService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping("/add/{bookId}")
    public String add(@PathVariable("bookId") long bookId) {
        /*BookReadUpdateDto bookDto = bookService.getBook(bookId);
        requestDto.setBookDto(bookDto);*/
        requestService.addRequest(bookId);
        return "redirect:/list";
    }

    @PreAuthorize("hasRole('ROLE_READER')")
    @GetMapping("/pageReader")
    public String getRequestedBooks(Authentication auth, Model model){
        Optional<User> user = userService.findUserByEmail(auth.getName());
        model.addAttribute("requests", requestService.getRequestedBooks(user.orElseThrow().getId()));
        return "reader/page-reader";
    }


    @PreAuthorize("hasRole('ROLE_READER')")
    @GetMapping("/getBook/{id}")
    public String getRequestInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("request", requestService.getRequestById(id));
        return "reader/description-of-request";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/pageManager")
    public String getRequestedBooksWithStatusWaiting(Model model){
        //в модель добавить метод на получений доступных копий
        model.addAttribute("list", requestService.getBooksWithStatusWaiting());
        return "manager/page-manager";
    }

    @PreAuthorize("hasRole('ROLE_READER')")
    @PostMapping("/returnBookToLibrary/{id}")
    public String returnBookToLibrary(@PathVariable("id") long requestId){
        requestService.returnBookToLibrary(requestId);
        return "redirect:/pageReader";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/accept/{id}")
    public String acceptRequest(@PathVariable("id") long id) {
        requestService.acceptRequest(id);
        return "redirect:/pageManager";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/reject/{id}")
    public String rejectRequest(@PathVariable("id") long id) {
        requestService.rejectRequest(id);
        return "redirect:/pageManager";
    }
}
