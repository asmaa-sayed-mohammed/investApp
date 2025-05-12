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
import java.util.Scanner;
import java.util.Date;
/**
 * The {@code Portfolio} class manages a user's collection of financial assets such as stocks, real estate, and gold.
 * It handles adding, editing, removing, loading, and saving assets to a user-specific file.
 * This class also supports integration with a {@link Zakatcalculator} for zakat computations.
 *
 * <p>Implements the Singleton design pattern — one portfolio per user session.</p>
 */
class Portfolio implements Serializable {
    private static Portfolio port;
    private static String username;
    private List<Asset> assets;
    private File FILE_NAME;
    String uSername;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Zakatcalculator zakatcalculator;
    /**
     * Private constructor to initialize the portfolio for a specific user.
     * Loads assets from a file or creates a new portfolio if the file doesn't exist.
     *
     * @param usernameKey The user's email used to derive a unique file name.
     */
    private Portfolio(String usernameKey) {
        username = usernameKey.replaceAll("@.*", "");
        zakatcalculator = new Zakatcalculator(username);
        FILE_NAME = new File(username + "_portfolio.txt");
        assets = loadAssets();
    }
    /**
     * Saves the current list of assets to the user's portfolio file.
     */
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
            System.out.println("Error saving portfolio: " + var6.getMessage());
        }

    }
    /**
     * Loads the list of assets from the user's portfolio file.
     *
     * @return A list of {@link Asset} objects; empty list if file not found or error occurs.
     */
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
    /**
     * Returns the singleton instance of the portfolio for the specified user.
     * If it doesn't exist, it is created.
     *
     * @param usernameKey The email or identifier of the user.
     * @return The singleton {@code Portfolio} instance.
     */
    public static Portfolio getInstance(String usernameKey) {
        if (port == null) {
            port = new Portfolio(usernameKey);
        }

        return port;
    }

    /**
     * Returns a list of the current user's assets.
     *
     * @return A copy of the list of {@link Asset} objects.
     */
    public List<Asset> getAssets() {
        return new ArrayList(this.assets);
    }
    /**
     * Adds an asset to the portfolio and persists the updated list.
     *
     * @param asset The {@link Asset} to add.
     */
    public void addAsset(Asset asset) {
        this.assets.add(asset);
        this.saveAssets();
        System.out.println("Asset added successfully.");
    }
    /**
     * Removes an asset from the portfolio and updates the saved file.
     *
     * @param asset The {@link Asset} to remove.
     */
    public void removeAsset(Asset asset) {
        this.assets.remove(asset);
        this.saveAssets();
        System.out.println("Asset removed successfully.");
    }

//   public void editAsset(int index, Asset newAsset) {
//      this.assets.set(index, newAsset);
//     this.saveAssets();
//  }

    /**
     * Edits an existing asset in the portfolio by allowing the user to update its fields interactively.
     *
     * @param index The index of the asset to edit.
     */
public void editAsset(int index, Asset asset) {
    if (index < 0 || index >= assets.size()) {
        System.out.println("Invalid index.");
        return;
    }
    asset = assets.get(index);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Editing Asset: " + asset.getAssetName());

    System.out.print("Enter new name: ");
    String name = scanner.nextLine();
    if (!name.isEmpty()) asset.setAssetName(name);

    System.out.print("Enter new quantity : ");
    String qtyStr = scanner.nextLine();
    if (!qtyStr.isEmpty()) asset.setQuantity(Float.parseFloat(qtyStr));

    System.out.print("Enter new purchase price : ");
    String priceStr = scanner.nextLine();
    if (!priceStr.isEmpty()) asset.setPurchasePrice(Float.parseFloat(priceStr));

    System.out.print("Enter new purchase date (d-MM-yyyy): ");
    String date = scanner.nextLine();
    if (!date.isEmpty()) {
        try {
            Date newDate = new SimpleDateFormat("d-MM-yyyy").parse(date);
            if (newDate.after(new Date())) {
                System.out.println("Invalid Purchase date as Purchase date can't be in the future.");
            } else {
                asset.setPurchaseDate(newDate);
            }
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }
    }
    if (asset instanceof RealEstate) {
        RealEstate real = (RealEstate) asset;
         real.setType();
    }

    if (asset instanceof Gold) {
        Gold gold = (Gold) asset;
         gold.setType();
        System.out.print("Enter new grams : ");
        String grams = scanner.nextLine();
        if (!grams.isEmpty()) gold.setAssetamount(Integer.parseInt(grams));
    }

    saveAssets();
    System.out.println("Asset updated successfully.");
}



    /**
     * Returns the username associated with the current portfolio.
     *
     * @return The username derived from the email prefix.
     */
    public static String getUsername() {
        return username;
    }
    /**
     * Returns the {@link Zakatcalculator} instance associated with this portfolio.
     *
     * @return A {@code Zakatcalculator} used for zakat computations.
     */
    public Zakatcalculator getZakatcalculator() {
        return zakatcalculator;
    }
}
