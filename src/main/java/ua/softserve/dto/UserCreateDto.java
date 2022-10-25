package ua.softserve.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ua.softserve.model.enums.Role;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

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
        UserCreateDto userDto = (UserCreateDto) o;
        return email.equals(userDto.email) && password.equals(userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
