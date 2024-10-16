package pojo.recipe_2_17_ii;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class CalculatorPointcuts {

    @Pointcut("within(pojo.recipe_2_17_ii.LoggingRequired)")
    public void loggingOperation() {
    }

}
