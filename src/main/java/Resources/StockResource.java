package Resources;


import com.google.common.collect.Lists;
<<<<<<< HEAD
import jdbi.StockDAO;
import model.Category;
=======
>>>>>>> parent of 61ce471... added more functionality
import model.StockItem;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)

public class StockResource {


<<<<<<< HEAD
    private StockDAO stockDAO;

    public StockResource(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }
=======
    public StockResource() {
}
>>>>>>> parent of 61ce471... added more functionality

    @GET
    public List<StockItem> getCurrentStock() {
        return stockDAO.getStock();
        //StockItem stockItem = new StockItem("Cola", 5, "1234", 12, "Soda");
        //return Lists.newArrayList(stockItem);

    }
<<<<<<< HEAD

    @GET
    @Path("/categories")
    public List<Category> getCategories() {
        return stockDAO.getCategories();

    }
=======
>>>>>>> parent of 61ce471... added more functionality
}
