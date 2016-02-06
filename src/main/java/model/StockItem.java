package model;

public class StockItem {

    private String id;
    public String name;
    public int price;
    public String ean;
    public int ammountSold;
    public String category;

    public StockItem() {
    }

    public StockItem(String id, String name, int price, String ean, int ammountSold, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ean = ean;
        this.ammountSold = ammountSold;
        this.category = category;
    }
}
