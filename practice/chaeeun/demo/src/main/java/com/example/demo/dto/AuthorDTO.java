package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    @NotBlank(message = "저자 이름은 비워둘 수 없습니다.")
    @Size(min = 1, max = 50, message = "저자 이름의 길이는 1부터 50 사이여야 합니다.")
    private String name;

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
