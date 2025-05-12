// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.List;
import java.util.Scanner;
/**
 * The {@code AssetServiceImpl} class provides an implementation of the {@link AssetService} interface.
 * It allows operations such as adding, editing, and removing assets from a user's {@link Portfolio}.
 */
class AssetServiceImpl implements AssetService {
    private Portfolio portfolio;
    private final List<Asset> assets;
    /**
     * Constructs an {@code AssetServiceImpl} with the given portfolio.
     *
     * @param portfolio The {@link Portfolio} object that holds a list of assets.
     */
    public AssetServiceImpl(Portfolio portfolio) {
        this.portfolio = portfolio;
        this.assets = portfolio.getAssets();
    }
    /**
     * Adds a new asset to the portfolio after validating it.
     *
     * @param asset The {@link Asset} to add.
     */
    public void addAsset(Asset asset) {
        if (this.isValidAsset(asset)) {
            this.portfolio.addAsset(asset);
        } else {
            System.out.println("Invalid asset.");
        }

    }

    /**
     * Removes an asset from the portfolio at the specified index.
     *
     * @param index The index of the asset to be removed.
     */
    public void removeAsset(int index) {
        this.portfolio.removeAsset((Asset)this.portfolio.getAssets().get(index));
    }
    /**
     * Edits an asset in the portfolio at the specified index.
     * The asset is validated before it is edited.
     *
     * @param index The index of the asset to be edited.
     */
    public void editAsset(int index) {
        Asset oldAsset = portfolio.getAssets().get(index);

        if (this.isValidAsset(oldAsset)) {
            this.portfolio.editAsset(index, oldAsset);
        } else {
            System.out.println("Invalid asset.");
        }
    }
    /**
     * Validates whether a given {@link Asset} object contains proper values.
     * Ensures the asset is non-null, has a non-empty name, positive quantity and price,
     * and a non-null purchase date.
     *
     * @param asset The asset to validate.
     * @return {@code true} if the asset is valid; {@code false} otherwise.
     */
    private boolean isValidAsset(Asset asset) {
        if (asset == null) {
            return false;
        } else if (asset.getAssetName() != null && !asset.getAssetName().isEmpty()) {
            if (asset.getQuantity() <= 0.0F) {
                return false;
            } else if (asset.getPurchasePrice() <= 0.0F) {
                return false;
            } else {
                return asset.getPurchaseDate() != null;
            }
        } else {
            return false;
        }
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < this.portfolio.getAssets().size();
    }
}
