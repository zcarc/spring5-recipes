package pojo.shop_2_8_iii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.shop_2_8_iii.config.ShopConfiguration;

public class Main {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);


        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        Product dvdrw = context.getBean("dvdrw", Product.class);

        ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
        cart1.addItem(aaa);
        cart1.addItem(cdrw);
        System.out.println("Shopping cart 1 contains " + cart1.getItems());

        ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
        cart2.addItem(dvdrw);
        System.out.println("Shopping cart 2 contains " + cart2.getItems());

        Cashier cashier = context.getBean("cashier", Cashier.class);
        cashier.checkout(cart1);
        cashier.checkout(cart2);

        // 이 부분을 명시하지 않으면 컨텍스트가 종료될 때,
        // Cashier 빈에 있는 destroyMethod 로 설정된 메서드는 호출되지 않는다.
        context.close();
    }
}
