package ua.softserve.dto;

import jakarta.persistence.*;
import lombok.*;
import ua.softserve.model.Book;
import ua.softserve.model.User;
import ua.softserve.model.enums.Status;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateDto {

    private UserCreateDto userDto;
    private BookReadUpdateDto bookDto;
    private LocalDate dateOfIssue;
    private LocalDate shouldBeReturn;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestCreateDto requestDto = (RequestCreateDto) o;
        return userDto.equals(requestDto.userDto)
                && bookDto.equals(requestDto.bookDto)
                && dateOfIssue.equals(requestDto.dateOfIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDto, bookDto, dateOfIssue);
    }
}
