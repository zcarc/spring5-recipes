package pojo.shop_2_7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ShopConfiguration {

    // 이 메서드 이름을 바꾸면 스프링이 인식하지 못한다.
    // 캐시를 1초로 설정하면 메세지를 다시 읽을 때, 최종 수정 타임스탬프 이후의 변경사항이 있는 메세지만 읽는다.
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:2_7/messages");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }
}
