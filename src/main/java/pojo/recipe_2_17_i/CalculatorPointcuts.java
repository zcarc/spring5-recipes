package pojo.recipe_2_17_i;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {

    @Pointcut("@annotation(pojo.recipe_2_17_i.LoggingRequired)")
    public void loggingOperation() {
    }
}
