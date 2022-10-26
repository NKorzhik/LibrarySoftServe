package ua.softserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.softserve.dto.BookReadUpdateDto;
import ua.softserve.dto.RequestCreateDto;
import ua.softserve.service.BookService;
import ua.softserve.service.RequestService;

@Controller
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;
    private final BookService bookService;

    @Autowired
    public RequestController(RequestService requestService, BookService bookService) {
        this.requestService = requestService;
        this.bookService = bookService;
    }

    @PostMapping("/add/{bookId}")
    public String add(@PathVariable("bookId") long bookId, RequestCreateDto requestDto) {
        /*BookReadUpdateDto bookDto = bookService.getBook(bookId);
        requestDto.setBookDto(bookDto);*/
        requestService.addRequest(requestDto, bookId);
        return "redirect:/list";
    }
}
