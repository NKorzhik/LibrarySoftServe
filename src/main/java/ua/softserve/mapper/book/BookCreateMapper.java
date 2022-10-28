package ua.softserve.mapper.book;

import ua.softserve.dto.book.BookCreateDto;
import ua.softserve.model.Book;

public class BookCreateMapper {
    public static Book mapToModel(BookCreateDto bookDto) {
       /* Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setDescription(bookDto.getDescription());
        book.setISBN(bookDto.getISBN());*/
        return Book.builder()
                .title(bookDto.getTitle())
                .genre(bookDto.getGenre())
                .description(bookDto.getDescription())
                .ISBN(bookDto.getISBN())
                .build();
        //return book;
    }
}
