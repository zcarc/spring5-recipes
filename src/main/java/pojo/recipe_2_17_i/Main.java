package pojo.recipe_2_17_i;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new GenericXmlApplicationContext("recipe_2_17_i/appContext.xml");

        ArithmeticCalculator arithmeticCalculator =
                (ArithmeticCalculator) context.getBean("arithmeticCalculator");
        arithmeticCalculator.add(1, 2);
        arithmeticCalculator.sub(4, 3);
//        arithmeticCalculator.mul(2, 3);
//        arithmeticCalculator.div(4, 2);

//        UnitCalculator unitCalculator =
//                (UnitCalculator) context.getBean("unitCalculator");
//        unitCalculator.kilogramToPound(10);
//        unitCalculator.kilometerToMile(5);
    }
}