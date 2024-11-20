package inu.gdsc.library.book;

import inu.gdsc.library.NotEnoughQuantityException;
import inu.gdsc.library.author.Author;
import inu.gdsc.library.publisher.Publisher;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Entity @NoArgsConstructor
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private Integer ISBN;

    private Integer quantity;

    private boolean isAvailable;


    //==비지니스 로직==//
    public void addQuantity(int newQuantity) {
        this.quantity += newQuantity;
    }
    public void removeQuantity(int newQuantity) {
        int restQuantity = this.quantity - newQuantity;
        if (restQuantity < 0) {
            throw new NotEnoughQuantityException("수량이 부족합니다");
        }
        this.quantity = restQuantity;
    }
}
