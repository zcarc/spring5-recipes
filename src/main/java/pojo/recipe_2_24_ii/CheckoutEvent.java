package pojo.recipe_2_24_ii;

import lombok.Getter;

import java.util.Date;

/**
 * CheckoutListener 에서 @EventListener 를 사용하므로 ApplicationEvent 를 상속할 필요가 없다.
 */

@Getter
public class CheckoutEvent {

    private final ShoppingCart cart;
    private final Date time;

    public CheckoutEvent(ShoppingCart cart, Date time) {
        this.cart = cart;
        this.time = time;
    }
}
