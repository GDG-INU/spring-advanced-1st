# 4주차 스터디

## 1. **Java: 에러와 예외, Checked Exception과 Unchecked Exception**

Java에서는 예외(Exception)와 에러(Error)를 구분한다.

### (1) 에러(Error)

- **정의**: 주로 시스템 수준의 문제로, 프로그램에서 복구할 수 없는 심각한 상황을 뜻합니다.

### (2) **예외(Exception)**

- **정의**: 실행 중에 발생하는 문제로, 복구 가능성이 있으며, 코드에서 처리할 수 있습니다. 이는 개발자가 미리 예측하여 방지할 수 있기 때문에 상황에 맞는 예외 처리(Exception Handle)를 해야한다.

-Checked Exception과 Unchecked Exception
- **Checked Exception (검사 예외)**:
    - RuntimeException을 상속하지 않는 클래스
    - 컴파일 시점에 확인됩니다.
    - 반드시 `try-catch` 블록으로 처리하거나 `throws`로 명시적으로 던져야 합니다.
    - **예**: IOException, SQLException
- **Unchecked Exception (비검사 예외)**:
    - RuntimeException을 상속하는 클래스
    - 실행 시점에 발생합니다.
    - 처리하지 않아도 컴파일 에러가 발생하지 않으며, 런타임에서 처리됩니다.
    - **예**: NullPointerException, IllegalArgumentException

### 

1. **예외 복구 (Recovery)**:
    - 예외가 발생했을 때 해당 문제를 해결하고 정상적으로 프로그램이 실행되도록 하는 방법

    ```java
    try {
        // 예외가 발생할 가능성이 있는 코드
    } catch (IOException e) {
        // 복구 코드
        System.out.println("파일을 다시 확인해주세요!");
    }
    
    ```

2. **예외 처리 회피**:
    - 예외를 직접 처리하지 않고 호출한 메서드로 위임해 예외 처리를 회피하는 방법. 그러나, 예외 처리의 필요성이 있다면 어느 정도는 처리한 후 위임하는 것이 좋다.

    ```java
    public void readFile() throws IOException {
        // 예외를 상위 호출자로 던짐
        Files.readAllLines(Paths.get("nonexistent_file.txt"));
    }
    
    ```

3. **예외 전환**:
    - 예외 회피와 비슷하게 메서드 밖으로 예외를 위임하지만, 그냥 위임하는 것이 아니라 적절한 예외로 전환해서 넘기는 방법. 이 때, 예외 처리를 단순하게 만들기 위해 포장(wrap)할 수도 있다.

    ```java
    try {
        Files.readAllLines(Paths.get("file.txt"));
    } catch (IOException e) {
        throw new CustomException("파일 처리 중 문제가 발생했습니다.", e);
    }
    
    ```



    ## 2. **스프링 MVC에서의 예외 처리 흐름**

스프링 MVC에서 예외는 다음과 같은 흐름으로 처리됩니다.

1. **컨트롤러 레벨에서 처리**:
    - 컨트롤러에서 발생한 예외는 스프링이 먼저 `@ExceptionHandler` 또는 `@ControllerAdvice`를 통해 처리할 기회를 제공합니다.

   ### 1-1. @ExceptionHandler를 이용한 예외 처리

   @ExceptionHandler 같은 경우 @Controller, @RestController가 적용된 Bean 내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 한다.

    ```java
    @RestController
    public class MyRestController {
    	...
        ...
        
        @ExceptionHandler(NullPointException.class)
        // 캐치하고 싶은 예외 클래스를 등록해주면 된다.
        public Object nullex(Exception e) {
        System.out.println(e.getClass());
        return "myService";
        }
     }
    ```

    - **특징**
        - 컨트롤러 클래스 내에서만 적용됩니다.
        - 처리할 예외 유형을 메서드에 명시합니다.
        - @ExceptionHandler({Exception1.class, Exception2.class}) 이런 식으로 두 개 이상도 등록이 가능하다

   ### 1-2. @ControllerAdvice를 이용한 예외 처리

   @ExceptionHandler가 하나의 클래스에 대한 것이라면, @ControllerAdvice 는 모든 @Controller 즉, 전역에서 발생하는 예외를 잡아 처리해주는 어노테이션이다.

    ```java
    @RestControllerAdvice
    public class Myadvice {
    //새로운 클래스 파일을 만들어서 어노테이션을 붙이면 된다.
    	@ExceptionHandler(CustomException.class)
        public String custom() {
        	return "hello custom";
            }
        }    
    ```

    - 그 다음엔 @ExceptionHandler로 처리하고 싶은 예외를 잡아 처리한다.
    - 별도의 속성값이 없어 사용하면 모든 패키지 전역에 있는 컨트롤러를 담당하게 된다.

2. **HandlerExceptionResolver**:
- 위에서 처리되지 않은 예외는 `HandlerExceptionResolver`를 통해 처리됩니다.
- 기본적으로 제공되는 구현체는 다음과 같습니다:
    - **DefaultHandlerExceptionResolver**: 스프링 MVC 기본 예외 처리기.
    - **ResponseStatusExceptionResolver**: `@ResponseStatus`가 적용된 예외를 처리.
    - **ExceptionHandlerExceptionResolver**: `@ExceptionHandler` 메서드를 실행.

1. **디폴트 에러 페이지**:
    - 위 단계에서 처리되지 않은 예외는 서블릿 컨테이너로 전달되며, 컨테이너가 기본 에러 페이지를 렌더링합니다.

## 3. ResponseEntityExceptionHandler 상속을 통한 예외 처리

`ResponseEntityExceptionHandler`는 스프링 프레임워크에서 제공하는 **기본적인 예외 처리 클래스**로, HTTP 응답의 형태로 예외를 처리할 수 있도록 설계되었습니다. 이를 상속받아 예외 처리 로직을 커스터마이징하면, 보다 체계적이고 재사용 가능한 예외 처리 코드를 작성할 수 있습니다.

- **주요 역할**:
    - 스프링 MVC에서 발생하는 예외들을 적절한 HTTP 상태 코드와 함께 클라이언트에 응답합니다.
    - 예를 들어, 유효성 검사 실패, 요청 파라미터 오류 등과 같은 예외 상황을 처리하는 메서드를 제공합니다.
    -

`ResponseEntityExceptionHandler`는 다양한 예외를 처리하기 위해 메서드들을 제공합니다. 필요에 따라 이러한 메서드를 오버라이드(Override)하여 특정 예외에 대한 응답 내용을 커스터마이징할 수 있습니다.

예시 : handleMethodArgumentNotValid,

handleHttpRequestMethodNotSupported,

handleHttpMediaTypeNotSupported,

handleMissingServletRequestParameter

등등

### **⇒ ResponseEntityExceptionHandler를 상속받아 구현하는 방법**

1. **`@RestControllerAdvice` 사용**
    - `@RestControllerAdvice`를 사용하면 REST API에서 발생하는 예외를 처리하는 글로벌 예외 처리 클래스를 만들 수 있습니다.
    - **구조**:

        ```java
        @RestControllerAdvice
        public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
        
            @Override
            protected ResponseEntity<Object> handleMethodArgumentNotValid(
                    MethodArgumentNotValidException ex,
                    HttpHeaders headers,
                    HttpStatus status,
                    WebRequest request) {
                String errorMessage = "Validation error: " + ex.getBindingResult().getFieldError().getDefaultMessage();
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
        
            @Override
            protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
                    HttpRequestMethodNotSupportedException ex,
                    HttpHeaders headers,
                    HttpStatus status,
                    WebRequest request) {
                String errorMessage = "HTTP 메서드가 지원되지 않습니다.";
                return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
        
        ```

2. **ResponseEntity의 구성**
    - `ResponseEntity`는 HTTP 응답에 상태 코드와 메시지를 포함하는 객체입니다.
    - **구조**: `new ResponseEntity<>(body, status)`
        - `body`: 클라이언트에 전송할 데이터 (보통 JSON 형식으로 반환)
        - `status`: HTTP 상태 코드 (예: `HttpStatus.BAD_REQUEST`)

1. **ResponseEntityExceptionHandler의 장점**

   **재사용 가능성**:

    - 여러 컨트롤러에서 공통적으로 발생하는 예외를 처리하는 전역 예외 처리기를 정의할 수 있습니다.

   **확장성**:

    - 기본 제공 메서드를 오버라이드하거나, 새로운 메서드를 추가하여 프로젝트 요구사항에 맞게 커스터마이징 가능합니다.

   **일관성**:

    - 동일한 구조와 형식으로 HTTP 응답을 제공하여 클라이언트와의 통신을 표준화할 수 있습니다.

⇒ `ResponseEntityExceptionHandler`는 RESTful 웹 애플리케이션에서 예외 처리를 표준화하고 체계적으로 관리하기 위한 강력한 도구이다. 이를 활용하면 예외 발생 시 클라이언트에게 일관된 응답을 제공하고, 개발자 입장에서 공통 예외 처리 로직을 손쉽게 재사용할 수 있습니다.

## 6. **예외 처리 우선순위**

### **1) 특정 컨트롤러에서만 적용하는 예외 처리**

- 개별적으로 처리해야 하는 예외가 있다면 컨트롤러 내의 `@ExceptionHandler`를 활용합니다.
- **예시**: 특정 요청에서만 발생하는 사용자 정의 예외 처리.

### **2) 전역적으로 공통 예외 처리**

- 모든 컨트롤러에 동일한 로직으로 예외를 처리하려면 `@ControllerAdvice`와 `@ExceptionHandler`를 사용합니다.
- **예시**: 공통적으로 `IllegalArgumentException` 발생 시 `400 Bad Request`로 응답.

### **3) 표준 HTTP 예외 처리**

- 스프링 MVC에서 제공하는 표준 예외(`MethodArgumentNotValidException`, `HttpRequestMethodNotSupportedException`)를 처리하려면 `ResponseEntityExceptionHandler`를 상속받아 커스터마이징합니다.
- **예시**: 유효하지 않은 요청에 대해 표준화된 응답 형식을 제공.

### **4) 최후의 예외 처리**

- 모든 레벨에서 처리되지 않은 예외는 기본 서블릿 컨테이너 또는 `BasicErrorController`에서 처리됩니다.
- **예시**: 예외 로그 기록 및 기본 에러 페이지 반환.