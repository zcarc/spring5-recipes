package pojo.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.shop.config.ShopConfiguration;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Battery aaa = context.getBean("aaa", Battery.class);
        Disc cdrw = context.getBean("cdrw", Disc.class);

        System.out.println("aaa = " + aaa);
        System.out.println("cdrw = " + cdrw);
    }

}