package ua.softserve.model;


import jakarta.persistence.*;
import lombok.*;

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
    private LocalDate birthday;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "userId", cascade = {CascadeType.MERGE})
    @ToString.Exclude
    private List<HistoryOfRequest> listOfRequests;

}
