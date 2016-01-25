package model;

import java.util.List;

/**
 * Created by Mikael on 2016-01-25.
 */
public class Purchase {
    public List<StockItem> items;

    public Purchase(List<StockItem> items) {
        this.items = items;
    }
}
