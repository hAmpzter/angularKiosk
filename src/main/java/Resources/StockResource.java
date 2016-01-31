package Resources;


import com.google.common.collect.Lists;
import jdbi.StockDAO;
import model.Category;
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


    private StockDAO stockDAO;

    public StockResource(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    @GET
    public List<StockItem> getCurrentStock() {
        return stockDAO.getStock();

    }

    @GET
    @Path("/categories")
    public List<Category> getCategories() {
        return stockDAO.getCategories();

    }

}
