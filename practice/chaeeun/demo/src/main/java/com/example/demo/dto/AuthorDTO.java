package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String name;

    public AuthorDTO() {}

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
