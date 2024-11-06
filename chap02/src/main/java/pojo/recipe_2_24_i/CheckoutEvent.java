package pojo.recipe_2_24_i;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

@Getter
public class CheckoutEvent extends ApplicationEvent {

    private final ShoppingCart cart;
    private final Date time;

    public CheckoutEvent(ShoppingCart cart, Date time) {
        super(cart);
        this.cart = cart;
        this.time = time;
    }
}
