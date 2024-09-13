package pojo.sequence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.sequence.config.SequenceConfiguration;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SequenceConfiguration.class);
        SequenceGenerator generator = context.getBean(SequenceGenerator.class);

        System.out.println("generator.getSequence() = " + generator.getSequence());
        System.out.println("generator.getSequence() = " + generator.getSequence());
    }
}
