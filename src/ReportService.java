/**
 * The {@code ReportService} interface defines the contract for generating and downloading
 * reports related to a user's financial or asset data.
 *
 * <p>Implementing classes are expected to handle user-specific reporting logic,
 * such as creating summaries, exporting files, and presenting data in a readable format.</p>
 */
public interface ReportService {
    /**
     * Generates a report for the specified user.
     * This method should compile and format relevant user data into a report.
     *
     * @param username The username or unique identifier of the user whose report is to be generated.
     */
    void generateReport(String username);
    /**
     * Downloads the generated report for the specified user.
     * Implementations may export the report to a file or other storage location.
     *
     * @param username The username or unique identifier of the user whose report is to be downloaded.
     */
    void downloadReport(String username);
}
