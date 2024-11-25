# 5주차 스터디

## JUnit 5 사용법

- JUnit 5 구조 (Platform, Jupiter, Vintage)
- 주요 어노테이션 (@Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll)
- Assertions 메서드 활용
- 파라미터화된 테스트 (@ParameterizedTest)
- 태깅과 필터링 (@Tag)
- 테스트 라이프사이클

## 0. JUnit 5 란?

JUnit 5는 자바 언어로 작성된 테스트 프레임워크로, 소프트웨어 개발에서 단위 테스트(unit testing)를 작성하고 실행하는 데 사용됩니다. JUnit 5는 JUnit 4를 기반으로 발전된 버전으로, 다양한 새로운 기능과 개선된 구조를 제공합니다. JUnit 5는 **JUnit Platform**, **JUnit Jupiter**, **JUnit Vintage**로 구성되어 있다.

## 1. **JUnit 5 구조**

JUnit 5는 **Platform, Jupiter, Vintage**의 세 가지 주요 컴포넌트로 구성된다. 이를 통해 다양한 테스트 스타일과 환경을 지원한다.

### **1) JUnit Platform**

- **역할**: 테스트 실행 환경.
- **특징**:
    - 다양한 테스트 프레임워크(JUnit, TestNG 등)를 통합하여 실행.
    - IDE(인텔리J, Eclipse 등)와 빌드 도구(Maven, Gradle)와 호환.
- **주요 기능**:
    - 테스트 실행 결과를 수집하고 출력.

### **2) JUnit Jupiter**

- **역할**: JUnit 5에서 사용되는 **새로운 테스트 API**.
- **특징**:
    - JUnit 5의 어노테이션과 기능 제공.
    - Java 8 이상의 람다와 스트림 활용 가능.
- **예**:
    - `@Test`, `@BeforeEach`, `@ParameterizedTest` 등 어노테이션.

### **3) JUnit Vintage**

- **역할**: **JUnit 4와 3**에 작성된 테스트를 JUnit 5 환경에서 실행.
- **특징**:
    - 기존 테스트 코드를 JUnit 5로 전환하지 않아도 사용 가능.

### ⇒ 정리

- **JUnit Platform**: 테스트 실행을 위한 플랫폼으로, JUnit 5의 테스트를 실행하는 기본 환경을 제공합니다. `JUnit Platform`은 다양한 테스트 엔진을 실행할 수 있게 해줍니다.
- **JUnit Jupiter**: JUnit 5에서 새롭게 도입된 테스트 엔진으로, 새로운 기능과 확장성을 제공합니다. JUnit 5의 핵심이자 새로운 API를 제공합니다.
- **JUnit Vintage**: 이전 버전인 JUnit 3, JUnit 4로 작성된 테스트 코드도 JUnit 5에서 실행할 수 있도록 호환성을 제공합니다.

### ⇒ JUnit의 필요성

- **향상된 유연성 및 확장성**:
  JUnit 5는 테스트를 실행하고 관리하는 데 있어서 훨씬 더 많은 유연성을 제공합니다. 예를 들어, **테스트 인프라**가 잘 정의되어 있어, 새로운 기능이나 외부 라이브러리를 쉽게 통합할 수 있습니다.
- **모듈화된 아키텍처**:
  JUnit 5는 이전의 JUnit 4와 달리 **모듈화된 구조**를 가지고 있어, 필요한 부분만을 선택적으로 사용할 수 있습니다. 예를 들어, `JUnit Jupiter`만 사용할 수도 있고, `JUnit Vintage`와 함께 사용할 수도 있습니다.
- **새로운 어노테이션과 기능**:
  JUnit 5는 새로운 어노테이션과 기능을 도입하여 테스트 작성 및 관리를 효율적으로 만들었습니다. 예를 들어, **`@BeforeEach`**, `@AfterEach`와 같은 새로운 어노테이션을 통해 테스트 전후의 작업을 정의하는 방식이 개선되었습니다. 또한, 파라미터화된 테스트(Parameterized Test)나 **태깅 기능(@Tag)** 등 다양한 기능을 제공하여 테스트의 관리와 실행을 효율적으로 할 수 있습니다.
- **강력한 테스트 확장 기능**:
  JUnit 5는 **확장성**을 중시하여, 다양한 기능을 추가하거나 외부 라이브러리와 통합할 때 더 용이합니다. `@ExtendWith`를 사용하여 사용자 정의 확장 기능을 추가할 수 있고, 이를 통해 테스트 환경을 더 세밀하게 조정할 수 있습니다.

## 2. **주요 어노테이션**

### **1) `@Test`**

- **용도**: 테스트 메서드를 정의.
- **특징**:
    - 메서드에 붙여 테스트 대상임을 표시.
    - 테스트 메서드는 반드시 `public`일 필요는 없음.
- **예시코드**:

    ```java
    
    @Test
    void testAddition() {
        assertEquals(4, 2 + 2);
    }
    
    ```


### **2) `@BeforeEach`**

- **용도**: 각 테스트 실행 전에 실행할 로직 정의.
- **예시**:

    ```java
    
    @BeforeEach
    void setUp() {
        System.out.println("Test 시작 전 설정");
    }
    ```


### **3) `@AfterEach`**

- **용도**: 각 테스트 실행 후 실행할 로직 정의.
- **예시**:

    ```java
    @AfterEach
    void tearDown() {
        System.out.println("Test 종료 후 정리");
    }
    
    ```


### **4) `@BeforeAll`**

- **용도**: **모든 테스트 실행 전 1회**만 실행.
- **특징**:
    - `static` 메서드로 정의.
- **예시**:

    ```java
    
    @BeforeAll
    static void initAll() {
        System.out.println("모든 테스트 전에 한 번 실행");
    }
    
    ```


### **5) `@AfterAll`**

- **용도**: **모든 테스트 실행 후 1회**만 실행.
- **특징**:
    - `static` 메서드로 정의.
- **예시**:

    ```java
    
    @AfterAll
    static void cleanUp() {
        System.out.println("모든 테스트 후에 한 번 실행");
    }
    
    ```


## 3. **Assertions 메서드 활용**

JUnit은 테스트 결과를 확인하기 위한 다양한 **Assertions 메서드**를 제공합니다.

### **1) 주요 Assertions 메서드**

- `assertEquals(expected, actual)`: 기대값과 실제값 비교.
- `assertTrue(condition)`: 조건이 참인지 확인.
- `assertFalse(condition)`: 조건이 거짓인지 확인.
- `assertNull(object)`: 객체가 `null`인지 확인.
- `assertThrows(Exception.class, executable)`: 예외 발생 여부 확인.

### **2) 예시**

```java

@Test
void testAssertions() {
    assertEquals(4, 2 + 2, "계산 결과가 일치하지 않습니다.");
    assertTrue(3 > 2, "조건이 참이어야 합니다.");
    assertThrows(ArithmeticException.class, () -> {
        int result = 1 / 0;
    });
}

```

## 4. **파라미터화된 테스트 (`@ParameterizedTest`)**

파라미터화된 테스트를 사용하면 **여러 입력값**으로 동일한 테스트를 반복 실행할 수 있습니다.

### **1) 주요 어노테이션**

- `@ParameterizedTest`: 파라미터화된 테스트를 선언.
- `@ValueSource`: 단순 값 목록 제공.
- `@CsvSource`: CSV 형태로 값 제공.
- `@MethodSource`: 메서드에서 파라미터 제공.

### **2) 예제**

```java

@ParameterizedTest
@ValueSource(strings = {"racecar", "radar", "level"})
void testPalindrome(String word) {
    assertTrue(isPalindrome(word));
}

@ParameterizedTest
@CsvSource({"1, 1, 2", "2, 3, 5", "3, 5, 8"})
void testAddition(int a, int b, int result) {
    assertEquals(result, a + b);
}

```

## 5. **태깅과 필터링 (`@Tag`)**

테스트에 태그를 붙여 특정 그룹의 테스트만 실행하거나 제외할 수 있습니다.

### **1) `@Tag` 사용**

- 각 테스트에 태그를 추가.
- **특정 태그만 실행**하거나 제외할 수 있음.
- Gradle/Maven에서 `D` 옵션으로 필터링 가능.

### **2) 예제**

```java

@Tag("fast")
@Test
void fastTest() {
    assertEquals(2, 1 + 1);
}

@Tag("slow")
@Test
void slowTest() {
    assertEquals(5, 2 + 3);
}

```

### **3) 실행 방법**

- `fast` 태그만 실행: `./gradlew test -DincludeTags=fast`
- `slow` 태그 제외: `./gradlew test -DexcludeTags=slow`

## 6. **테스트 라이프사이클**

JUnit 5 테스트는 **라이프사이클 메서드**를 통해 특정 시점에 실행할 코드를 정의합니다.

### **라이프사이클 단계**

1. **`@BeforeAll`**: 테스트 클래스 실행 전 1회 실행.
2. **`@BeforeEach`**: 각 테스트 메서드 실행 전 실행.
3. **`@Test`**: 테스트 메서드 실행.
4. **`@AfterEach`**: 각 테스트 메서드 실행 후 실행.
5. **`@AfterAll`**: 테스트 클래스 실행 후 1회 실행.

### **라이프사이클 흐름**

```java

@BeforeAll
static void initAll() {
    System.out.println("모든 테스트 전에 실행");
}

@BeforeEach
void init() {
    System.out.println("각 테스트 전에 실행");
}

@Test
void testOne() {
    System.out.println("테스트 1 실행");
}

@Test
void testTwo() {
    System.out.println("테스트 2 실행");
}

@AfterEach
void tearDown() {
    System.out.println("각 테스트 후에 실행");
}

@AfterAll
static void tearDownAll() {
    System.out.println("모든 테스트 후에 실행");
}

```

### **출력 결과**

```
코드 복사
모든 테스트 전에 실행
각 테스트 전에 실행
테스트 1 실행
각 테스트 후에 실행
각 테스트 전에 실행
테스트 2 실행
각 테스트 후에 실행
모든 테스트 후에 실행
//순서대로 작동되는 것을 알 수 있음.
```

1. **JUnit 5 구조**:
    - Platform, Jupiter, Vintage로 구성.
2. **주요 어노테이션**:
    - `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`.
3. **Assertions 메서드**:
    - `assertEquals`, `assertTrue`, `assertThrows` 등을 활용.
4. **파라미터화된 테스트**:
    - 입력값을 다양하게 테스트하는 `@ParameterizedTest`.
5. **태깅과 필터링**:
    - 테스트를 그룹화하고 실행 조건을 설정.
6. **테스트 라이프사이클**:
    - `@BeforeAll` → `@BeforeEach` → `@Test` → `@AfterEach` → `@AfterAll`.