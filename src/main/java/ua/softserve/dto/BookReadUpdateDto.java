package ua.softserve.dto;

import lombok.*;
import ua.softserve.model.enums.Genre;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookReadUpdateDto {

    private long id;
    private String title;
    private Genre genre;
    private String description;
    private String ISBN;
    private AuthorDto authorDto;
    private AuthorDto coAuthorDto;

    //private List<Quantity> quantities; //закоментировано т.к в методе getMoreInfoAboutBook мы используем метод
    //getCountOfQuantityByBookId

    //private int quantities;
    //private List<HistoryOfRequest> requests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReadUpdateDto bookDto = (BookReadUpdateDto) o;
        return title.equals(bookDto.title) && ISBN.equals(bookDto.ISBN);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, ISBN);
    }
}
