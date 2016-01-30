package model;

public class PurchasedItem {
    public int itemPurchaseId;
    public long purchaseId;
    public String name;

    public PurchasedItem() {
    }

    public PurchasedItem(int itemPurchaseId,long purchaseId, String name) {
        this.itemPurchaseId = itemPurchaseId;
        this.purchaseId = purchaseId;
        this.name = name;
    }
}
