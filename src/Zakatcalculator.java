import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * The {@code Zakatcalculator} class calculates Zakat for a user's investment portfolio
 * and generates/downloads a detailed report based on Islamic financial principles.
 *
 * <p>It supports calculating Zakat only on specific asset types that qualify,
 * like Investment Gold, Investment Real Estate, and Stocks that have been held
 * for at least one lunar year (approx. 354 days).</p>
 *
 * <p>This class implements the {@link ReportService} interface.</p>
 */
public class Zakatcalculator implements ReportService{
    private String username;
    private double nisab = 0.025;
    /**
     * Constructs a Zakatcalculator for a specific user.
     * @param username the username for whom Zakat is to be calculated
     */
    public Zakatcalculator(String username) {
        this.username = username;
    }
    /**
     * Calculates the total Zakat due based on qualifying assets
     * that have been held for one full lunar year.
     *
     * @return the total Zakat amount
     */
    public double calculateZakat() {
        Portfolio portfolio = Portfolio.getInstance(username);
        List<Asset> assets = portfolio.getAssets();
        double totalZakat = 0.0;
        double goldValue = 0.0;
        int goldQuantity = 0;
        if (assets.size() == 0) {
            System.out.println("No assets found");
            return 1;
        }
        for (Asset asset : assets) {
            if ((asset.getAssetType().equals("Investment Gold") || asset.getAssetType().equals("Investment RealEstate") || asset.getAssetType().equals("Stock")) && isOneYearPassed(asset.getPurchaseDate())) {
                if (asset.getAssetType().equals("Investment Gold")) {
                    goldQuantity += asset.getAssetamount();
                    goldValue += asset.getQuantity() * asset.getPurchasePrice();
                } else {
                    totalZakat += asset.getQuantity() * asset.getPurchasePrice() * nisab;
                }
            }
        }
        if (goldQuantity >= 85 ) {
            totalZakat += goldValue * nisab;
        }

        System.out.println("Zakah calculated successfully.");
        return totalZakat;
    }
    /**
     * Checks if the asset has been held for at least 354 days (approx. one lunar year).
     *
     * @param purchaseDate the purchase date of the asset
     * @return {@code true} if one year has passed, otherwise {@code false}
     */
    private boolean isOneYearPassed(Date purchaseDate) {
        long currentTime = new Date().getTime();
        long purchaseTime = purchaseDate.getTime();
        long dif = currentTime - purchaseTime;
        long days = TimeUnit.MILLISECONDS.toDays(dif);
        return days >= 354;
    }
    /**
     * Calculates the number of days passed since asset purchase.
     *
     * @param purchaseDate the date the asset was purchased
     * @return number of days since purchase
     */
    private long getDaysSincePurchase(Date purchaseDate) {
        long currentTime = new Date().getTime();
        long purchaseTime = purchaseDate.getTime();
        long dif= currentTime - purchaseTime;
        return TimeUnit.MILLISECONDS.toDays(dif);
    }
    /**
     * Generates a text file report summarizing Zakat details for the user,
     * indicating whether each asset qualifies for Zakat and the reason why.
     *
     * @param username the username for whom to generate the report
     */

    public void generateReport(String username) {
        Portfolio portfolio = Portfolio.getInstance(username);
        List<Asset> assets = portfolio.getAssets();
        double zakat = calculateZakat();
        String reportContent = "Zakat Report for user: " + username + "\n"
                + "-------------------------------------\n"
                + "Total Zakat Due: " + zakat + " units\n";

        try (FileWriter writer = new FileWriter(username + "_zakat_report.txt"))
        {writer.write(reportContent);
            writer.write(System.lineSeparator());
            int goldQuantity = 0;
            for (Asset asset : assets) {
                long daysSincePurchase = getDaysSincePurchase(asset.getPurchaseDate());
                if ((asset.getAssetType().equals("Investment Gold") || asset.getAssetType().equals("Investment RealEstate") || asset.getAssetType().equals("Stock"))) {
                    if (isOneYearPassed(asset.getPurchaseDate())) {
                        writer.write(asset.getAssetName() + " Zakah calculated from it (owned for " + daysSincePurchase + " days)\n");
                    } else {
                        long daysLeft = 354 - daysSincePurchase;
                        writer.write(asset.getAssetName() + " Zakah not due yet (still " + daysLeft + " days left)\n");
                    }
                    if (asset.getAssetType().equals("Investment Gold")) {
                        goldQuantity += asset.getAssetamount();
                    }
                } else {
                    writer.write(asset.getAssetName() + " Zakah can't be calculated from it as it's owned not for investment.\n");
                }
            }
            if (goldQuantity >= 85) {
                writer.write("Gold total: " + goldQuantity + "g - Zakat applicable \n");
            } else {
                writer.write("Gold total: " + goldQuantity + "g - Below Nisab\n");
            }

            System.out.println("Zakat report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
    /**
     * Copies the generated Zakat report to the user's default Downloads directory.
     *
     * @param username the username whose report is to be downloaded
     */
    @Override
    public void downloadReport(String username) {
        String filename = username + "_zakat_report.txt";
        File originalFile = new File(filename);
        File downloadDir = new File(System.getProperty("user.home") + "/Downloads");
        File destFile = new File(downloadDir, filename);

        if (originalFile.exists()) {
            try {
                java.nio.file.Files.copy(originalFile.toPath(), destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Report downloaded to: " + destFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error downloading file: " + e.getMessage());
            }
        } else {
            System.out.println("Report not found. Please generate it first.");
        }
    }
}
