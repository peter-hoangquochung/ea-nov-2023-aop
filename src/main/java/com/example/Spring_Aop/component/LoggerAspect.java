package com.example.Spring_Aop.component;

import com.example.Spring_Aop.entity.ActivityLog;
import com.example.Spring_Aop.repository.ActivityRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
@AllArgsConstructor
public class LoggerAspect {
    private final ActivityRepository activityRepository;
    private final HttpServletRequest request;

    @Around("@annotation(ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        ActivityLog activityLog = new ActivityLog();
        activityLog.setDuration(executionTime);
        activityLog.setDate(LocalDate.now());
        activityLog.setOperation(joinPoint.getSignature().getName());
        activityRepository.save(activityLog);

        return proceed;
    }

    @Before("execution(* com.example.Spring_Aop.service.*.*(..))")
    public void checkAopIsAwesomeHeader(JoinPoint joinPoint) {
        if (request.getMethod().equals("POST")) {
            if (!"AOP-IS-AWESOME".equals(request.getHeader("AOP-IS-AWESOME"))) {
                throw new AopIsAwesomeHeaderException("AOP-IS-AWESOME header is missing");
            }
        }
    }
}