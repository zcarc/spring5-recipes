package pojo.recipe_2_12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pojo.recipe_2_12.Cashier;

@Configuration
@Profile("global")
@ComponentScan("pojo.recipe_2_12")
public class ShopConfigurationGlobal {

    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier cashier() {
        final String path = System.getProperty("java.io.tmpdir") + "cashier";
        Cashier c1 = new Cashier();
        c1.setPath(path);
        return c1;
    }
}
