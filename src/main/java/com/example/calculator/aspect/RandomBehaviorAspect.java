package com.example.calculator.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Aspect
public class RandomBehaviorAspect {

    private Random random = new Random();

    @Pointcut("within(com.example.calculator.service..*)")
    public void serviceLayer() {
        // Pointcut for methods within the service layer
    }

    @Around("serviceLayer()")
    public Object aroundServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
        if (random.nextBoolean()) {
            // Introduce a delay of up to 1000ms
            Thread.sleep(random.nextInt(1000));
        }

        if (random.nextBoolean()) {
            // Throw a custom exception randomly
            throw new RuntimeException("Random failure injected for resilience testing");
        }

        return pjp.proceed();
    }
}