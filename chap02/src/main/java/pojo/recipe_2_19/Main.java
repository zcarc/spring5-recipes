package pojo.recipe_2_19;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ArithmeticCalculator arithmeticCalculator = context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
        UnitCalculator unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);

        Counter arithmeticCounter = (Counter) arithmeticCalculator;
        System.out.println(arithmeticCounter.getCount());

        arithmeticCounter.increase();
        System.out.println(arithmeticCounter.getCount());


        Counter unitCounter = (Counter) unitCalculator;
        System.out.println(unitCounter.getCount());

        unitCounter.increase();
        unitCounter.increase();
        System.out.println(unitCounter.getCount());
    }
}
