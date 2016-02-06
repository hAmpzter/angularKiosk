package Resources;


import com.google.common.collect.Lists;
import jdbi.PurchaseDao;
import jdbi.SettingsDao;
import jdbi.StockDAO;
import model.Category;
import model.PurchasedItem;
import model.StockItem;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)

public class StockResource {


    private StockDAO stockDAO;
    private PurchaseDao purchaseDao;
    private int lanNumber;

    public StockResource(StockDAO stockDAO, PurchaseDao purchaseDao, SettingsDao settingsDao) {
        this.stockDAO = stockDAO;
        this.purchaseDao = purchaseDao;
        lanNumber=settingsDao.getLanNumber();
    }

    @GET
    @PermitAll
    public List<StockItem> getCurrentStock() {
        return stockDAO.getStock();
    }

    @GET
    @PermitAll
    @Path("/top")
    public List<StockItem> getTopStock() {
        List<PurchasedItem> purchaseHistory = purchaseDao.getPurchaseHistory(lanNumber);
        Map<String, Long> purchaseCounts = purchaseHistory
                .stream()
                .collect(Collectors.groupingBy(p -> p.name, Collectors.counting()));
        List<StockItem> stock = stockDAO.getStock();
        stock.sort((o1, o2) -> purchaseCounts.getOrDefault(o2.name,0l).compareTo(purchaseCounts.getOrDefault(o1.name,0l)));
        return stock.subList(0,10);
    }


    @GET
    @PermitAll
    @Path("/categories")
    public List<Category> getCategories() {
        return stockDAO.getCategories().stream().filter(c -> c.name != null).collect(Collectors.toList());

    }

    @GET
    @PermitAll
    @Path("/categories/{category}/stock")
    public List<StockItem> getCategories(@PathParam("category") String category) {
        return stockDAO.getStock(category);

    }

}
