package ua.softserve.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "user_account")
@RequiredArgsConstructor
public class User extends BaseEntity<Long>{

    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate birthday;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "userId", cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    @ToString.Exclude
    private List<HistoryOfRequest> requestList;

}
