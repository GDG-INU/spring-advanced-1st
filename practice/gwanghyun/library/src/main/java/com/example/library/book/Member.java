package com.example.library.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long memberId;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rentList;

    // 기본 생성자
    protected Member() {}

    // 불변 객체를 위한 생성자
    public Member(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public List<Rent> getRentList() {
        return rentList;
    }
}
