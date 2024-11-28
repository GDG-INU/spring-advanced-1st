package inu.gdsc.library.book;

import inu.gdsc.library.NotEnoughQuantityException;
import inu.gdsc.library.author.Author;
import inu.gdsc.library.publisher.Publisher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter @Setter
@Entity @NoArgsConstructor
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long id;

    @NotNull
    @Range(min = 2)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @NotNull
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @NotNull
    private Integer ISBN;

    @NotNull
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
