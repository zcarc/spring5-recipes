package pojo.recipe_2_17_iv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CalculatorLoggingAspect {

    private Log log = LogFactory.getLog(this.getClass());


    @Before("CalculatorPointcuts.parameterPointcut(target, a, b)")
    public void beforeMethod(Object target, double a, double b) {
        System.out.println("메소드 실행 전");
        log.info("Target class : " + target.getClass().getName());
        log.info("Arguments : " + a + ", " + b);
    }


}