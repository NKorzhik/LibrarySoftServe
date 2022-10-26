package ua.softserve.mapper;

import ua.softserve.dto.BookReadUpdateDto;
import ua.softserve.model.Book;

public class BookReadUpdateMapper {
    public static BookReadUpdateDto mapToDto(Book book) {
        BookReadUpdateDto bookDto = BookReadUpdateDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(book.getGenre())
                .description(book.getDescription())
                .ISBN(book.getISBN())
                .authorDto(AuthorMapper
                        .mapToDto(book.getAuthor()))
                //.quantities(book.getQuantities())
                .build();
        if (book.getCoAuthor() != null) {
            bookDto.setCoAuthorDto(AuthorMapper
                    .mapToDto(book.getCoAuthor()));
        }
        return bookDto;
    }

    public static Book mapToModel(BookReadUpdateDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .genre(bookDto.getGenre())
                .description(bookDto.getDescription())
                .ISBN(bookDto.getISBN())
                .author(AuthorMapper
                        .mapToModel(bookDto.getAuthorDto()))
                //возможно тоже сделать проверку на null? но у нас есть проверка на наличие соавтора в контроллере
                .coAuthor(AuthorMapper
                        .mapToModel(bookDto.getCoAuthorDto()))
                //.quantities(bookDto.getQuantities())
                .build();
    }
}
