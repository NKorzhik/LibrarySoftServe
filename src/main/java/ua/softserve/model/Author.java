package ua.softserve.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToMany(mappedBy = "authorList")
    List<Book> books;
}
