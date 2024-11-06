package pojo.recipe_2_11;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public abstract class Product {

    private String name;
    private double price;

    public Product() {
        System.out.println("Product no args...");
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        System.out.println("Product two args...");
    }
}
