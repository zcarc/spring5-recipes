package pojo.recipe_2_10_iii;

import java.util.Map;

public class ProductCreator {

    private Map<String, Product> products;

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Product createProduct(String productId) {

        Product product = products.get(productId);
        if (product != null) {
            return product;
        }

        throw new IllegalArgumentException("Product with id " + productId + " not found");
    }

}
