package inu.gdsc.library.loan;

import inu.gdsc.library.Member.Member;
import inu.gdsc.library.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Loan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "loan_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loan")
    private List<Book> loanBooks = new ArrayList<Book>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private LocalDate borrowDate;

    private LocalDate returnDue;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    //==연관관계 메서드==//
    public void borrowBooks() {
        for (Book book : loanBooks){
            book.removeQuantity(1);
        }
        this.borrowDate = LocalDate.now();
        this.returnDue = borrowDate.plusDays(14);
        this.status = LoanStatus.ACTIVE;
    }

    public void returnBooks() {
        for (Book book : loanBooks) {
            book.addQuantity(1);
        }
        this.returnDate = LocalDate.now();
        this.status = LoanStatus.COMPLETED;
        if (returnDate.isAfter(returnDue)) {
            // 사용자에게 제재를 가하는 로직
        }
    }
}
