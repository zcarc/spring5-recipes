package pojo.shop_2_8_ii;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Battery extends Product {

    private boolean rechargeable;

    public Battery() {
        super();
    }

    public Battery(String name, double price) {
        super(name, price);
    }
}
