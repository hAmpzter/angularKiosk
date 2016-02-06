package model;

import java.util.List;

public class Purchase {
    public List<StockItem> items;

    public Purchase() {
    }

    public Purchase(List<StockItem> items) {
        this.items = items;
    }
}
