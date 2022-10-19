package ua.softserve.dto;

import lombok.*;
import ua.softserve.model.Author;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.Quantity;
import ua.softserve.model.enums.Genre;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;
    private String title;
    private Genre genre;
    private String description;
    private String ISBN;
    private Author author;
    private Author coAuthors;
    private List<Quantity> quantities;
    private List<HistoryOfRequest> requests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return title.equals(bookDto.title) && ISBN.equals(bookDto.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ISBN);
    }
}
