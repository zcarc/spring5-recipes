package pojo.recipe_3_5.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Locale;

@RequestMapping("/welcome")
@Controller
public class WelcomeController {

    @Autowired
    ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private ApplicationContext applicationContext;


    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model) {
        Date today = new Date();
        model.addAttribute("today", today);

//        String de = messageSource.getMessage("welcome.title", null, new Locale("de"));
//        System.out.println("de = " + de);

        ReloadableResourceBundleMessageSource messageSource1 = applicationContext.getBean("messageSource", ReloadableResourceBundleMessageSource.class);
        System.out.println("messageSource1 = " + messageSource1);

        return "recipe_3_5/welcome";
    }
}
