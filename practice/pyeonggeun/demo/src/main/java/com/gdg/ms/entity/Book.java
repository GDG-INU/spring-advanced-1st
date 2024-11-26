package com.gdg.ms.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_pk;
    private String name;
    private String content;
    private String publisher;
    private String image_link;
    private String is_check_out = "N";

    // 현재 시간
    @CreationTimestamp
    private LocalDateTime created_at;

}
