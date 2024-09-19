package pojo.shop_2_8_iv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import pojo.shop_2_8_iv.SequenceGenerator;

@Configuration
@ComponentScan("pojo.shop_2_8_iv")
public class SequenceConfiguration {

    @Bean
    @DependsOn("datePrefixGenerator") // datePrefixGenerator 에 의존하기 때문에 해당 빈이 먼저 생성한다. (단순 문자열 이외에 {} 속성으로 여러개의 인자 전달 가능)
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator sequence = new SequenceGenerator();
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        return sequence;
    }
}


