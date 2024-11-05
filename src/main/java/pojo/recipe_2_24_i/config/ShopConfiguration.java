package pojo.recipe_2_24_i.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pojo.recipe_2_24_i.Cashier;

@Configuration
@ComponentScan("pojo.recipe_2_24_i")
public class ShopConfiguration {

    @Bean
    public Cashier cashier() {
        return new Cashier();
    }
}
