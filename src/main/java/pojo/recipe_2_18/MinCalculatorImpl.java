package pojo.recipe_2_18;

public class MinCalculatorImpl implements MinCalculator {

    @Override
    public double min(double a, double b) {
        double result = Math.min(a, b);
        System.out.println("result = " + result);

        return result;
    }
}
