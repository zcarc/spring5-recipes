package pojo.recipe_2_17_iii;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CalculatorLoggingAspect {

    private Log log = LogFactory.getLog(this.getClass());

//    @Before("CalculatorPointcuts.loggingOperation()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("The method " + joinPoint.getSignature().getName()
//                + "() begins with " + Arrays.toString(joinPoint.getArgs()));
//    }

    @After("CalculatorPointcuts.loggingOperation()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() ends");
    }

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