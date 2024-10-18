package pojo.recipe_2_17_i;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalculatorLoggingAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("CalculatorPointcuts.loggingOperation()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() begins with " + Arrays.toString(joinPoint.getArgs()));
    }

//    @After("CalculatorPointcuts.loggingOperation()")
//    public void logAfter(JoinPoint joinPoint) {
//        log.info("The method " + joinPoint.getSignature().getName()
//                + "() ends");
//    }
//
//    @AfterReturning(
//            pointcut = "CalculatorPointcuts.loggingOperation()",
//            returning = "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("The method " + joinPoint.getSignature().getName()
//                + "() ends with " + result);
//    }
//
//    @AfterThrowing(
//            pointcut = "CalculatorPointcuts.loggingOperation()",
//            throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, IllegalArgumentException e) {
//        log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs())
//                + " in " + joinPoint.getSignature().getName() + "()");
//    }
//
//    @Around("CalculatorPointcuts.loggingOperation()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("The method " + joinPoint.getSignature().getName()
//                + "() begins with " + Arrays.toString(joinPoint.getArgs()));
//        try {
//            Object result = joinPoint.proceed();
//            log.info("The method " + joinPoint.getSignature().getName()
//                    + "() ends with " + result);
//            return result;
//        } catch (IllegalArgumentException e) {
//            log.error("Illegal argument "
//                    + Arrays.toString(joinPoint.getArgs()) + " in "
//                    + joinPoint.getSignature().getName() + "()");
//            throw e;
//        }
//    }
}
