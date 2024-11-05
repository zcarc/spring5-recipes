package pojo.recipe_2_24_i;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener implements ApplicationListener<CheckoutEvent> {

    @Override
    public void onApplicationEvent(CheckoutEvent event) {
        // 체크아웃 시 수행할 로직을 여기에 구현합니다.
        System.out.println("Checkout event [" + event.getTime() + "]");
    }
}
