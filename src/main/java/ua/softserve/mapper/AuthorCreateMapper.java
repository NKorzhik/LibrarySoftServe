package ua.softserve.mapper;

import ua.softserve.dto.AuthorCreateDto;
import ua.softserve.model.Author;

import java.util.Collections;

public class AuthorCreateMapper {

    public static Author mapToModel(AuthorCreateDto authorDto) {
        return Author.builder()
                .name(authorDto.getName())
                .surname(authorDto.getSurname())
                .books(Collections.emptyList())
                .build();
    }
}
