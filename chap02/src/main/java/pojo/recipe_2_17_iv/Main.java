package pojo.recipe_2_17_iv;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new GenericXmlApplicationContext("recipe_2_17_iv/appContext.xml");

        ArithmeticCalculator arithmeticCalculator =
                (ArithmeticCalculator) context.getBean("arithmeticCalculator");
        arithmeticCalculator.div(4, 2);

        UnitCalculator unitCalculator =
                (UnitCalculator) context.getBean("unitCalculator");
        unitCalculator.kilometerToMile(5);
    }
}
