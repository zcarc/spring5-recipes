package pojo.sequence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("pojo.sequence");
        SequenceService service = context.getBean(SequenceService.class);
        String generated1 = service.generate("IT");
        System.out.println("generated1 = " + generated1);
        String generated2 = service.generate("IT");
        System.out.println("generated2 = " + generated2);

    }
}
