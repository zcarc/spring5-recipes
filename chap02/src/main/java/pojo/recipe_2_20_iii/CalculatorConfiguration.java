package pojo.recipe_2_20_iii;

import org.springframework.context.annotation.*;

@EnableLoadTimeWeaving
@EnableAspectJAutoProxy
@ComponentScan("pojo.recipe_2_20_iii")
@Configuration
public class CalculatorConfiguration {

    @Bean
    public Complex complex() {
        return new Complex();
    }

}
