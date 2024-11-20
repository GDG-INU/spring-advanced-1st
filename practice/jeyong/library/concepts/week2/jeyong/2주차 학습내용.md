# 2주차 학습내용

<aside>
📁

## 스프링 핵심 개념

1. IoC(Inversion of Control)와 DI(Dependency Injection) 심화
    - IoC 컨테이너의 작동 원리
    - BeanFactory와 ApplicationContext의 차이
    - 다양한 DI 방식: 생성자 주입, 세터 주입, 필드 주입
    - @Autowired, @Qualifier, @Primary 어노테이션 활용
    - 순환 참조 문제와 해결 방법
2. 스프링 빈과 의존관계
    - 빈 생명주기와 스코프
    - @Component, @Service, @Repository, @Configuration 어노테이션
    - XML 기반 vs **어노테이션 기반** 빈 설정
    - 빈 설정 메타데이터 작성 방법
    - @Bean vs @Component
3. 스프링 MVC 구조
    - MVC 패턴 개요
    - DispatcherServlet의 역할과 동작 원리
        - 프론트 컨트롤러 패턴
    - @Controller와 @RestController의 차이
    - 요청 처리 흐름 상세 설명
4. Controller와 Service 레이어
    - 각 레이어의 역할과 책임
    - Controller 작성 방법과 best practices
    - Service 레이어 구현과 비즈니스 로직 분리
    - DTO(Data Transfer Object)와 도메인 객체의 분리
5. Request 파라미터 처리
    - 스프링에서 객체의 직렬화와 역직렬화는 어떻게 이뤄질까요?
        - ObjectMapper의 작동방식
        - @RequestBody와 @ModelAttribute는 어떤 차이가 있을까요?
    - @RequestParam: URL 쿼리 파라미터와 폼 데이터 처리
    - @PathVariable: URL 경로 변수 처리
    - @ModelAttribute: 복잡한 객체의 바인딩
    - @RequestBody: JSON/XML 요청 본문 처리
    - @ResponseBody: 응답 본문 직접 작성
</aside>

# 1. IoC(Inversion of Control)와 DI(Dependency Injection) 심화

## IoC 컨테이너의 작동 원리

객체를 생성하고 관리하면서 의존관계를 연결해주는 컨테이너

### 제어의 역전 IoC(Inversion of Control)

- 각 기능은 필요한 인터페이스들을 호출하지만 어떤 구현 객체들이 실행될지는 모른다
- 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)라 한다.

**프레임워크**

- 내가 작성한 코드를 제어하고, 대신 실행하는
    
    경우
    

**라이브러리**

내가 작성한 코드가 제어의 흐름을 담당하는

경우

### 의존관계 주입 DI(Dependency Injection)

- 각 기능은 인터페이스에 의존한다. 실제로는 어떤 구현 객체가 사용될지는 모른다!
- 의존관계는 정적인 클래스 의존관계와 실행 시점에 결정되는 객체 의존관계 둘을 분리해서 생각해야 한다

### 동적인 객체 인스턴스 의존 관계

![Untitled](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/Untitled.png)

- 애플리케이션 실행 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것을 의존관계 주입이라 한다
- 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결한다
- 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다
- 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 할 수 있다.

## BeanFactory와 ApplicationContext의 차이

![Untitled](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/Untitled%201.png)

- BeanFactory
    
    스프링 컨테이너의 최상위 인터페이스
    
    스프링 빈을 관리하고 조회하는 역할을 담당
    
- AppliacationContext
    
    BeanFactory 기능을 모두 상속받아서 제공합니다.
    
    빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주지만 이외에 부가기능을 제공
    
    - MesageSource, EnvironmentCapabe, ApplicationEventPublisher, ResourceLoader

→ ApplicationContext는 BeanFactory의 기능을 상속받고, 빈 관리기능과 더불어 편리한 부가기능을 제공

BeanFactory를 직접 사용하는 일은 거의 없으며, 부가기능이 포함된 ApplicationContext 사용

## 다양한 DI 방식: 생성자 주입, 세터 주입, 필드 주입

### 생성자 주입

- 생성자를 통해 의존관계를 주입 받는 방법
- 특징
    - 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다
    - 불변, 필수 의존관계에 사용된다

```java
@Autowired
public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy
 discountPolicy) {
         this.memberRepository = memberRepository;
         this.discountPolicy = discountPolicy;
     }
```

### 수정자 주입 (setter 주입)

- setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해 의존관게를 주입하는 방법
- 특징
    - 선택, 변경 가능성이 있는 의존관계에 사용
    - 자바 빈 프로퍼티 규약의 수정자 메서드 방식을 사용

```java
@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
    
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
```

### 필드 주입

- 필드에 바로 주입하는 방법
- 특징
    - 코드가 간결하지만 테스트하기 힘들다는 단점이 있다
    - DI 프레임워크가 없으면 아무것도 할 수 없다
    - 결론 → 사용하지않는 것이 좋다

```java
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;
}
```

## 생성자 주입을 선택하라❗

### 불변

- 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다.
- 수정자 주입을 사용하면, setter 메서드를 public으로 열어놔야 한다
- 누군가 실수로 변경할 수 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 방법이 아니다
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에는 호출되는 일이 없다. 따라서 불변하게 설계할  수 있다.

## @Autowired, @Qualifier, @Primary 어노테이션 활용

@Autowired는 타입 (Type)으로 조회하기 때문에 선택된 빈이 2개 이상일 때 문제가 발생한다.

이를 위한 해결 방법

### @Autowired 필드명 매칭

→ @Autowired는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.

필드명 매칭은 먼저 타입 매칭을 시도하고 그 결과에 여러 빈이 있을 때 추가로 동작하는 기능이다.

```java
// 2개의 빈이 존재
@Component
public class FixDiscountPolicy implements DiscountPolicy {}
 
@Component
public class RateDiscountPolicy implements DiscountPolicy {}

// 기존 코드
@Autowired
private DiscountPolicy discountPolicy

// 수정 코드 : 필드 명을 빈 이름으로 변경
@Autowired
private DiscountPolicy rateDiscountPolicy
```

### @Qualifier

@Qualifier는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는  것이지 빈 이름을 변경하는 것이 아니다.

```java
// 빈 등록시 @Qualifier를 붙여준다
@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {}

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {}
```

### @Primary

@Primary는 우선순위를 정하는 방법이다. @Autowire에 여러 빈이 매칭되면 @Primary가 우선권을 가진다.

```java
// rateDiscountPolicy가 우선권을 가지도록 설정
 @Component
 @Primary
 public class RateDiscountPolicy implements DiscountPolicy {}
 
 @Component
 public class FixDiscountPolicy implements DiscountPolicy {}
```

## 순환 참조 문제와 해결 방법

순환참조 문제란 A클래스가 B클래스의 Bean을 주입받고, B클래스가 A클래스의 Bean을 주입받는 상황과 같이 서로 순환되어 참조할 경우 발생하는 문제를 의미한다.

### 해결방법

- 설계시 발생하지 않도록 예방
- @Lazy 어노테이션을 통해 임의로 해결
- 생성자 주입을 사용한다

# 2. 스프링 빈과 의존관계

## 빈 생명주기와 스코프

### 스프링 빈의 라이프사이클

<aside>
👉🏻 객체 생성 → 의존관계 주입

</aside>

스프링 빈은 객체를 생성하고 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 준비가 완료가 되기 때문에 의존관계 주입이 완료된 시점을 아는 것이 중요하다. 

이를 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공한다. 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.

### 스프링 빈의 이벤트 라이프 사이클

<aside>
👉🏻 스프링 컨테이너 생성 → 스프링 빈 생성 → 의존관계 주입 → 초기화 콜백 → 소멸전 콜백 →스프링 종료

</aside>

- 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
- 소멸전 콜백 : 빈이 소멸되기 직전 호출

### 스프링의 3가지 빈 생명주기 콜백 방법

- 인터페이스(Initializing Bean, Disposable Bean)
- 설정 정보에 초기화 메서드, 종료 메서드 지정
- @PostConstruct, @PreDestroy 애노테이션 지원

### 인터페이스

- 스프링 전용 인터페이스이기 때문에 해당 코드가 스프링 전용 인터페이스에 의존한다
- 초기화, 소멸 메서드의 이름을 변경할 수 없다
- 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다

→ 요즘에는 거의 쓰지 않는 방법

### 빈 등록 초기화, 소멸 메소드 지정

- 메서드 이름을 자유롭게 줄 수 있다
- 스프링 빈이 스프링 코드에 의존하지 않는다
- 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용 시킬 수 있다.

### 종료 메서드 추론

- @Bean destoryMethod 속성에는 기본값이 (inferred)로 등록되어 있다.
- close, shutdown 이름의 메서드를 자동으로 호출해준다.
- 스프링 빈으로 등록하면 종료메서드는 따로 적어주지 않아도 잘 동작한다.
- 기능을 사용하기 싫다면 빈 공백으로 지정하면 된다

### 애노테이션 @PostConstruct, @PreDestroy

- 최신 스프링에서 권장하는 방법
- 애노테이션 하나만 붙이면 되므로 매우 편리하다
- JSR-250 자바 표준을 사용한다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다
- 컴포넌트 스캔과 잘 어울린다
- 외부 라이브러리에는 적용하지 못한다. 외부 라이브러리를 초기화, 종료하기 위해선 @Bean의 기능을 사용해야 한다.

### 빈 스코프

빈 스코프란 빈이 존재할 수 있는 범위를 뜻한다.

### 스프링이 지원하는 다양한 스코프

- 싱글톤 : 디폴트 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
- 프로토타입 : 스프링 컨테이너는 프로토타입 빈의 생성과 의존 관계 주입까지만 관여하고, 더는 관리하지 않는 매우 짧은 범위의 스코프. 종료 메서드가 호출되지 않음
- 웹 관련 스코프
    - request : 웹 요청이 들어오고 나갈 때까지 유지되는 스코프
    - session : 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
    - application : 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프

## @Component, @Service, @Repository, @Configuration 어노테이션

등록해야 할 스프링 빈이 많아지게 되면 많은 어려움이 발생하는데, 이를 위해 스프링 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 ‘컴포넌트 스캔’의 일종

- @Component : 컴포넌트 스캔에 사용
- @Controller : 스프링 MVC 컨트롤러에서 사용
- @Service : 스프링 비지니스 로직에서 사용
- @Repository : 스프링 데이터 접근 계층에서 사용
- @Configuration : 스프링 설정 정보에서 사용

## XML 기반 vs **어노테이션 기반** 빈 설정

### XML 기반 빈 설정

XML 파일을 사용하는 방법으로 <bean> 요소의 class 속성에 FQCN(Fully-Qualified Class Name)을 기술하면 빈이 정의된다. <constructor-arg>나 <property> 요소를 사용해 의존성을 주입한다.

```xml
<bean id="id" class="inu.gdsc.BeanTest"></bean>
```

<bean> 태그를 통해 빈을 설정한다.

- Id : 빈 이름(id) 설정
- class : 빈 타입 설정
- scope : 빈의 scope 설정 (singleton/prototype)
- primary : true를 지정하여 같은 타입의 빈이 여러개 일때 우선적으로 사용할 빈 설정
- lazy-init : true를 지정하여 빈을 사용할 때 객체가 생성되도록 설정
- init-method : 빈 객체가 생성될때 호출할 메소드 설정
- destroy-method : 빈 객체가 소멸될때 호출할 메소드 설정
- autowire : 자동주입 설정 (no, byName, byType, constructor)

### Annotation 기반 빈 설정

![Untitled](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/Untitled%202.png)

![Untitled](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/Untitled%203.png)

## 빈 설정 메타데이터 작성 방법

## 스프링 빈 설정 메타 정보 - BeanDefinition

- 추상화를 담당하는 BeanDefinition
- 역할과 구현을 개념적으로 나눈 것
    
    → 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 오직 BeanDefinition만 알면 된다
    
- BeanDefinition을 빈 설정 메타정보라 한다
    - @Bean, <bean> 당 각각 하나씩 메타 정보 생성
- 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다

![Untitled](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/Untitled%204.png)

![Untitled](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/Untitled%205.png)

### BeanDefinition 정보

- BeanClassName: 생성할 빈의 클래스 명
- factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
- factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
- Scope: 싱글톤(기본값)
- lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한 생성을 지연 처리 하는지 여부
- InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
- DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
- Constructor arguments, Properties: 의존관계 주입에서 사용한다.

## @Bean vs @Component

- 개발자가 컨트롤이 불가능한 외부 라이브러리들을 Bean으로 등록하고 싶을 때 @Bean
- 개발자가 직접 컨트롤이 가능한 Class의 경우 @Component

# 3. 스프링 MVC 구조

![스크린샷 2024-08-15 17.13.13.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-08-15_17.13.13.png)

## MVC 패턴 개요

## 개요

![스크린샷 2024-08-11 14.35.30.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-08-11_14.35.30.png)

- JSP가 너무 많은 일을 담당하는 문제
- 변경의 라이프 사이클
    - UI를 수정하는 사이클과 비지니스 로직을 수정하는 사이클이 다르기 때문에 하나의 코드로 관리하는 것은 좋지않다.
- 기능 특화
    - 화면을 렌더링하는 뷰 템플릿과 비지니스 로직을 담당하는 것을 따로 구분하여 효과적 운영

### Model View Controller

- 컨트롤러 : HTTP 요청을 받아 파라미터를 검증하고, 비지니스 로직을 실행. 뷰에 전달할 결과 데이터를 조회하여 모델에 담는 역할
- 모델 : 뷰에 출력할 데이터를 담아두는 역할
- 뷰 : 모델에 담겨있는 데이터를 사용하여 화면을 그리는 역할을 담당. (HTML 생성)

## DispatcherServlet의 역할과 동작 원리

### 프론트 컨트롤러 패턴

각 Controller에서 중복된 부분을 제거하여 공통 부분으로 떼어낸 것

![스크린샷 2024-08-13 20.29.17.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-08-13_20.29.17.png)

![스크린샷 2024-08-13 20.29.27.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-08-13_20.29.27.png)

### DispatcherServlet

✅ 스프링 MVC의 프론트 컨트롤러 : DispatcherServlet

스프링 MVC의 핵심이 되는 디스패처 서블릿

### DispatcherServlet 등록

- DispatcherServlet도 부모 클래스에서 HttpServlet을 상속 받아 사용하고, 서블릿으로 동작

> DispatcherServlet → FrameworkServlet → HttpServletBean → HttpServlet
> 
- 스프링 부트는 DispatcherServlet을 서블릿으로 자동 등록하며 모든 경로에 대해 매핑
    
    urlPatterns=”/”
    

→ 더 자세한 URL 매핑에 대한 우선순위가 높기 때문에 기존에 등록한 서블릿도 함께 동작

### 요청 흐름 🌊

1. 서블릿이 호출되면 HttpServlet이 제공하는 service()가 호출
2. 스프링 MVC는 DispathcerServlet의 부모인 FrameworkServlet에서 service()를 오버라이드 함
3. FrameworkServlet.sercie()를 시작으로 여러 메서드가 호출 되면서 DispatcherServlet.doDispatch() 실행

### DispatcherServlet.doDispatch()

```java
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	HttpServletRequest processedRequest = request;
	HandlerExecutionChain mappedHandler = null;
	ModelAndView mv = null;
	
	// 1. 핸들러 조회
	mappedHandler = getHandler(processedRequest);
	if (mappedHandler == null) {
		noHandlerFound(processedRequest, response);
		return;
	}
	
	// 2. 핸들러 어댑터 조회 - 핸들러를 처리할 수 있는 어댑터
	HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
	
	// 3. 핸들러 어댑터 실행 -> 4. 핸들러 어댑터를 통해 핸들러 실행 -> 5. ModelAndView 반환
	mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
	processDispatchResult(processedRequest, response, mappedHandler, mv,dispatchException);
}

private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, HandlerExecutionChain mappedHandler, ModelAndView mv, Exception exception) throws Exception {
	// 뷰 렌더링 호출
	render(mv, request, response);
}

protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	View view;
	String viewName = mv.getViewName();
	
	// 6. 뷰 리졸버를 통해서 뷰 찾기, 7. View 반환
	view = resolveViewName(viewName, mv.getModelInternal(), locale, request);
	
	// 8. 뷰 렌더링
	view.render(mv.getModelInternal(), request, response);
}
```

## @Controller와 @RestController의 차이

### Controller

![스크린샷 2024-10-30 15.33.25.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-10-30_15.33.25.png)

### RestController

![image.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/image.png)

- @Controller는 반환 값이 String이면 뷰 이름으로 인식된다 → 찾은 뷰를 렌더링
- @RestController는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력 한다.
    
    JSON 형태의 객체 데이터 반환
    
     ex) return “ok”의 실행 결과로 ok 메시지를 받을 수 있다
    

## 요청 처리 흐름 상세 설명

## 동작 순서

1. 핸들러 조회 : 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러를 조회
2. 핸들러 어댑터 조회 : 핸들러를 실행할 수 있는 핸들러 어댑터 조회
3. 핸들러 어댑터 실행 : 핸들러 어댑터 실행
4. 핸들러 실행 : 핸들러 어댑터가 실제 핸들러(컨트롤러)를 실행
5. ModelAndView 반환 : 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환 후 반환
6. viewResolver 호출 : 뷰 리졸버를 찾아 실행
    
    JSP의 경우는 InternalResourceViewResolver가 자동 등록되고, 사용
    
7. View 반환 : 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체 반환
8. 뷰 렌더링 : 뷰를 통해 뷰를 렌더링

### 뷰 리졸버 동작방식

1. 핸들러 어댑터 호출
    
    핸들러 어댑터를 통해 논리 뷰 이름 획득
    
2. ViewResolver 호출
    1. 논리 뷰 이름으로 viewResolver를 순서대로 호출
    2. BeanNameViewResolver로 논리 뷰 이름과 같은 스프링 빈으로 등록된 뷰를 찾는다
    3. 없을 경우 InternalResourceViewResolver가 호출된다
3. InternalResourceView
    
    InternalResourceView는 JSP처럼 포워드 forward()를 호출하여 처리할 수 있는 경우에 사용
    
4. view.render()

view.render가 호출되고 InternalResourceView는 forward()를 사용하여 JSP 실행

# 4. Controller와 Service 레이어

## 각 레이어의 역할과 책임

### Controller

- 사용자의 입력처리와 응답에만 집중
- Web Layer & User Interface Layer

### Service

- 실제 기능을 어떤식으로 제공하는지에 대해서만 집중
- Service Layer & Applicatiation

## Controller 작성 방법과 best practices

```java
@Controller
@RequestMapping(value="/board") // 컨트롤러 URL 매핑
public class HomeController {
	
	// /list URL에 대한 요청 처리
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home2(Model model) {
		return "/board/list";
	}
}
```

- @Controller 애노테이션 사용
- URL 매핑을 통한 요청처리

## Service 레이어 구현과 비즈니스 로직 분리

### Service 레이어의 역할

Service 레이어는 비즈니스 로직을 캡슐화, 프레젠테이션 레이어와 데이터 액세스 레이어 사이의 중간 계층 역할

- 비즈니스 로직 처리 및 규칙 적용
- 트랜잭션 관리
- 데이터 접근 계층과의 상호작용 조정
- 프레젠테이션 계층에 명확한 API 제공

### 비지니스 로직 분리

유지보수성 향상

- 비즈니스 로직과 데이터 접근 로직의 명확한 구분
- 코드의 재사용성 증가
- 변경사항 발생 시 영향 범위 최소화

테스트 용이성

- 비즈니스 로직에 대한 단위 테스트 수행 가능
- Repository 계층을 모킹하여 독립적인 테스트 가능

책임의 분리

- Controller는 요청/응답 처리에만 집중
- Repository는 순수한 데이터 접근에만 집중
- Service는 비즈니스 규칙과 로직에만 집중

## DTO(Data Transfer Object)와 도메인 객체의 분리

### DTO

**DTO**란 프로세스 간에 데이터를 전달하는 객체를 의미

데이터를 전송하기 위해 사용하는 객체, 비즈니스 로직 같은 복잡한 코드는 없고 순수하게 전달하고 싶은 데이터만 존재

![스크린샷 2024-10-30 09.55.08.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-10-30_09.55.08.png)

# 5. Request 파라미터 처리

## 스프링에서 객체의 직렬화와 역직렬화는 어떻게 이뤄질까요?

## ❓ ObjectMapper란?

- JSON 컨텐츠를 Java 객체로 deserialization 하거나 Java 객체를 JSON으로 serialization 할 때 사용하는 Jackson 라이브러리의 클래스이다.
- ObjectMapper는 생성 비용이 비싸기 때문에 bean/static으로 처리하는 것이 좋다.

### ObjectMapper의 작동방식

ObjectMapper는 리플렉션을 활용해서 객체로부터 Json 형태의 문자열을 만들어내는데, 이것을 직렬화(Serialize)라고 한다. 해당 부분은 @ResponseBody나 @RestController 또는 ResponseEntity 등을 사용하는 경우에 처리된다.

![스크린샷 2024-10-30 09.13.51.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-10-30_09.13.51.png)

### ObjectMapper를 이용한 역직렬화

ObjectMapper는 리플렉션을 활용해서 Json문자열로부터 객체를 만들어내는데, 이것을 역직렬화(Deserialize)라고 한다. 스프링에서 @RequestBody로 Json 문자열을 객체로 받아올 때 역직렬화가 된다.

![스크린샷 2024-10-30 09.17.15.png](2%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%B3%E1%86%B8%E1%84%82%E1%85%A2%E1%84%8B%E1%85%AD%E1%86%BC%2012e93df08ef280029ce6fc4c7eac4790/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-10-30_09.17.15.png)

### @RequestBody와 @ModelAttribute는 어떤 차이가 있을까요?

### @RequestBody

파라미터에 @RequestBody가 존재한다면, 클라이언트가 보낸 Json형식의 http 요청 본문 (body)가 그대로 존재된다. xml이나 Json 기반의 메시지 요청에 유용하다.

요청 본문을 message converter를 통해 자바 객체로 바꾸는 것이므로, getter와 setter가 없더라도 정상적으로 할당된다.

### @ModelAttribute

@ModelAttribute는 HTTP Body 내용과 HTTP 파라미터의 값들을 Getter, Setter를 통해 주입하기 위해 사용한다. RequestBody와는 달리 기존에 존재하는 Bean객체를 생성해 그곳에 값을 넣는 것이므로, getter와 setter가 있어야 한다.

클라이언트로부터 요청이 들어오면, 생성한 객체에 요청한 파라미터와 같은 이름을 가진 변수에 값을 넣는다. 이로서 파라미터가 여러 개일때 모든 파라미터에 RequestParam을 지정할 필요 없이 한 번에 할당할 수 있게 된다.

## @RequestParam: URL 쿼리 파라미터와 폼 데이터 처리

### 클라이언트에서 서버로 요청 데이터를 전달하는 방법 3가지

- GET - 쿼리 파라미터
    - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함하여 전달
- POST - HTML Form
    - content-type : application/x-www-form-urlencoded
    - 메시지 바디에 쿼리 파라미터 형식으로 전달
- HTTP message body에 데이터 직접 담아서 요청
    - HTTP API에서 주로 사용, JSON, XML, TEXT
    - 데이터 형식은 주로 JSON 사용
    - POST, PUT, PATCH

### 요청 파라미터(request parameter) 조회

HttpServletRequest의 request.getParameter()를 사용한 두가지 요청 파라미터 조회

- GET, 쿼리 파라미터 전송
- POST, HTML Form 전송

둘다 형식이 같으므로 구분없이 조회 가능

### HTTP 요청 파라미터 - @RequestParam

```html
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <title>Title</title>
</head>
<body>
 <form action="/test" method="post">
 username: <input type="text" name="username" />
 age: <input type="text" name="age" />
 <button type="submit">전송</button>
 </form>
</body>
</html>
```

```java
@ResponseBody
@RequestMapping("/url")
public String requestParam(
				@RequestParam("username") String userName,
				@RequestParma("age") int userAge) {
				
	// 클라이언트가 Form에 username = "kim" , age = 20으로 작성 후 전송 시
  System.out.println("username = " + username); // "kim" 출력
  System.out.println("age = " + age); // 20 출력			
	return "ok"
}
```

@RequestParam : 파라미터 이름으로 바인딩

@RequestParam의 name(value) 속성이 파라미터 이름으로 사용

- @RequestParam("name") String userName → request.getParameter(”username”)

→ HTTP 파라미터 이름이 변수이름과 같으면 생략가능하다

```java
@ResponseBody
@RequestMapping("/test")
public String requestParam(
		@RequestParam String username, 
		@RequestParam int age) {

 // 클라이언트가 Form에 username = "kim" , age = 20으로 작성 후 전송 시
 System.out.println("username = " + username); // "kim" 출력
 System.out.println("age = " + age); // 20 출력
 return "ok";
}
```

### 파라미터 필수 여부 - requestParamRequired

- @RequestParam.required : 파라미터 필수 여부
- 기본값은 파라미터 필수 (true)
- 파라미터가 전달되지 않을시 예외 발생!
- 파라미터에 값이 없는 경우 defaultValue를 사용하여 기본 값을 적용 가능

```java
@ResponseBody
@RequestMapping("test")
public String requestParamDefault(
 @RequestParam(required = true, defaultValue = "guest") String username,
 @RequestParam(required = false, defaultValue = "-1") int age) {
```

## @PathVariable: URL 경로 변수 처리

### @PathVariable

- 경로 변수를 표시하기 위해 메서드에 매개변수에 사용된다.
- 경로 변수는 중괄호 {id}로 둘러싸인 값을 나타낸다
- url 경로에서 변수 값을 추출하여 매개변수에 할당한다.
- 기본적으로 경로 변수는 반드시 값을 가져야 하며, 값이 없는 경우 404 오류가 발생한다.
- 주로 상세 조회, 수정, 삭제와 같은 작업에서 리소스 식별자로 사용된다.

```java
// 변수명이 같으면 생략 가능
// @PathVariable("userId") String userId -> @PathVariable String userId
@GetMapping("/mapping/{userId}")
public String mappingPath(@PathVariable("userId") String data) {
    log.info("mappingpath userId = {}", data);
    return "ok";
}
// 'http://localhost:8080/mapping/30' 으로 접속했을 때의 출력 결과
// mappingpath userId = 30

// PathVariable 다중 사용
@GetMapping("/mapping/users/{userId}/orders/{orderId}")
public String mappingPath(@PathVariable String userId, @PathVariable Long
        orderId) {
    log.info("mappingPath userId={}, orderId={}", userId, orderId);
    return "ok";
}
//'http://localhost:8080/mapping/users/30/orders/7' 으로 접속했을 때의 출력 결과
// mappingPath userId=30, orderId=7
    
@GetMapping("/{name}")
public String user(@PathVariable Map<String,String> map) {
	  System.out.println(map);
		return "index";
}
//'http://localhost:8080/user/30/홍길동' 으로 접속했을 때의 출력 결과
//{name=30, age=홍길동}
```

## @ModelAttribute: 복잡한 객체의 바인딩

요청 파라미터를 받아서 필요한 객체를 만드는 과정은 불편하니 스프링에서 @ModelAttribute를 이용해 이를 처리해준다.

### 바인딩 받을 객체

```java
import lombok.Data;

@Data
public class User {
	private String username;
	private int age;
}
```

롬복 @Data 

- @Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor 를
자동으로 적용해준다.

```java
@ResponseBody
@RequestMapping("/test")
public String modelAttribute(@ModelAttribute User user) {
	// 클라이언트가 Form에 username = "kim" , age = 20으로 작성 후 전송 시
  System.out.println("username = " + user.getUsername()); // "kim" 출력
  System.out.println("age = " + user.getAge()); // 20 출력
  return "ok";
}
```

User 객체가 생성되고, 요청 파라미터의 값도 User 객체에 들어가 있다.

### 스프링 MVC의 @ModelAttribute

- User 객체를 생성
- 요청 파라미터의 이름으로 User 객체의 프로퍼트를 찾고, 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩)

바인딩 오류

- 타입 오류 등 바인딩에 오류가 발생하면 BindException 발생

### 생략

```java
@ResponseBody
@RequestMapping("/test")
public String modelAttribute(User user) {
	// 클라이언트가 Form에 username = "kim" , age = 20으로 작성 후 전송 시
  System.out.println("username = " + user.getUsername()); // "kim" 출력
  System.out.println("age = " + user.getAge()); // 20 출력
  return "ok";
}
```

스프링의 생략시 규칙

- String, int, Integer 같은 단순 타입 → @ReqeustParam
- 나머지 = @ModelAttribute

## @RequestBody: JSON/XML 요청 본문 처리

@RequestBody가 붙으면 http요청의 본문(body)가 그대로 넘어온다.

HttpMessageConverter가 HTTP Request body를 읽고 이를 역직렬화(deserialization)하여 자바 객체로 변환해준다.

### HTTP 메시지 바디를 통해 데이터가 넘어오는 경우

1. HTTP 요청 수신: 클라이언트가 JSON/XML 형식의 데이터를 HTTP 요청 본문에 담아 전송
2. 메시지 변환: Spring은 내부적으로 HttpMessageConverter를 사용하여 요청 본문을 자바 객체로 변환
3. 객체 매핑: Jackson 라이브러리(ObjectMapper)가 JSON을 POJO(Plain Old Java Object)로 역직렬화

### 조회

RequestBody로 JSON 데이터 수신

```java
@ResponseBody
@PostMapping("/test")
public String requestBody(@RequestBody String body) {
		System.out.println(body);
		return "ok";
}
	
// {name : "Jeyong", age : 24} 데이터 전송
// 출력 결과 : {"name":"Jeyong","age":"24"} 
```

자동 객체 생성

```java
@ResponseBody
@PostMapping("/test")
public String requestBody(@RequestBody User user) {
	System.out.println("username = " + user.getUsername());
	System.out.println("age = " + user.getUserage());
}
// {name : "Jeyong", age : 24} 데이터 전송
// username = Jeyong
// age = 24
```

## @ResponseBody: 응답 본문 직접 작성

@ResponseBody 어노테이션을 메소드 레벨이나 메소드의 리턴 타입으로 붙이면, 메소드가 리턴하는 값을 HttpMessageConverter를 통해 Response body로 직렬화(serialization)한다.

```java
@RequestMapping("/test")
@ResponseBody
public User responseBody(User user) {
	// 존재하는 User 객체를 Response body로 변경하여 응답 메시지로 클라이언트에게 전송
	System.out.println("username = " + user.getUsername());
	System.out.println("age = " + user.getUserage());
	// username = Jeyong
	// age = 24
	return user
}
```

### @RestController

 @RestController라는 어노테이션을 추가해서 해당 Controller의 모든 메소드 리턴 타입을 @ResponseBody로 처리한다는 것을 명시 → @ResposeBody 생략