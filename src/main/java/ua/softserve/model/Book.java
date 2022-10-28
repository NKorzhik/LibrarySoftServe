package ua.softserve.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ua.softserve.model.enums.Genre;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
//@Builder
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
    private Author coAuthor;

    @OneToMany(mappedBy = "bookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<HistoryOfRequest> requests;

    @OneToMany(mappedBy = "bookId", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<Quantity> quantities;

    @Builder
    public Book(Long id,
                String title,
                Genre genre,
                String description,
                String ISBN,
                Author author,
                Author coAuthor
                /*List<HistoryOfRequest> requests,
                List<Quantity> quantities*/) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.ISBN = ISBN;
        this.author = author;
        this.coAuthor = coAuthor;
        /*this.requests = requests;
        this.quantities = quantities;*/
    }
}
