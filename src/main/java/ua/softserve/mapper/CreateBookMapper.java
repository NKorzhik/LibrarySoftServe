package ua.softserve.mapper;

import ua.softserve.dto.CreateBookDto;
import ua.softserve.model.Author;
import ua.softserve.model.Book;

import java.util.Collections;

public class CreateBookMapper {

    public static CreateBookDto toDto(Book book) {
        CreateBookDto bookDto = new CreateBookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setGenre(book.getGenre());
        bookDto.setDescription(book.getDescription());
        bookDto.setISBN(bookDto.getISBN());
        bookDto.setMainAuthorName(book.getAuthor().getName());
        bookDto.setMainAuthorSurname(book.getAuthor().getSurname());
        bookDto.setCoAuthorName(book.getCoAuthors().getName());
        bookDto.setCoAuthorSurname(book.getCoAuthors().getSurname());
        bookDto.setQuantity(book.getQuantities().size());
        return bookDto;
    }

    public static Book toModel(CreateBookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setDescription(bookDto.getDescription());
        book.setISBN(bookDto.getISBN());
        /*book.setAuthor(new Author(
                bookDto.getMainAuthorName(),
                bookDto.getCoAuthorSurname(),
                Collections.emptyList()));
        book.setCoAuthors(new Author(
                bookDto.getCoAuthorName(),
                bookDto.getCoAuthorSurname(),
                Collections.emptyList()
        ));*/
        return book;
    }
}
