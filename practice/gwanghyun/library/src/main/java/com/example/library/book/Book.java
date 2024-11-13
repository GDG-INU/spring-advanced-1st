package com.example.library.book;
import com.example.library.service.Member;
import jakarta.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String title;
    private final String author;
    private boolean available;  // 도서 대여 가능 여부

    @ManyToMany(mappedBy = "rentedBooks")
    private final Set<Member> members;

    // 기본 생성자 - JPA에서 필요
    protected Book() {
        this.members = new HashSet<>();
        this.title = null;
        this.author = null;
        this.available = true;
    }

    // 불변 객체를 위한 생성자
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;  // 기본적으로 대여 가능하도록 설정
        this.members = new HashSet<>();
    }

    // Getter 메서드만 제공
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public Set<Member> getMembers() {
        return Collections.unmodifiableSet(members); // 외부에서 직접 수정할 수 없도록 불변 Set으로 반환
    }

    // 책의 대여 가능 여부를 변경하는 메서드
    public void changeAvailability(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}
