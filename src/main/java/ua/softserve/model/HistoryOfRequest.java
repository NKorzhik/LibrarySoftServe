package ua.softserve.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ua.softserve.model.enums.Status;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "history_of_request")
@RequiredArgsConstructor
public class HistoryOfRequest extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book bookId;

    public LocalDate dateOfIssue;
    public LocalDate shouldBeReturn;
    public LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    public Status status;
}