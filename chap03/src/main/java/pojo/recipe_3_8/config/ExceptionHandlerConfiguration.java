package pojo.recipe_3_8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import pojo.recipe_3_8.service.ReservationNotAvailableException;

import java.util.List;
import java.util.Properties;

@Configuration
public class ExceptionHandlerConfiguration implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(handlerExceptionResolver());
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        Properties exceptionMapping = new Properties();
        exceptionMapping.setProperty(ReservationNotAvailableException.class.getName(), "recipe_3_8/reservationNotAvailable");

        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        exceptionResolver.setExceptionMappings(exceptionMapping);
        exceptionResolver.setDefaultErrorView("recipe_3_8/error");
        return exceptionResolver;
    }
}
