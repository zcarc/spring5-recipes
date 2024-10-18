package pojo.recipe_2_18;

public class MaxCalculatorImpl implements MaxCalculator {

    public double max(double a, double b) {

        double result = Math.max(a, b);
        System.out.println("result = " + result);

        return result;
    }
}
