package pojo.shop_2_8_iii;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public abstract class Product {

    private String name;
    private double price;
    private double discount;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
