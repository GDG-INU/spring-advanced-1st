package inu.gdsc.library.loan;

import inu.gdsc.library.Member.Member;
import inu.gdsc.library.Member.MemberRepository;
import inu.gdsc.library.book.Book;
import inu.gdsc.library.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public void borrowBooks(Loan loan) {
        loan.borrowBooks();
        loanRepository.save(loan);
    }

    public void returnBooks(Loan loan) {
        loan.returnBooks();
        loanRepository.save(loan);
    }

    // 반납 기한 조회
    public LocalDate returnDue(String memberName) {
        Loan findloan = loanRepository.findByMemberName(memberName);
        return findloan.getReturnDue();
    }
}
