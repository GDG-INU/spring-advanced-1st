package com.example.demo.controller;

import com.example.demo.dto.RentalDTO;
import com.example.demo.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<RentalDTO> rentBook(@RequestBody RentalDTO rentalDTO) {
        RentalDTO createdRentalDTO = rentalService.registerRental(rentalDTO.getMemberId(), rentalDTO.getBookId());
        return ResponseEntity.ok(createdRentalDTO);
    }

    @PutMapping("/{rentalId}/return")
    public ResponseEntity<RentalDTO> returnBook(@PathVariable Long rentalId) {
        RentalDTO rentalDTO = rentalService.returnBook(rentalId);
        return ResponseEntity.ok(rentalDTO);
    }

    // 특정 회원이 rental중인 책 리스트 목록
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<RentalDTO>> getAllRentals(@PathVariable Long memberId) {
        List<RentalDTO> rentals = rentalService.getAllRentals(memberId);
        return ResponseEntity.ok(rentals);
    }

}
