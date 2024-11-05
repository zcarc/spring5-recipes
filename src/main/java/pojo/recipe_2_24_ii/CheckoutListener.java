package pojo.recipe_2_24_ii;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener {

    /*
    *   스프링 4.2 부터는 @EventListener 만 붙여도 이벤트 리스너로 만들 수 있다.
    */

    @EventListener
    public void onApplicationEvent(CheckoutEvent event) {
        // 체크아웃 시 수행할 로직을 여기에 구현합니다.
        System.out.println("Checkout event [" + event.getTime() + "]");
    }
}
