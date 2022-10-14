package ua.softserve.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ua.softserve.model.enums.Genre;

import java.util.List;

@Entity
@Data
@Table(name = "book")
@RequiredArgsConstructor
public class Book extends BaseEntity<Long> {

    public String title;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    public List<Genre> genreList;
    public String description;
    public String ISBN;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ToString.Exclude
    private List<Author> authorList;

    @OneToMany(mappedBy = "bookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<HistoryOfRequest> requests;

    @OneToMany(mappedBy = "bookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<Quantity> quantities;

}
