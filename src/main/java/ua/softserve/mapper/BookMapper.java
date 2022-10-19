package ua.softserve.mapper;

import ua.softserve.dto.BookDto;
import ua.softserve.model.Book;

public class BookMapper {
    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setGenre(book.getGenre());
        bookDto.setDescription(book.getDescription());
        bookDto.setISBN(book.getISBN());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setCoAuthors(book.getCoAuthors());
        bookDto.setQuantities(book.getQuantities());
        bookDto.setRequests(book.getRequests());
        return bookDto;
    }

    public static Book toModel(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setDescription(bookDto.getDescription());
        book.setISBN(bookDto.getISBN());
        book.setAuthor(bookDto.getAuthor());
        book.setCoAuthors(bookDto.getCoAuthors());
        book.setQuantities(bookDto.getQuantities());
        book.setRequests(bookDto.getRequests());
        return book;
    }
}
