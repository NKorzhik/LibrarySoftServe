package ua.softserve.model;

import jakarta.persistence.*;
import lombok.*;
import ua.softserve.model.enums.Genre;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity<Long> {

    @Column(name = "title")
    public String title;

    //@ElementCollection
    @Enumerated(value = EnumType.STRING)
    public Genre genre;

    @Column(name = "description")
    public String description;

    @Column(name = "isbn")
    public String ISBN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_author_id")
    private Author coAuthors;

    @OneToMany(mappedBy = "bookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<HistoryOfRequest> requests;

    @OneToMany(mappedBy = "bookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<Quantity> quantities;

}
