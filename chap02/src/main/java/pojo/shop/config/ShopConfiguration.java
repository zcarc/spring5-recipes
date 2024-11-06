package pojo.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import pojo.shop.BannerLoader;
import pojo.shop.Battery;
import pojo.shop.Disc;
import pojo.shop.Product;

@Configuration
@PropertySource("classpath:discounts.properties")
@ComponentScan("pojo.shop")
public class ShopConfiguration {

    // :0 '0' 부분은 프로퍼티에 해당 key가 없으면 기본값을 설정하는 부분이다.
    @Value("${specialcustomer.discount:0}")
    private double specialCustomerDiscountField;

    @Value("${summer.discount:0}")
    private double specialSummerDiscountField;

    @Value("${endofyear.discount:0}")
    private double specialEndofyearDiscountField;

    @Value("classpath:banner.txt")
    private Resource banner;

    @Bean
    public BannerLoader bannerLoader() {
        BannerLoader bl = new BannerLoader();
        bl.setBanner(banner);
        return bl;
    }

    @Bean
    public Product aaa() {
        Battery p1 = new Battery();
        p1.setName("AAA");
        p1.setPrice(2.5);
        p1.setRechargeable(true);
        p1.setDiscount(specialCustomerDiscountField);
        return p1;
    }

    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", 1.5, specialSummerDiscountField);
        p2.setCapacity(700);
        return p2;
    }

    @Bean
    public Product dvdrw() {
        Disc p2 = new Disc("DVD-RW", 3.0, specialEndofyearDiscountField);
        p2.setCapacity(700);
        return p2;
    }
}
