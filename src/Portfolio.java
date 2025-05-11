// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class Portfolio implements Serializable {
    private static Portfolio port;
    private static String username;
    private List<Asset> assets;
    private File FILE_NAME;
    String uSername;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Zakatcalculator zakatcalculator;

    private Portfolio(String usernameKey) {
        username = usernameKey.replaceAll("@.*", "");
        zakatcalculator = new Zakatcalculator(username);
        FILE_NAME = new File(username + "_portfolio.txt");
        assets = loadAssets();
    }

    private void saveAssets() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.FILE_NAME));

            try {
                oos.writeObject(this.assets);
            } catch (Throwable var5) {
                try {
                    oos.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            oos.close();
        } catch (IOException var6) {
            System.out.println("Error -->saving portfolio: " + var6.getMessage());
        }

    }

    private List<Asset> loadAssets() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));

            List var2;
            try {
                var2 = (List)in.readObject();
            } catch (Throwable var5) {
                try {
                    in.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            in.close();
            return var2;
        } catch (ClassNotFoundException | IOException var6) {
            return new ArrayList();
        }
    }

    public static Portfolio getInstance(String usernameKey) {
        if (port == null) {
            port = new Portfolio(usernameKey);
        }

        return port;
    }


    public List<Asset> getAssets() {
        return new ArrayList(this.assets);
    }

    public void addAsset(Asset asset) {
        this.assets.add(asset);
        this.saveAssets();
    }

    public void removeAsset(Asset asset) {
        this.assets.remove(asset);
        this.saveAssets();
    }

    public void editAsset(int index, Asset newAsset) {
        this.assets.set(index, newAsset);
        this.saveAssets();
    }

    public static String getUsername() {
        return username;
    }
    public Zakatcalculator getZakatcalculator() {
        return zakatcalculator;
    }
}
