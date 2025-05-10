import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;


public class Zakatcalculator implements ReportService{
    private String username;

    public Zakatcalculator(String username) {
        this.username = username;
    }

    public double calculateZakat() {
        Portfolio portfolio = Portfolio.getInstance(username);
        List<Asset> assets = portfolio.getAssets();
        double totalZakat = 0.0;
        if (assets.size() == 0) {
            System.out.println("No assets found");
            return 1;
        }
        for (Asset asset : assets) {
            totalZakat += asset.getQuantity() * 0.025;
        }

        System.out.println("The Zakat you have to pay is "+totalZakat );
        return totalZakat;
    }
    public void generateReport(String username) {
        double zakat = calculateZakat();
        String reportContent = "Zakat Report for user: " + username + "\n"
                + "-------------------------------------\n"
                + "Total Zakat Due: " + zakat + " units\n";

        try (FileWriter writer = new FileWriter(username + "_zakat_report.txt")) {
            writer.write(reportContent);
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

