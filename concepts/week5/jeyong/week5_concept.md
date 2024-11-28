# 5주차 학습내용

1. 테스트 주도 개발(TDD) 소개
    - TDD 개념과 이점
    - Red-Green-Refactor 사이클
    - TDD 실습: 간단한 기능을 TDD로 구현해보기

---

# 테스트 주도 개발 (TDD)

## TDD의 개념

- Test Driven Development
- 반복 테스트를 이용한 소프트웨어 방법론으로 작은 단위의 테스트 케이스를 작성하고 이를 통과하는 코드를 추가하는 단계를 반복하여 구현한다.
- 짧은 개발 주기의 반복에 의존하는 개발 프로세스
- 애자일 방법론 중 하나인 eXtream Programming(XP)의 Test-First 개념에 기반을 둔 단순한 설계를 중요

<aside>
📄

### eXtream Programming

미래에 대한 예측을 최대한 하지 않고 지속적으로 프로토타입을 완성하는 애자일 기방법론 중 하나이다.

이 방법론은 추가 요구사항이 생기더라도 실시간 반영할 수 있다.

</aside>

### TDD의 개발 방식

- TDD가 일반적인 개발 방식의 가장 큰 차이점은 테스트 코드를 작성한 뒤에 실제코드를 작성하는 것
- 설계 단계에서 프로그래밍 목적을 반드시 미리 정의
- 무엇을 테스트 해야 할지 미리 정의해야 한다.
- 테스트 코드를 작성하는 도중 발생하는 예외 사항은 테스트 케이스에 추가하고 설계를 개선한다.
- 테스트가 통과된 코드만을 코드 개발 단계에서 실제 코드로 작성

![image.png](5%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2014c93df08ef2808f9d54ddf90e392aa8/image.png)

## TDD의 이점

- 반복적인 단계가 진행되면서 자연스럽게 코드의 버그가 줄어들고, 소스코드가 간결해진다.
- 테스트 케이스 작성으로 인해 자연스럽게 설계가 개선됨으로 재설계 시간이 절감

### 보다 튼튼한 객체 지향적인 코드 생산

- TDD는 코드의 재사용 보장을 명시하므로 TDD를 통한 소프트웨어 개발 시 기능 별 철저한 모듈화
- 종속성과 의존성이 낮은 모듈로 조합된 소프트웨어 개발 가능
- 모듈을 추가하거나 제거해도 소프트웨어 전체 구조에 영향을 미치지 않게 된다.

### 재설계 시간의 단축

- 테스트 코드를 작성하기 전 개발자가 무엇을 개발해야하는지 분명히 정의하고 개발을 시작
- 테스트 시나리오를 작성하며 다양한 예외사항에 대해 생각해볼 수 있다.
- 개발 진행 중 소프트웨어의 전반적인 설계가 변경되는 일을 방지

### 디버깅 시간의 단축

- 자동화된 유닛 테스팅을 통해 특정 버그를 손쉽게 찾아낼 수 있다.

### + ) 추가 구현의 용이함 , 테스트 문서 대체 가능

## Red-Green-Refactor 사이클

![스크린샷 2024-11-28 11.53.11.png](5%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2014c93df08ef2808f9d54ddf90e392aa8/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-11-28_11.53.11.png)

- 말그대로 한 단위(일반적으로 class)만을 테스트하는 것이다.

### TDD 개발 주기

- RED : 실패하는 테스트코드를 먼저 작성
- Green : 테스트 코드를 성공시키기 위한 실제 코드를 작성
- Blue : 중복 코드 제거, 일반화 등의 리팩토링을 수행

→ 실패하는 테스트 코드를 작성할 때까지 실제 코드를 작성하지 않는 것과, 실패하는 테스트를 통과할 정도의 최소 실제 코드를 작성해야하는 것이다.

→ 이를 통해, 실제 코드에 대해 기대되는 바를 명확하게 정의 함으로써 불필요한 설계를 피할 수 있고, 정확한 요구 사항에 집중할 수 있다.

## TDD 실습

개발 목표 : 현재 BookRepositroy에 책이 몇권있는지 확인하는 메소드

### 1. RED❤️ : 실패하는 테스트코드 작성

```java
package inu.gdsc.library.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class BookCountTest {

		@Autowired
    private final BookRepository bookRepository;

    @Test
    void bookCount() {
    
		    // 데이터 초기화
        bookRepository.deleteAll();

				// 테스트 데이터
        Book testBook1 = new Book();
        Book testBook2 = new Book();
        Book testBook3 = new Book();

        bookRepository.save(testBook1);
        bookRepository.save(testBook2);
        bookRepository.save(testBook3);

				// BookCount가 구현되어 있지 않아 에러 발생!
        int count = BookCount.all();
        Assertions.assertThat(count).isEqualTo(3);
    }
}

```

![스크린샷 2024-11-28 17.22.11.png](5%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2014c93df08ef2808f9d54ddf90e392aa8/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-11-28_17.22.11.png)

### 2. GREEN💚 : 테스트 코드를 성공시키기 위한 실제 코드 작성

```java
package inu.gdsc.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookCount {

    private final BookRepository bookRepository; // Non-static field

    public int all() {
        List<Book> books = bookRepository.findAll();
        return books.size();
    }
}
```

![스크린샷 2024-11-28 18.10.29.png](5%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2014c93df08ef2808f9d54ddf90e392aa8/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-11-28_18.10.29.png)

### 3. BLUE💙 : 중복 코드 제거, 일반화 등의 리팩토링을 수행

BookCount의 all의 이름이 맞지 않는 것 같아 all → countAllBooks()로 변경

```java
package inu.gdsc.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookCount {

    private final BookRepository bookRepository; // Non-static field

    public int countAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.size();
    }
}
```

```java
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
```