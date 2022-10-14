package ua.softserve.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ua.softserve.model.enums.Type;

@Entity
@Data
@Table(name = "quantity")
@RequiredArgsConstructor
public class Quantity extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book bookId;

    @Enumerated(EnumType.STRING)
    public Type type;
}
