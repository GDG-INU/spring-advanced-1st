package com.example.demo.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PublisherDTO {
    private Long id;
    @NotBlank(message = "출판사 이름은 비워둘 수 없습니다.")
    private String name;

    public PublisherDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
