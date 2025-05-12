// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.List;
import java.util.Scanner;
class AssetServiceImpl implements AssetService {
    private Portfolio portfolio;
    private final List<Asset> assets;

    public AssetServiceImpl(Portfolio portfolio) {
        this.portfolio = portfolio;
        this.assets = portfolio.getAssets();
    }

    public void addAsset(Asset asset) {
        if (this.isValidAsset(asset)) {
            this.portfolio.addAsset(asset);
        } else {
            System.out.println("Invalid asset.");
        }

    }

    public void removeAsset(int index) {
        this.portfolio.removeAsset((Asset)this.portfolio.getAssets().get(index));
    }
    public void editAsset(int index) {
        Asset oldAsset = portfolio.getAssets().get(index);

        if (this.isValidAsset(oldAsset)) {
            this.portfolio.editAsset(index, oldAsset);
        } else {
            System.out.println("Invalid asset.");
        }
    }

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
