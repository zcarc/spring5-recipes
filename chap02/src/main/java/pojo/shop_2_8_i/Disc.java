package pojo.shop_2_8_i;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Disc extends Product {

    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price) {
        super(name, price);
    }
}
