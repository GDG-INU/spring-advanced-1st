package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublisherDTO {
    private Long id;
    private String name;

    public PublisherDTO() {}

    public PublisherDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
