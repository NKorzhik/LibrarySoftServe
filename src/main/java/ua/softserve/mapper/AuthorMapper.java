package ua.softserve.mapper;

import ua.softserve.dto.AuthorDto;
import ua.softserve.model.Author;

import java.util.Collections;
import java.util.Optional;

public class AuthorMapper {

    public static Author mapToModel(AuthorDto authorDto) {
        return Author.builder()
                .name(authorDto.getName())
                .surname(authorDto.getSurname())
                .books(Collections.emptyList())
                .build();
    }

    public static AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}
