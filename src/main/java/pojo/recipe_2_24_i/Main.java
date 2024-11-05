package pojo.recipe_2_24_i;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.recipe_2_24_i.config.ShopConfiguration;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);

        ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
        System.out.println("cart1 = " + cart1);

        ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
        System.out.println("cart2 = " + cart2);

        Cashier cashier = context.getBean("cashier", Cashier.class);
        cashier.checkout(cart1);
        cashier.checkout(cart2);
    }
}
