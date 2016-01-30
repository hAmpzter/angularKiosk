package model;

public class StockItem {

    public String name;
    public int price;
    public String ean;
    public int ammountSold;
    public String category;

    public StockItem(String name, int price, String ean, int ammountSold, String category) {
        this.name = name;
        this.price = price;
        this.ean = ean;
        this.ammountSold = ammountSold;
        this.category = category;
    }
}
