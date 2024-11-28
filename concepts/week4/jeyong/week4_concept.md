# 이제용

제목: 애플리케이션 모니터링과 디버깅
주차: 4주차

---

애플리케이션 모니터링과 디버깅

- Actuator를 이용한 애플리케이션 상태 모니터링
- 로그를 이용한 효과적인 디버깅 방법
- 로그 분석 도구 소개 (ELK 스택 등)

---

# 어플리케이션 모니터링

## 정의

어플리케이션의 성능을 분석하는 분야로, APM (Application Performance Monitoring)이라고 불린다.

웹 서비스의 백엔드에서 발생하는 이슈를 분석하여 웹 서비스의 성능 문제를 해결하는 용도로 사용된다.

## Actuator를 이용한 애플리케이션 상태 모니터링

Springboot에서 Actuator는 애플리케이션이 현재 살아있는지, 로그 정보는 정상 설정 되었는지, 커넥션 풀은 얼마나 사용되고 있는지 등을 확인할 수 있도록 하는 기능을 제공한다.

```java
implementation 'org.springframework.boot:spring-boot-starter-actuator'
```

### 엔드포인트 설정

Actuator가 제공하는 기능 하나하나를 엔드포인트라 한다.

각각의 엔드포인트는 /actuator/{엔드포인트명}과 같은 형식으로 접근 가능

1. 엔드포인트 활성화 : 해당 기능을 사용할지 말지 선택
2. 엔드포인트 노출 : 노출 위치 설정 (HTTP, JMX)

기본적으로 shutdown을 제외하면 기본적으로 활성화 상태

### application.yml : 모든 엔드포인트를 웹에 노출

```java
management:
  endpoints:
    web:
      exposure:
        include: "*"
        exclude: "env, beans"
```

### application.yml : 특정 엔드포인트를 웹에 노출

```java
management:
  endpoint:
    shutdown:
      enabled: true
```

### 다양한 엔드포인트

| 엔드포인트 | 기능 |
| --- | --- |
| bean | 스프링 컨테이너에 등록된 스프링 빈을 보여준다. |
| conditions | condition 을 통해서 빈을 등록할 때 평가 조건과 일치하거나 일치하지 않는 이유를 표시한다. |
| configprops | @ConfigurationProperties 를 보여준다. |
| env | Environment 정보를 보여준다. |
| health | 애플리케이션 헬스 정보를 보여준다. |
| httpexchanges | HTTP 호출 응답 정보를 보여준다. HttpExchangeRepository 를 구현한 빈을 별 도로 등록해야 한다. |
| info | 애플리케이션 정보를 보여준다. |
| loggers | 애플리케이션 로거 설정을 보여주고 변경도 할 수 있다. |
| metrics | 애플리케이션의 메트릭 정보를 보여준다. |
| mappings | @RequestMapping 정보를 보여준다. |
| threaddump | 쓰레드 덤프를 실행해서 보여준다. |
| shutdown | 애플리케이션을 종료한다. 이 기능은 기본으로 비활성화 되어 있다 |

![스크린샷 2024-11-20 01.27.20.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%A6%E1%84%8B%E1%85%AD%E1%86%BC%20117a1b1d8307817ba6c1d1e7c8a98e33/%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-11-20_01.27.20.png)

## 로그를 이용한 효과적인 디버깅 방법

로그는 코드의 실행 흐름을 추적하고, 오류 발생 시점을 파악하는 데 유용한 도구이다. 따라서 로그를 통해 코드의 상태를 확인하고, 문제의 원인을 분석할 수 있다.

로그는 코드의 실행 과정을 기록하여, 오류 발생 시점과 원인을 명확히 파악할 수 있게 하기 때문이다.

→ 적절한 로그 설정과 관리 전략

### 로그 레벨 설정

SLF4J와 Logback을 사용하여 로깅을 하고, 다양한 로그 레벨을 설정하여 로그를 남긴다.

→ DEBUG : 개발 중 디버깅에 유용한 정보를 출력

특정 클래스나 패키지에 대해 DEBUG 레벨로 설정하여 로그를 확인할 수 있다.

### 로그 파일 저장 및 관리

로그 파일을 생성 및 저장하여 로그를 관리할 수 있다.

```java
logging.file.name=logs/spring-boot-app.log
logging.file.max-size=10MB  // 파일 크기가 10MB를 넘기면 새로운 파일 생성
logging.file.max-history=30 // 최대 30개의 파일을 보관
```

### 비동기 로깅

로그 기록이 동기적으로 처리될 경우 성능에 영향을 줄 수 있기 때문에 비동기 로깅을 통해 성능 저하를 막는다.

Logback의 ‘AsyncAppender’를 사용하여 비동기 방식으로 로그를 처리하여 성능 저하를 줄인다.

비동기 로깅의 경우 로그 발생과 쓰기를 분리하여 애플리케이션의 성능을 향상 시킨다.

```java
<appender name="ASYNC" class="ch.qos.logback.classic.AsyncAppender">
    <appender-ref ref="FILE"/>
</appender>
```

### 환경에 따른 로그 레별 분리

개발 환경과 운영 환경에서 다른 로그 레벨을 적용하여 로그를 남기도록 한다.

```java
# application-dev.yml
logging.level.root=DEBUG

# application-prod.yml
logging.level.root=INFO
```

### 예외 처리 및 스택 트레이스 로깅

예외가 발생했을 때 스택 트레이스를 포함하여 로그에 기록하고, 이를 통해 문제 발생 지점을 정확히 파악한다.

```java
try {
    // 에러 발생!
} catch (Exception e) {
    LOG.error("에러 발생!!!", e);
}
```

### 중앙 집중식 로깅 도구 사용

애플리케이션이 여러 서버에서 실행되는 경우, 중앙 집중식 로깅 도구를 사용한다.

ELK Stack이나 Splunk와 같은 도구를 사용하여 모든 서버의 로그를 한 곳에서 모니터링하고 분석할 수 있다.

## 로그 분석 도구

로그 분석 도구는 시스템, 애플리케이션, 네트워크 장비 등에서 발생하는 로그 데이터를 수집, 저장, 분석하여 문제를 해결하고 성능을 최적화하는 데 중요한 역할을 한다.

### ELK 스택

https://www.elastic.co/kr/elastic-stack

![image.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%A6%E1%84%8B%E1%85%AD%E1%86%BC%20117a1b1d8307817ba6c1d1e7c8a98e33/image.png)

오픈소스 기반의 강력한 로그 분석 솔류션으로 3가지 도구로 구성된다.

![image.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%A6%E1%84%8B%E1%85%AD%E1%86%BC%20117a1b1d8307817ba6c1d1e7c8a98e33/image%201.png)

- Elasticsearch : 분산형 검색 및 분석 엔진으로, 대용량 데이터를 빠르게 검색하고 분석 할 수 있다.
- Logstash : 로그 데이터를 수집하고 변환하는 데이터 파이프라인 도구로 다양한 소스셋 데이터를 수집하여 Elasticsearch로 전송한다.
- Kibana : Elasticsearch에 저장된 데이터를 시각화하는 도구로, 대시보드와 차트를 통해 실시간으로 데이터 모니터링을 할 수 있다.

![image.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%A6%E1%84%8B%E1%85%AD%E1%86%BC%20117a1b1d8307817ba6c1d1e7c8a98e33/image%202.png)

1. Data Processing (Logstash)
    1. 서버 내의 로그,웹,메트릭 등 다양한 소스에서 데이터를 수집하여 입력
    2. 데이터 변환 및 구조 구축
    3. 데이터 출력 및 송신
2. Storage (Elasticsearch)
    1. 데이터 저장
    2. 데이터 분석
    3. 데이터 관리
3. Visualize (Kibana)
    1. Dashboard를 통한 데이터 탐색
    2. 팀원들과 공유 및 협업하는데 사용 가능
    3. 엑세스 제어 사용 가능

### Splunk

https://www.splunk.com/en_us/download.html?utm_campaign=google_apac_kor_en_search_brand&utm_source=google&utm_medium=cpc&utm_content=free_trials_downloads&utm_term=splunk&device=c&_bt=690424212238&_bm=e&_bn=g&gad_source=1&gclid=Cj0KCQiAi_G5BhDXARIsAN5SX7p9gotdxfctYtFD8DVSG1uyC33UNWOvBFJFxUN1S6RHR2PYWVcBhcIaAhZSEALw_wcB

![image.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%A6%E1%84%8B%E1%85%AD%E1%86%BC%20117a1b1d8307817ba6c1d1e7c8a98e33/image%203.png)

상용 로그 관리 및 분석 도구로, 실시간 데이터 수집과 강력한 검색 및 시각화 기능을 제공한다. 머신 데이터에서 유의미한 정보를 추출하여 보안 모니터링, 성능 최적화, 문제 해결에 활용 가능하고, 머신 러닝을 활용한 이상 탐지 기능도 제공하며, 대규모 환경에서도 안정적으로 사용할 수 있다는 특징이 있다.

![image.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%A6%E1%84%8B%E1%85%AD%E1%86%BC%20117a1b1d8307817ba6c1d1e7c8a98e33/image%204.png)

- SPL 검색 언어 사용
- 실식간 데이터 분석
- 머신 러닝 기반의 이상 탐지
- 다양한 데이터 소스와의 통합

### Graylog

https://graylog.org/

오픈소스 로그 관리 플랫폼이며 대용량 로그 데이터를 중앙에서 관리하고 분석할 수 있는 기능을 제공한다.

Elasticsearch와 MongoDB를 기반으로 하여 빠른 검색 속도와 확장성을 제공하며, 사용자 정의 대시보드와 알림 기능도 지원한다. Graylog는 특히 보안 로그 분석 및 규정 준수 보고서 생성에 유용하다.

![image.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%A6%E1%84%8B%E1%85%AD%E1%86%BC%20117a1b1d8307817ba6c1d1e7c8a98e33/image%205.png)

- 고성능 로그 수집 및 인덱싱
- 사용자 정의 가능한 대시보드
- 확장 가능한 아키텍쳐