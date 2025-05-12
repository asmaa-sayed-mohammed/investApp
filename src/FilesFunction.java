import java.util.List;
/**
 * The {@code FilesFunction} interface defines methods for saving to and reading from a file.
 * It is typically implemented by classes that handle file-based storage and retrieval of structured data.
 */
public interface FilesFunction {
     /**
      * Saves data to a file.
      * Implementing classes should define how and where the data is saved.
      */
     void saveToFile();
     /**
      * Reads data from a file and returns it as a list of string arrays.
      * Each array typically represents a row of data, with individual fields as elements.
      *
      * @return A {@code List} of {@code String[]} arrays containing the file's data.
      */
     List<String[]> readFromFile();
}
