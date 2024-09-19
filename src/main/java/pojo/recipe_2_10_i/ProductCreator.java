package pojo.recipe_2_10_i;

public class ProductCreator {

    public static Product createProduct(String productId) {

        if("aaa".equals(productId)) {
            return new Battery("AAA", 2.5);
        } else if("cdrw".equals(productId)) {
            return new Disc("CD-RW", 1.5);
        } else if("dvdrw".equals(productId)) {
            return new Disc("DV-RW", 3.0);
        }

        throw new IllegalArgumentException("Invalid product ID: " + productId);
    }
}
