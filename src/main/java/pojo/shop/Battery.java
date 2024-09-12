package pojo.shop;

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
