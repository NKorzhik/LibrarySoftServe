package ua.softserve.model;

import jakarta.persistence.*;
import lombok.*;
import ua.softserve.model.enums.Status;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "history_of_request")
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class HistoryOfRequest extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book bookId;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;
    @Column(name = "should_be_return")
    private LocalDate shouldBeReturn;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "request_processing")
    private LocalDate requestProcessingDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public HistoryOfRequest(Long id,
                            User userId,
                            Book bookId,
                            LocalDate dateOfIssue,
                            LocalDate shouldBeReturn,
                            LocalDate returnDate,
                            LocalDate requestProcessingDate,
                            Status status) {
        super(id);
        this.userId = userId;
        this.bookId = bookId;
        this.dateOfIssue = dateOfIssue;
        this.shouldBeReturn = shouldBeReturn;
        this.returnDate = returnDate;
        this.requestProcessingDate = requestProcessingDate;
        this.status = status;
    }
}