package recipe_4_1_i.web.config;


//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.oxm.Marshaller;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.BeanNameViewResolver;
//import org.springframework.web.servlet.view.xml.MarshallingView;
//import recipe_4_1_i.domain.Member;
//import recipe_4_1_i.domain.Members;
//
//import java.util.Collections;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "recipe_4_1_i")
//public class CourtRestConfiguration {
//
//    @Bean
//    public View membertemplate() {
//        System.out.println("membertemplate");
//        return new MarshallingView(jaxb2Marshaller());
//    }
//
//    @Bean
//    public Marshaller jaxb2Marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setClassesToBeBound(Members.class, Member.class);
//        marshaller.setMarshallerProperties(Collections.singletonMap(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE));
//        return marshaller;
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        return new BeanNameViewResolver();
//    }
//}
