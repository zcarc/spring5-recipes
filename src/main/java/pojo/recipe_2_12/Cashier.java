package pojo.recipe_2_12;

import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;

import java.io.*;
import java.util.Date;

@ToString
public class Cashier implements BeanNameAware {

    private String fileName = "checkout";
    private String path = System.getProperty("java.io.tmpdir") + "/cashier";;
    private BufferedWriter writer;

    public Cashier() {
        System.out.println("Cashier constructor...");
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setBeanName(String name) {
        this.fileName = name;
    }

    public void openFile() throws IOException {

        System.out.println("openFile...");

        File targetDir = new File(path);
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }

        File checkoutFile = new File(path, fileName + ".txt");
        if (!checkoutFile.exists()) {
            checkoutFile.createNewFile();
        }

        writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(checkoutFile, true)));
    }

    public void checkout(ShoppingCart cart) throws IOException {
        writer.write(new Date() + "\t" + cart.getItems() + "\r\n");
        writer.flush();
    }

    public void closeFile() throws IOException {
        System.out.println("closeFile...");
        writer.close();
    }
}
