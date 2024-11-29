package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// Logging AOP 적용
@Slf4j
@Aspect // 이 클래스가 AOP에서 사용될 "Aspect"
@Component
public class LoggingAspect {

    // 컨트롤러와 서비스의 모든 메서드를 대상으로 지정
    @Pointcut("execution(* com.example.demo.controller.*.*(..)) || execution(* com.example.demo.service.*.*(..))")
    public void applicationMethods() {}

    // 메서드 실행 전
    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("메서드 호출 시작: {}", joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            log.info("전달된 파라미터:");
            for (Object arg : args) {
                log.info("  {}", arg);
            }
        }
    }

    // 메서드 실행 후
    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("메서드 호출 성공: {}", joinPoint.getSignature().toShortString());
        log.info("반환 값: {}", result);
    }

    // 예외 발생 시
    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("메서드 실행 중 예외 발생: {}", joinPoint.getSignature().toShortString());
        log.error("예외 메시지: {}", exception.getMessage(), exception);
    }
}




