package ua.softserve.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "author")
@RequiredArgsConstructor
public class Author extends BaseEntity<Long> {


    private String name;

    private String surname;

    @ManyToMany(mappedBy = "authorList")
    List<Book> books;
}
