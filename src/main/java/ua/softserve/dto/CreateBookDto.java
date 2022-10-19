package ua.softserve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.softserve.model.enums.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {

    private String title;
    private Genre genre;
    private String description;
    private String ISBN;
    private String mainAuthorName;
    private String mainAuthorSurname;
    private String coAuthorName;
    private String coAuthorSurname;
    private int quantity;
}
