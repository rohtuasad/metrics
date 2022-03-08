package ru.rohtuasad.metrics;

import java.lang.StackWalker.StackFrame;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricsAspect {

  public List<StackFrame> walkExample(Stream<StackFrame> stackFrameStream) {
    return stackFrameStream.collect(Collectors.toList());
  }

  @Around("@annotation(LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("Entering " + joinPoint );
    long start = System.currentTimeMillis();

    Object proceed = joinPoint.proceed();
    List<StackFrame> stackTrace = StackWalker.getInstance()
        .walk(this::walkExample);
    stackTrace.forEach(t -> log.info(t.getClassName() + " " +  t.getMethodName()));

    long executionTime = System.currentTimeMillis() - start;

    log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
    return proceed;
  }
}
