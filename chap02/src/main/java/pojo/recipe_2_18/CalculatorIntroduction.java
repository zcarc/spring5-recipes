package pojo.recipe_2_18;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {

//    특정 클래스로 지정하려면 execution 포인트컷 표현식을 사용하면 된다.
//    value = "execution(* pojo.recipe_2_18.ArithmeticCalculatorImpl.*(..))"

    @DeclareParents(
            value = "pojo.recipe_2_18.ArithmeticCalculatorImpl+",
            defaultImpl = MaxCalculatorImpl.class)
    public MaxCalculator maxCalculator;

    @DeclareParents(
            value = "pojo.recipe_2_18.ArithmeticCalculatorImpl+",
            defaultImpl = MinCalculatorImpl.class)
    public MinCalculator minCalculator;
}
