package com.example.demo.service;


import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Rental;
import com.example.demo.dto.RentalDTO;
import com.example.demo.exception.AlreadyRentedException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    // 책 대여
    @Transactional
    public RentalDTO registerRental(Long memberId, Long bookId){
        log.debug("책 대여 처리를 시작합니다. memberId={}, bookId={}", memberId, bookId);
        Member member = memberRepository.findById(memberId)
           .orElseThrow(() -> {
               log.error("존재하지 않는 회원으로 대여에 실패했습니다. memberId={}", memberId);
               return new NotFoundException("회원이 존재하지 않습니다.");
           });

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> {
                log.error("존재하지 않는 회원으로 대여에 실패했습니다. bookId={}", bookId);
                return new NotFoundException("책이 존재하지 않습니다.");
            });

        // 책이 이미 대여중인지 확인(returnDate가 null이라면 아직 반납되지 않은 상태)
        if(rentalRepository.findByBookAndReturnDateIsNull(book).isPresent()){
            log.warn("이미 대여중인 책은 대여가 불가합니다. bookId={}", bookId);
           throw new AlreadyRentedException("이 책은 이미 대여중이므로 대여할 수 없습니다.");
        }

        // 정적 팩토리 메서드를 사용해 객체 생성
        Rental rental = Rental.createRental(member, book);
        Rental savedRental = rentalRepository.save(rental);
        log.info("책 대여에 성공했습니다. rentalId={}, memberId={}, bookId={}", rental.getId(), memberId, bookId);

       return RentalDTO.from(savedRental);
    }

    // 책 반납
    @Transactional
    public RentalDTO returnBook(Long rentalId){
        log.debug("책 반납 처리를 시작합니다. rentalId={} ", rentalId);
        Rental rental = rentalRepository.findWithMemberAndBookById(rentalId)
                .orElseThrow(() -> {
                    log.error("존재하지 않는 대여 기록으로 반납에 실패했습니다. rentalId={}", rentalId);
                    return new NotFoundException("대여 기록이 존재하지 않습니다.");
                });

        rental.markAsReturned();
        Rental savedRental = rentalRepository.save(rental);
        log.info("책 반납에 성공했습니다. rentalId={}, returnData={}", rentalId, savedRental.getReturnDate());

        return RentalDTO.from(savedRental);

    }

    // 현재 대여중인 책 조회
    public List<RentalDTO> getAllRentals(Long memberId) {
        log.debug("회원 대여 목록 조회를 시작합니다. memberId={}", memberId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    log.error("존재하지 않는 회원입니다.memberId={}", memberId);
                return new IllegalArgumentException(memberId + " 는 존재하지 않습니다.");
                });

        return rentalRepository.findByMemberIdAndReturnDateIsNull(memberId).stream()
                .map(rental -> new RentalDTO(
                        rental.getId(),
                        rental.getRentalDate(),
                        rental.getReturnDate(),
                        rental.getMember().getId(),
                        rental.getBook().getId(),
                        rental.getBook().getTitle()
                ))
                .collect(Collectors.toList());
    }

}

