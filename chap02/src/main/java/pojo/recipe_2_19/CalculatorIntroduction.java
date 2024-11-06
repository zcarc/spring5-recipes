package pojo.recipe_2_19;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {

    @DeclareParents(
            value = "pojo.recipe_2_19.*CalculatorImpl",
            defaultImpl = CounterImpl.class)
    public Counter counter;

    @After(value = "execution(* pojo.recipe_2_19.*Calculator.*(..))" + " && this(counter)", argNames = "counter")
    public void increaseCount(Counter counter) {
        counter.increase();
    }

}
