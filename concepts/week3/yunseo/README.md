## 3주차 스터디
## <Spring Data JPA와 Repository 레이어>
- DAO와 DTO 차이?
- 복습. Repository Layer란?
- JpaRepository 인터페이스의 기능
- JpaRepository vs CrudRepository
- 쿼리 메소드 (findBy-, countBy- 등)
- @Query 어노테이션을 이용한 JPQL 작성
- Specification과 QueryDSL을 이용한 동적 쿼리 작성



## 0. DAO와 DTO 차이?
### **1) DAO(Data Access Object)**

**DAO**는 실제로 **DB의 data**에 **접근하기 위한 객체**.

- 실제로 DB에 접근하여 data를 삽입, 삭제, 조회, 수정 등 CRUD 기능을 수행합니다.
- Service와 DB를 연결하는 고리 역할을 합니다.
- Repository package가 바로 DAO입니다.

### **2) DTO(Data Transfer Object)**

**DTO는 계층 간 데이터 교환을 하기 위해 사용하는 객체**로, **DTO는 로직을 가지지 않는 순수한 데이터 객체(Java Beans)**입니다.

- DTO는 즉, getter/setter 메서드만 가진 클래스를 의미합니다.
- DB에서 데이터를 얻어서 Service나 Controller 등으로 보낼 때 사용합니다.
- 즉 엔티티를 DTO 형태로 변환한 후 사용합니다.


## 1. 복습. **Repository Layer란?**

- **Repository Layer**는 애플리케이션의 데이터 액세스를 담당하는 부분입니다. 데이터베이스와 직접 통신하여 데이터를 조회, 저장, 수정, 삭제하는 역할을 합니다. 이 레이어는 데이터를 처리하는 비즈니스 로직과 데이터 저장소를 깔끔하게 분리해줍니다.
- Repository 레이어는 **데이터베이스와 직접적으로 소통하는 역할**을 담당하는 부분.
  **데이터를 저장하고, 불러오고, 수정하고, 삭제하는 작업을 도와주는 '중간 다리'** 역할을 합니다.

  ### Repository 레이어의 역할:

    1. **Service 레이어는 데이터베이스에 직접 접근하지 않는다**: 대신 Repository 레이어를 통해 요청을 보낸다.
       예를 들어, 어떤 책을 찾고 싶다면 Service 레이어는 직접 DB에 가서 찾지 않고, Repository에게 "이 책 좀 찾아줘"라고 요청한다.
    2. **책임 분담**: Service는 비즈니스 로직(앱이 실제로 해야 할 일)에 집중하고, Repository는 "이 데이터를 어디서 가져오고, 어떻게 저장할지"에 대한 책임을 진다.


## 2-0. JPA란?

- JPA는 "Java Persistence API"의 약자로, 자바 객체를 관계형 데이터 베이스에 영속적으로 저장하고 조회할 수 있는 ORM 기술에 대한 표준 명세를 의미합니다.
- JPA를 통해 개발자는 SQL쿼리를 작성하지 않고도 객체를 통해 데이터베이스를 조작할 수 있으며, 객체 지향적인 코드 작성과 유지 보수성이 향상됩니다.

  기본적으로 Entity클래스를 작성한 후 Repository 인터페이스를 생성해야 합니다.

  Springboot에서 기본적인 작업을 도와주는 JPARepository 인터페이스가 있다.


## 2. **JpaRepository 인터페이스의 기능**

- **JpaRepository**는 스프링 데이터 JPA에서 제공하는 인터페이스로, 데이터베이스 작업을 더 쉽고 빠르게 할 수 있도록 많은 기능을 내장하고 있습니다.
  `JpaRepository`는 `CrudRepository`와 `PagingAndSortingRepository`를 확장하여 더 많은 기능을 포함하고 있습니다.
- 제공 기능:
    - 기본적인 CRUD (Create, Read, Update, Delete) 작업
    - 페이징 및 정렬 기능
    - 커스텀 쿼리 작성 없이도 복잡한 조회 메서드 제공
      >커스텀 쿼리란?
      >커스텀 쿼리(Custom Query)는 기본적으로 JpaRepository가 제공하는 자동 생성 메소드 외에, 개발자가 원하는 특정 조건이나 복잡한 데이터베이스 작업을 직접 정의하여 사용하는 쿼리입니다.
      >스프링 데이터 JPA는 메소드 이름만으로도 많은 기본적인 데이터베이스 작업(예: findByName, findAll)을 자동으로 처리할 수 있지만, 때로는 더 복잡한 조건이나 특정 요구사항에 맞는 쿼리가 필요할 때가 있습니다. 이럴 때 @Query 어노테이션을 사용하여 개발자가 직접 작성한 SQL 또는 JPQL(Java Persistence Query Language) 쿼리를 사용할 수 있습니다.
      ```java
      public interface BookRepository extends JpaRepository<Book, Long> {

      // 커스텀 쿼리 작성 예제
      @Query("SELECT b FROM Book b WHERE b.publishedDate BETWEEN :startDate AND :endDate")
      List<Book> findBooksPublishedBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
      }
      ```


### 3. JpaRepository와 CrudRepository의 차이점

애초에 `JpaRepository`는 `CrudRepository`와 `PagingAndSortingRepository`를 확장하여 더 많은 기능을 포함하고 있다.

- **CrudRepository**는 기본적인 CRUD 기능만 제공하며, 단순한 데이터 처리용으로만 사용된다.
- **JpaRepository**는 CRUD 기능 외에도 페이징, 정렬 등 추가 기능을 지원한다. 또한, JPA와 더 깊게 통합되어 있기 때문에 더 많은 기능을 제공합니다. CrudRepository를 상속받는다.

그래서 대부분의 경우 **JpaRepository**를 사용하는 것이 더 편리하고 유용하다!!


### 4. **쿼리 메소드 (findBy-, countBy- 등)**

- 쿼리 메소드(Query Method)는 Spring Data JPA가 제공하는 기능으로, 메소드의 이름을 기반으로 자동으로 SQL 쿼리를 생성하여 데이터베이스와 상호작용하는 방법이다.
  즉, 복잡한 SQL 쿼리를 작성하지 않고도 메소드 이름만으로 원하는 데이터를 조회할 수 있도록 한다.
- Spring Data JPA는 메소드 이름을 해석해 필요한 SQL 쿼리를 자동으로 생성합니다. 메소드 이름에 규칙을 따라 특정한 접두어나 키워드를 붙이면 Spring이 이를 해석하여 적절한 쿼리를 실행해줍니다.
    - **findBy**: 데이터 조회
    - **countBy**: 특정 조건을 만족하는 데이터의 개수를 반환
    - **existsBy**: 특정 조건을 만족하는 데이터가 존재하는지 boolean 값을 반환한다.
    - **deleteBy**: 특정 조건에 맞는 데이터를 삭제한다.

예를 들어, User라는 엔티티가 있다고 가정하자. User 엔티티는 id, name, email 필드를 포함하고 있다.

 ```java
public interface UserRepository extends JpaRepository<User, Long> {
    // 메소드 이름만으로 쿼리를 생성
    List<User> findByName(String name);
    User findByEmail(String email);
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByNameContaining(String namePart);
}
```

- `findByName(String name)`: `name`이 특정 값인 모든 `User` 엔티티를 조회합니다.
- `findByEmail(String email)`: `email`이 특정 값인 단일 `User` 엔티티를 조회합니다.
- `findByNameAndEmail(String name, String email)`: `name`과 `email`이 모두 특정 조건을 만족하는 `User` 엔티티 목록을 조회합니다.
- `findByNameContaining(String namePart)`: `name` 필드에 특정 문자열이 포함된 모든 `User` 엔티티를 조회합니다.

⇒ 메서드 이름을 통해 조건이나 정렬을 지정할 수 있어 코드가 간결해진다!


### 5. **@Query 어노테이션을 이용한 JPQL 작성**

**`@Query` 어노테이션**은 Spring Data JPA에서 **직접 쿼리를 작성**할 수 있게 해주는 기능이다. 이를 통해 **JPQL (Java Persistence Query Language)** 또는 **SQL 쿼리**를 사용해 복잡한 데이터베이스 쿼리를 작성할 수 있다.

- **@Query** 어노테이션을 사용하면 복잡한 쿼리를 직접 작성할 수 있습니다.
- JPQL(Java Persistence Query Language)은 SQL과 유사하지만, 엔티티 객체를 대상으로 쿼리합니다.


```java
public interface UserRepository extends JpaRepository<User, Long> {
    
    // 사용자 이름으로 검색하는 JPQL
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findByName(@Param("name") String name);

    // 이메일에 특정 문자열이 포함된 사용자 검색
    @Query("SELECT u FROM User u WHERE u.email LIKE %:emailPart%")
    List<User> findByEmailContaining(@Param("emailPart") String emailPart);
}
```

### 6. **Specification과 QueryDSL을 이용한 동적 쿼리 작성**

`Specification`과 `QueryDSL`은 Spring Data JPA에서 **동적 쿼리**를 작성할 수 있게 해주는 강력한 도구이다. 이 두 가지 방법을 사용하면 코드에서 **조건에 따라 유연하게 쿼리를 구성**할 수 있어 더욱 효율적이고 복잡한 쿼리 처리가 가능해진다.

- **Specification**은 JPA의 **Criteria API**를 사용하여 동적 쿼리를 작성할 수 있도록 도와주는 기능입니다. 복잡한 조건을 메서드 체이닝으로 만들어 다양한 검색 조건을 다룰 수 있습니다.
- **QueryDSL**은 더 직관적이고 간편하게 동적 쿼리를 작성할 수 있게 해줍니다. 코드 가독성이 높아지고 SQL-like한 방식으로 쿼리를 작성할 수 있습니다.

### 1) Specification

- `Specification`은 **JPA Criteria API**를 사용하여 **동적 쿼리 작성**을 지원하는 기능입니다.
- `Specification`을 이용하면 쿼리 조건을 **메서드로 나누어 정의**하고, 조건을 조합하여 사용함으로써 동적으로 쿼리를 변경할 수 있습니다.

### **사용법**

- Spring Data JPA에서 `Specification` 인터페이스를 사용하려면 `JpaSpecificationExecutor`를 상속받아 사용합니다.

```java
public interface ProductRepository extends JpaRepository<Product, Long>, 
JpaSpecificationExecutor<Product> {
}
```

```java
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;

public class ProductSpecification {
    
    public static Specification<Product> hasPriceGreaterThan(Double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), minPrice);
    }

    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }
}
```

⇒위와 같이 각 조건을 `Specification`으로 정의한 후에, 원하는 조건을 조합해서 사용.

### **장점**

- 조건을 각각의 메서드로 나누어 **재사용성과 가독성**을 높일 수 있습니다.
- 여러 조건을 쉽게 **조합**할 수 있습니다.


### 2) QueryDSL

- **QueryDSL**은 **타입 안전한** 쿼리를 지원하는 Java 라이브러리로, **정적 메타 모델**을 사용하여 **동적 쿼리를 코드로 작성**할 수 있습니다.
- QueryDSL은 **SQL과 유사한 문법**을 Java 코드로 작성하게 해 주어 코드 가독성을 높이고, 컴파일 타임에 오류를 잡아주는 장점이 있습니다.

### **사용법**

1. **설치**: Spring Boot 프로젝트에서는 QueryDSL을 위한 의존성을 추가해야 합니다.
2. **Q 클래스 생성**: 각 엔티티에 대해 QueryDSL이 자동으로 `Q` 클래스를 생성한다.
   예를 들어, `Product` 엔티티가 있다면 `QProduct` 클래스가 생성됩니다.

```java
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

@Service
public class ProductService {
    private final JPAQueryFactory queryFactory;

    public ProductService(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Product> getProductsByCriteria(Double minPrice, String category) {
        QProduct product = QProduct.product;
        
        return queryFactory.selectFrom(product)
            .where(
                product.price.gt(minPrice),
                product.category.eq(category)
            )
            .fetch();
    }
}
```

- **QProduct.product.price.gt(minPrice)**: `price`가 `minPrice`보다 큰 상품을 검색하는 조건입니다.
- **product.category.eq(category)**: `category`가 일치하는 상품을 검색하는 조건입니다.
- **fetch()**: 쿼리 결과를 리스트로 반환합니다.

### **장점**

- **타입 안정성**: SQL이나 JPQL에 비해 컴파일 타임에 오류를 잡아줘서 코드 안정성을 높입니다.
- **가독성**: SQL과 유사한 문법을 사용해 코드 가독성이 좋고 유지보수가 용이합니다.