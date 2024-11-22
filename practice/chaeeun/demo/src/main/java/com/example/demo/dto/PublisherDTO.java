package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublisherDTO {
    private Long id;
    private String name;

    public PublisherDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
