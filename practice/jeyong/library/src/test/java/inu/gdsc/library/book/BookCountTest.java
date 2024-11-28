package inu.gdsc.library.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookCountTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCount bookCount;

    @Test
    void bookCount() {

        // 데이터 초기화
        bookRepository.deleteAll();

        Book testBook1 = new Book();
        Book testBook2 = new Book();
        Book testBook3 = new Book();

        bookRepository.save(testBook1);
        bookRepository.save(testBook2);
        bookRepository.save(testBook3);

        // 저장된 책의 수 확인
        int count = bookCount.countAllBooks();
        Assertions.assertThat(count).isEqualTo(3);
    }
}