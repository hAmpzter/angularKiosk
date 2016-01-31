package model;

import java.util.List;

public class CompletedPurchase {
    public List<PurchasedItem> items;
    public int purchaseId;

    public CompletedPurchase() {
    }

    public CompletedPurchase(List<PurchasedItem> items, int purchaseId) {
        this.items = items;
        this.purchaseId = purchaseId;
    }
}
