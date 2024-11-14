//package pojo.recipe_3_4.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import java.util.Locale;
//
////@Configuration
//public class I18NConfiguration_i implements WebMvcConfigurer {
//
//
//    // 브라우저 쿠키값에 따라 로케일 설정
////    @Bean
//    public LocaleResolver localeResolver_Cookie() {
//        // 쿠키가 없으면 accept-language 헤더로 기본 로케일을 결정합니다.
//        return new CookieLocaleResolver();
//    }
//
//    // 세션 속성에 따라 로케일 해석하기
////    @Bean
//    public LocaleResolver localeResolver_Session() {
//
//        // 세션이 없으면 accept-language 헤더로 기본 로케일을 결정합니다.
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//
//        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//        return sessionLocaleResolver;
//    }
//
////    @Bean
//    public LocaleResolver localeResolver_Custom() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setCookieName("language");
//        cookieLocaleResolver.setCookieMaxAge(3600);
//        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//        return cookieLocaleResolver;
//    }
//}
