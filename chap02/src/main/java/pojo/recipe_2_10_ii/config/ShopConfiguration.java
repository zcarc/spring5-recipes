package pojo.recipe_2_10_ii.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pojo.recipe_2_10_ii.Battery;
import pojo.recipe_2_10_ii.Disc;
import pojo.recipe_2_10_ii.Product;
import pojo.recipe_2_10_ii.ProductCreator;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("pojo.recipe_2_10_ii")
public class ShopConfiguration {

    @Bean
    public ProductCreator productCreatorFactory() {

        Map<String, Product> products = new HashMap<>();
        products.put("aaa", new Battery("AAA", 2.5));
        products.put("cdrw", new Disc("CD-RW", 1.5));
        products.put("dvdrw", new Disc("DVD-RW", 3.0));

        ProductCreator factory = new ProductCreator();
        factory.setProducts(products);

        return factory;
    }

    @Bean
    public Product aaa() {
        return productCreatorFactory().createProduct("aaa");
    }

    @Bean
    public Product cdrw() {
        return productCreatorFactory().createProduct("cdrw");
    }

    @Bean
    public Product dvdrw() {
        return productCreatorFactory().createProduct("dvdrw");
    }
}
