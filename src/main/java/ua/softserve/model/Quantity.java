package ua.softserve.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ua.softserve.model.enums.Type;

@Entity
@Data
@Table(name = "quantity")
@AllArgsConstructor
@NoArgsConstructor
public class Quantity extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book bookId;

    @Enumerated(EnumType.STRING)
    public Type type;
}
