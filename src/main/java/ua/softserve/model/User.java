package ua.softserve.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ua.softserve.model.enums.Role;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "user_account")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<Long>{

    private String name;

    private String surname;

    private String email;

    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private LocalDate birthday;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "userId", cascade = {CascadeType.MERGE})
    @ToString.Exclude
    private List<HistoryOfRequest> listOfRequests;

    @Builder
    public User(Long id,
                String name,
                String surname,
                String email,
                String password,
                Role role,
                LocalDate birthday,
                LocalDate registrationDate) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
    }
}
