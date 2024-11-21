package com.example.demo.dto;

import com.example.demo.domain.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
    private Long id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private Long memberId;
    private Long bookId;
    // 현재 rental 중인 책을 반환하는 메서드를 위해 추가
    private String bookTitle;

    public static RentalDTO from(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getRentalDate(),
                rental.getReturnDate(),
                rental.getMember().getId(),
                rental.getBook().getId(),
                rental.getBook().getTitle()
        );
    }
}
