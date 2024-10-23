package pojo.recipe_2_18;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new GenericXmlApplicationContext("recipe_2_18/appContext.xml");

        ArithmeticCalculator arithmeticCalculator = context.getBean(ArithmeticCalculator.class);

        MaxCalculator maxCalculator = (MaxCalculator) arithmeticCalculator;
        maxCalculator.max(1, 2);

        MinCalculator minCalculator = (MinCalculator) arithmeticCalculator;
        minCalculator.min(1, 2);

    }
}
