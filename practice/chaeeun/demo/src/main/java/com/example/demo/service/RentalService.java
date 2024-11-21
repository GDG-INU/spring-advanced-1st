package com.example.demo.service;


import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Rental;
import com.example.demo.dto.RentalDTO;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        Member member = memberRepository.findById(memberId)
           .orElseThrow(() -> new IllegalArgumentException(memberId + " 는 존재하지 않는 회원입니다."));

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException(bookId + " 는 존재하지 않는 책입니다."));

        // 책이 이미 대여중인지 확인(returnDate가 null이라면 아직 반납되지 않은 상태)
        // if(rentalRepository.findByBookAndReturnDateIsNull(book).isPresent()){
        // throw new IllegalStateException("이 책은 이미 대여중이므로 대여할 수 없습니다.");
        // }

        // Lombok의 @Builder를 사용해서 Rental 객체 생성
        Rental rental = Rental.builder()
                .member(member)
                .book(book)
                .build();

        Rental savedRental = rentalRepository.save(rental);

       return RentalDTO.from(savedRental);
    }

    // 책 반납
    @Transactional
    public RentalDTO returnBook(Long rentalId){
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException(rentalId + " 는 존재하지 않습니다."));

        rental.markAsReturned();

        Rental savedRental = rentalRepository.save(rental);

        return RentalDTO.from(savedRental);

    }

    // 현재 대여중인 책 조회
    public List<RentalDTO> getAllRentals(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(memberId + " 는 존재하지 않습니다."));

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

