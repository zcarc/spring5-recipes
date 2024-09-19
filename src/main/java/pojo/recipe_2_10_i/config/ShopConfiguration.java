package pojo.recipe_2_10_i.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pojo.recipe_2_10_i.Product;
import pojo.recipe_2_10_i.ProductCreator;

@Configuration
@ComponentScan("pojo.recipe_2_10_i")
public class ShopConfiguration {

    @Bean
    public Product aaa() {
        return ProductCreator.createProduct("aaa");
    }

    @Bean
    public Product cdrw() {
        return ProductCreator.createProduct("cdrw");
    }

    @Bean
    public Product dvdrw() {
        return ProductCreator.createProduct("dvdrw");
    }
}
