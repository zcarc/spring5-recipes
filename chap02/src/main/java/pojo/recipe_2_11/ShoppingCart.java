package pojo.recipe_2_11;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
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
