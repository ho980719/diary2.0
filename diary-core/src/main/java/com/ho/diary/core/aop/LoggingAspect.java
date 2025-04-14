package com.ho.diary.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  @Around("execution(* com.ho.diary..controller..*(..)) || " +
    "execution(* com.ho.diary..service..*(..)) || " +
    "execution(* com.ho.diary..repository..*(..))")
  public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    // JwtTokenProvider logging skip

    Signature signature = joinPoint.getSignature();
    String methodName = signature.toShortString();
    Object[] args = joinPoint.getArgs();

    log.info("➡️ [START] {} args = {}", methodName, Arrays.toString(args));

    long start = System.currentTimeMillis();
    try {
      Object result = joinPoint.proceed();
      long end = System.currentTimeMillis();

      log.info("✅ [END] {} result = {} ({}ms)", methodName, result, (end - start));
      return result;
    } catch (Throwable throwable) {
      log.error("❌ [ERROR] {} message = {}", methodName, throwable.getMessage(), throwable);
      throw throwable;
    }
  }
}