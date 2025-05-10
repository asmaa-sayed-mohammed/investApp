import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Zakatcalculator implements ReportService{
    private String username;
    private double nisab = 0.025;

    public Zakatcalculator(String username) {
        this.username = username;
    }

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
    private boolean isOneYearPassed(Date purchaseDate) {
        long currentTime = new Date().getTime();
        long purchaseTime = purchaseDate.getTime();
        long dif = currentTime - purchaseTime;
        long days = TimeUnit.MILLISECONDS.toDays(dif);
        return days >= 354;
    }
    private long getDaysSincePurchase(Date purchaseDate) {
        long currentTime = new Date().getTime();
        long purchaseTime = purchaseDate.getTime();
        long dif= currentTime - purchaseTime;
        return TimeUnit.MILLISECONDS.toDays(dif);
    }

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
                    writer.write(asset.getAssetName() + " - Zakah can't be calculated from it as it's owned not for investment.\n");
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
