package pojo.recipe_2_17_ii;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class CalculatorPointcuts {

    // 해당 패키지의 모든 타입(클래스)에 적용
//    @Pointcut("within(pojo.recipe_2_17_ii.*)")
//    public void loggingOperation() {
//    }

    @Pointcut("within(pojo.recipe_2_17_ii.*)")
    public void loggingOperationAll() {
    }

    // 해당 패키지에 해당 애너테이션이 붙은 메서드만 적용
    @Pointcut("within(pojo.recipe_2_17_ii.LoggingRequired)")
    public void loggingOperation() {
    }

}
