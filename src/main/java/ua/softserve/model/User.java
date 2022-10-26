package ua.softserve.model;


import jakarta.persistence.*;
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
@Builder
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

}
