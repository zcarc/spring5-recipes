package pojo.shop_2_7;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfiguration.class);

        GenericXmlApplicationContext context = new GenericXmlApplicationContext("2_7/shop-config.xml");

        String alert = context.getMessage("alert.checkout", null, Locale.US);
        String alert_inventory = context.getMessage("alert.inventory.checkout",
                new Object[]{"[DVD-RW 3.0]", new Date()},
                Locale.US);

        System.out.println("The I18N message for alert.checkout is: " + alert);
        System.out.println("The I18N message for alert.inventory.checkout is: " + alert_inventory);
    }
}
