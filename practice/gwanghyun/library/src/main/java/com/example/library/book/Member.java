package com.example.library.book;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    private String contact;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rentList;

    // 기본 생성자 (JPA는 기본 생성자가 필요)
    protected Member() {}

    // Member 엔티티를 불변 객체로 만들기 위해 생성자 사용
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
