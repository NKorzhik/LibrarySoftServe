package ua.softserve.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ua.softserve.model.enums.Status;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "history_of_request")
@AllArgsConstructor
@NoArgsConstructor
public class HistoryOfRequest extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book bookId;

    @Column(name = "date_of_issue")
    public LocalDate dateOfIssue;
    @Column(name = "should_be_return")
    public LocalDate shouldBeReturn;
    @Column(name = "return_date")
    public LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    public Status status;
}