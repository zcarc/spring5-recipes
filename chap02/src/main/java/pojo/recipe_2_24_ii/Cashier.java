package pojo.recipe_2_24_ii;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.Date;

public class Cashier implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void checkout(ShoppingCart cart) {
        CheckoutEvent event = new CheckoutEvent(cart, new Date());
        applicationEventPublisher.publishEvent(event);
    }
}
