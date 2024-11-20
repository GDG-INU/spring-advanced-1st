package com.GDG.Spring_Study.entitiy;

import com.GDG.Spring_Study.global.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "tbl_rental_status")
public class RentalStatus extends BaseEntity {
    @Id
    @Comment(value="대여 상태 구분자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
