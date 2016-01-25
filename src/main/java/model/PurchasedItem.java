package model;

public class PurchasedItem {
    public int itemPurchaseId;
    public String name;

    public PurchasedItem() {
    }

    public PurchasedItem(int itemPurchaseId, String name) {
        this.itemPurchaseId = itemPurchaseId;
        this.name = name;
    }
}
