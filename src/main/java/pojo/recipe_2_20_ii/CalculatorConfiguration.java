package pojo.recipe_2_20_ii;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan("pojo.recipe_2_20_ii")
@Configuration
public class CalculatorConfiguration {

    @Bean
    public Complex complex() {
        return new Complex();
    }

}
