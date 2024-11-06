package pojo.recipe_2_17_iii;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {

    @Pointcut("within(ArithmeticCalculator+)")
    public void arithmeticOperation() {
    }

    @Pointcut("within(UnitCalculator+)")
    public void unitOperation() {
    }

    @Pointcut("within(ArithmeticCalculator+) || within(UnitCalculator+)")
    public void loggingOperation() {
    }

//    위와 같은 방식으로 더 간단하게는 이렇게 표현이 가능하다.
//    @Pointcut("arithmeticOperation() || unitOperation()")
//    public void loggingOperation() {
//    }

//    메서드 시그니처 패턴과 동일하게 클래스 또는 메서드 레벨에 설정해야 한다.
//    @Pointcut("within(pojo.recipe_2_17_iii.LoggingRequired)")
//    public void loggingOperation2() {
//    }

}
