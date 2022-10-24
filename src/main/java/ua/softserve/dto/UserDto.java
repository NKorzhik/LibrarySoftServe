package ua.softserve.dto;


import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private Role role;
    private LocalDate registrationDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return email.equals(userDto.email) && password.equals(userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
