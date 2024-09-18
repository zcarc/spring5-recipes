package pojo.shop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Disc extends Product {

    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price, double discount) {
        super(name, price, discount);
    }
}
