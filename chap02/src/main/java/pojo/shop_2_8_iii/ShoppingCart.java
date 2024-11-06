package pojo.shop_2_8_iii;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Lazy
public class ShoppingCart {

    private List<Product> items = new ArrayList<>();

    public ShoppingCart() {
        System.out.println("ShoppingCart...");
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public List<Product> getItems() {
        return items;
    }
}
