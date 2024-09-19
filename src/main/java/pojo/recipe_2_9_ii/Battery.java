package pojo.recipe_2_9_ii;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Battery extends Product {

    private boolean rechargeable;

    public Battery() {
        super();
        System.out.println("Battery no args...");
    }

    public Battery(String name, double price) {
        super(name, price);
        System.out.println("Battery two args...");
    }
}
