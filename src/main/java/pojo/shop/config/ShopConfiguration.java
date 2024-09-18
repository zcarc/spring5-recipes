package pojo.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pojo.shop.Disc;
import pojo.shop.Product;

@Configuration
@PropertySource("classpath:discounts.properties")
@ComponentScan("pojo.shop")
public class ShopConfiguration {

    // :0 '0' 부분은 프로퍼티에 해당 key가 없으면 기본값을 설정하는 부분이다.
    @Value("${endofyear.discount:0}")
    private double specialEndofyearDiscountField;

    @Bean
    public Product dvdrw() {
        Disc p2 = new Disc("DVD-RW", 3.0, specialEndofyearDiscountField);
        p2.setCapacity(700);
        return p2;
    }
}
