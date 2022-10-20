package ua.softserve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.softserve.model.enums.Genre;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {
    private String title;
    private Genre genre;
    private String description;
    private String ISBN;
    private AuthorCreateDto authorDto;
    private AuthorCreateDto coAuthorDto;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCreateDto bookDto = (BookCreateDto) o;
        return title.equals(bookDto.title) && ISBN.equals(bookDto.ISBN);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, ISBN);
    }
}
