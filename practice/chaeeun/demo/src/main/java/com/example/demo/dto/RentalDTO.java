package com.example.demo.dto;

import com.example.demo.domain.Rental;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "대여 날짜는 비워둘 수 없습니다.")
    private LocalDate rentalDate;
    private LocalDate returnDate;
    @NotNull(message= "회원 ID는 비워둘 수 없습니다.")
    private Long memberId;
    @NotNull(message = "책 ID는 비워둘 수 없습니다.")
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
