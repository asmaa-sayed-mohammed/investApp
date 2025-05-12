/**
 * The {@code AssetService} interface defines the contract for managing a collection of {@link Asset} objects.
 * It includes methods for adding, editing, and removing assets, as well as validating asset indices.
 */
public interface AssetService {

    /**
     * Adds a new asset to the collection.
     *
     * @param var1 The {@link Asset} object to be added.
     */
    void addAsset(Asset var1);

    /**
     * Edits an existing asset at the specified index.
     *
     * @param var1 The index of the asset to be edited.
     */
    void editAsset(int var1);

    /**
     * Removes an asset from the collection based on the provided index.
     *
     * @param var1 The index of the asset to be removed.
     */
    void removeAsset(int var1);

    /**
     * Checks if the provided index is valid for the asset list.
     *
     * @param var1 The index to validate.
     * @return {@code true} if the index is valid; {@code false} otherwise.
     */

    boolean isValidIndex(int var1);
}
