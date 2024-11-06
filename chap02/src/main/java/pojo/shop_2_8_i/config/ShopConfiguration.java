package pojo.shop_2_8_i.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pojo.shop_2_8_i.Battery;
import pojo.shop_2_8_i.Cashier;
import pojo.shop_2_8_i.Disc;
import pojo.shop_2_8_i.Product;

@Configuration
@ComponentScan("pojo.shop_2_8_i")
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

    // 책에서는 빈 생성 이전에 openFile 가 호출된다고 하고 있지만
    // 실제로는 빈 생성 이후에 openFile 가 호출된다.
    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier cashier() {

        String path = System.getProperty("java.io.tmpdir") + "/cashier";
        Cashier c1 = new Cashier();
        c1.setFileName("checkout");
        c1.setPath(path);
        return c1;
    }
}
