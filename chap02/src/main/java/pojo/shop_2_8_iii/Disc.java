package pojo.shop_2_8_iii;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Disc extends Product {

    private int capacity;

    public Disc() {
        super();
        System.out.println("Dics no args...");
    }

    public Disc(String name, double price) {
        super(name, price);
        System.out.println("Dics two args...");
    }
}
