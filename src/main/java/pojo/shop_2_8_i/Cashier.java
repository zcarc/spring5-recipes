package pojo.shop_2_8_i;

import java.io.*;
import java.util.Date;

public class Cashier {

    private String fileName;
    private String path;
    private BufferedWriter writer;

    public Cashier() {
        System.out.println("Cashier constructor...");
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
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
