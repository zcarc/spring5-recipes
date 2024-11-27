package pojo.recipe_3_9.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pojo.recipe_3_9.domain.SportTypeConverter;
import pojo.recipe_3_9.service.ReservationService;

@ComponentScan("pojo.recipe_3_9")
@EnableWebMvc
@Configuration
public class BindingConfiguration implements WebMvcConfigurer {

    @Autowired
    private ReservationService reservationService;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SportTypeConverter(reservationService));
    }
}
