# week2

### - Controller Layer, Service Layer
### - Service Layer와 비즈니스 로직 분리
### - DTO(Data Transfer Object)와 도메인 객체의 분리



### 1. Controller 레이어의 역할과 책임
**Controller는 사용자 요청을 받는 입구입니다.** 사용자가 웹이나 앱을 통해 요청을 보내면 가장 먼저 Controller가 그 요청을 받습니다.

- **역할**: 사용자가 보내는 HTTP 요청(GET, POST 등)을 받아서 적절한 Service에 전달합니다.
- **책임**: URL 경로를 정의하고, 어떤 요청에 어떤 작업을 할지 결정합니다. 또한 응답을 사용자에게 보내기 전에 필요한 데이터를 가공하거나 상태 코드를 설정합니다.
- **예시**: 예를 들어, `GET /books` 요청이 들어오면 Controller는 모든 책 목록을 조회하기 위해 Service에 요청하고, 조회 결과를 사용자에게 돌려줍니다.
```java
@RestController
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
```

Spring에서는 주로 `@RestController`와 `@RequestMapping`을 사용하여 Controller 클래스를 정의합니다.  
예를 들어, `BookController`는 다음과 같이 작성할 수 있습니다.

- **`@RestController`**
    - 이 클래스가 REST API를 제공하는 컨트롤러임을 표시합니다.
    - `@Controller`와 `@ResponseBody`를 합친 역할을 하며, 모든 메서드의 반환값을 JSON 형식으로 자동 변환해줍니다.

- **`@RequestMapping("/books")`**
    - 이 컨트롤러가 `/books` 경로로 들어오는 HTTP 요청을 처리하도록 매핑합니다.
    - 예를 들어, `/books` 경로로 GET 요청이 들어오면 이 컨트롤러의 메서드들이 처리하게 됩니다.


HTTP 메서드에 맞는 매핑 사용: GET, POST, PUT, DELETE 등의 메서드를 적절히 매핑하여 일관된 API를 제공합니다.

> ### HTTP 메서드에 맞는 매핑 사용
> RESTful API에서는 각 HTTP 메서드가 고유한 의미를 가지므로, 자원을 다루는 작업에 적합한 HTTP 메서드를 매핑하는 것이 중요하다.

> - **GET**: 데이터를 조회할 때 사용 (ex. `@GetMapping`)
> - **POST**: 새로운 자원을 생성할 때 사용 (ex. `@PostMapping`)
> - **PUT**: 기존 자원을 수정할 때 사용 (ex. `@PutMapping`)
> - **DELETE**: 자원을 삭제할 때 사용 (ex. `@DeleteMapping`)


### 2. Service 레이어의 역할과 책임

**Service는 실질적인 작업을 처리하는 곳**입니다. Controller가 요청을 전달하면, Service가 그 요청을 받아 필요한 작업을 수행합니다.

- **역할**: 비즈니스 로직을 처리합니다. 즉, "요청한 작업을 어떻게 수행할 것인가"에 대한 세부 내용을 담당합니다.
- **책임**: 데이터를 조회, 추가, 수정, 삭제 등의 작업을 수행하며, DB와의 직접적인 통신은 Repository 레이어에 위임합니다.

> ### Repository Layer란?
> Repository 레이어는 **데이터베이스와 직접적으로 소통하는 역할**을 담당하는 부분.
> 데이터를 저장하고, 불러오고, 수정하고, 삭제하는 작업을 도와주는 '중간 다리' 역할을 한다.
> ### Repository 레이어의 역할:
> 1. **Service 레이어는 데이터베이스에 직접 접근하지 않는다**: 대신 Repository 레이어를 통해 요청을 보낸다.
     > 예를 들어, 어떤 책을 찾고 싶다면 Service 레이어는 직접 DB에 가서 찾지 않고, Repository에게 "이 책 좀 찾아줘"라고 요청한다.
> 2. **책임 분담**: Service는 비즈니스 로직(앱이 실제로 해야 할 일)에 집중하고, Repository는 "이 데이터를 어디서 가져오고, 어떻게 저장할지"에 대한 책임을 진다.

예시: 모든 책 목록을 조회해야 할 때, Service는 실제 데이터베이스에서 책 정보를 가져오는 로직을 처리하고, 필요한 가공이나 계산을 수행한 후 Controller에 전달합니다.

```java
    @Service
    public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
            .map(BookDTO::fromEntity)
            .collect(Collectors.toList());
    }
}
```

### 3. Service 레이어 구현과 비즈니스 로직 분리

Service 레이어 구현과 비즈니스 로직 분리는 시스템을 잘 구조화하고 유지보수를 쉽게 하기 위한 중요한 개념입니다.

### **⇒ 비즈니스 로직이란?**

비즈니스 로직은 애플리케이션이 **해결해야 할 실제 문제**를 정의하고 처리하는 로직입니다.

- **도서 관리 시스템**에서 책이 대출 가능한지 확인하는 로직, 대출할 때 연체 여부를 체크하는 로직 등이 모두 비즈니스 로직입니다.
- **쇼핑몰**에서는 상품의 재고를 확인하거나, 주문 시 결제 및 배송 처리를 하는 과정도 비즈니스 로직입니다.

즉, 비즈니스 로직은 "어떻게 처리해야 하는가?"에 대한 구체적인 작업 내용을 담고 있습니다.

### **⇒ 왜 비즈니스 로직을 Service 레이어에서 분리할까?**

비즈니스 로직을 Service 레이어에서 분리하는 이유는 **유지보수와 재사용성** 때문입니다.

- **유지보수**: 비즈니스 로직을 한 곳에 모아두면, 로직이 바뀌거나 새로운 요구사항이 생겨도 해당 로직만 수정하면 됩니다.
- **재사용성**: Service 레이어에 비즈니스 로직이 잘 정의되어 있으면, 여러 Controller나 다른 애플리케이션에서 같은 로직을 반복해서 사용할 수 있습니다.

- **비즈니스 로직 분리**: 책 대출 가능 여부를 확인하고, 대출 기록을 만드는 등 구체적인 로직들은 별도의 메서드나 클래스(예: `LoanService`, `LoanValidator`)에 분리합니다.

```java
@Service
public class LoanService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public LoanService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public void loanBook(Long bookId, Long userId) {
        if (!isBookAvailable(bookId)) { // 비즈니스 로직 분리
            throw new RuntimeException("Book is not available.");
        }
        // 대출 기록 생성
        createLoanRecord(bookId, userId); // 비즈니스 로직 분리
    }

    private boolean isBookAvailable(Long bookId) {
        // 책의 대출 가능 여부 확인 로직
        return !loanRepository.existsByBookIdAndReturnedFalse(bookId);
    }

    private void createLoanRecord(Long bookId, Long userId) {
        // 대출 기록을 DB에 저장하는 로직
        Loan loan = new Loan(bookId, userId);
        loanRepository.save(loan);
    }
}
```


### 4. DTO(Data Transfer Object)와 도메인 객체의 분리

DTO (Data Transfer Object)와 **도메인 객체**의 분리는 데이터를 주고받기 위한 객체와 실제 비즈니스 로직을 처리하는 객체를 나누는 것을 의미합니다.

**DTO를 쓰는 이유?**

1. **DTO**: 주로 **컨트롤러와 서비스 간** 또는 **API 통신**에서 **데이터 전달**을 위해 사용하는 객체입니다. 필요한 최소한의 정보만 포함하며, 비즈니스 로직은 포함하지 않습니다.
    - 예: `BookDTO`는 책의 제목, 저자 등의 정보만 담아 전달하는 역할을 합니다.

2. **도메인 객체**: 실제 **비즈니스 로직**과 데이터 처리를 담당하는 객체입니다. 데이터베이스의 테이블과 연결되며, 상태와 행동(로직)을 포함합니다.
    - 예: `Book` 엔티티는 책의 정보뿐만 아니라, 책 대여 가능 여부를 확인하는 등의 로직도 포함할 수 있습니다.

   DTO는 **데이터 전달용 객체**, 도메인 객체는 **로직 수행용 객체**이다.


