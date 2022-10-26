package ua.softserve.mapper;

import ua.softserve.dto.BookCreateDto;
import ua.softserve.model.Book;

public class BookCreateMapper {
    public static Book mapToModel(BookCreateDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setDescription(bookDto.getDescription());
        book.setISBN(bookDto.getISBN());
        return book;
    }
}
