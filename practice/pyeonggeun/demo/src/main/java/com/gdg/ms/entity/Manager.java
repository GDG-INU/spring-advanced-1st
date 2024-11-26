package com.gdg.ms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="manager")
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manager_pk;
    private String id;
    private String password;
    private String name;
    private int age;
    private String resident_number;

}
