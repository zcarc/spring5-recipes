package pojo.recipe_3_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pojo.recipe_3_1.web.ExtensionInterceptor;
import pojo.recipe_3_1.web.MeasuremenInterceptor_Class;

public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(measuremenInterceptor());
        registry.addInterceptor(summaryReporterInterceptor()).addPathPatterns("/reservationSummary");
    }

    @Bean
    public MeasuremenInterceptor_Class measuremenInterceptor() {
        return new MeasuremenInterceptor_Class();
    }

    @Bean
    public ExtensionInterceptor summaryReporterInterceptor() {
        return new ExtensionInterceptor();
    }

}
