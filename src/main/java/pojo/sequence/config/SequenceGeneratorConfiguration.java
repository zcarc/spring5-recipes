package pojo.sequence.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.sequence.SequenceGenerator;

@Configuration
public class SequenceGeneratorConfiguration {

    @Bean
    public SequenceGenerator sequenceGenerator() {

        SequenceGenerator seqgen = new SequenceGenerator();
        seqgen.setPrefix("30");
        seqgen.setSuffix("A");
        seqgen.setInitial(100000);
        return seqgen;
    }
}