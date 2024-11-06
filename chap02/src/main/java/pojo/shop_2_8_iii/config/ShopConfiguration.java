package pojo.shop_2_8_iii.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pojo.shop_2_8_iii.Battery;
import pojo.shop_2_8_iii.Disc;
import pojo.shop_2_8_iii.Product;

@Configuration
@ComponentScan("pojo.shop_2_8_iii")
public class ShopConfiguration {

    @Bean
    public Product aaa() {
        Battery p1 = new Battery();
        p1.setName("AAA");
        p1.setPrice(2.5);
        p1.setRechargeable(true);
        return p1;
    }

    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", 1.5);
        p2.setCapacity(700);
        return p2;
    }

    @Bean
    public Product dvdrw() {
        Disc p2 = new Disc("DVD-RW", 3.0);
        p2.setCapacity(700);
        return p2;
    }
}
