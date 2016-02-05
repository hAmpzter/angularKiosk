package Resources;


import com.google.common.collect.Lists;
import jdbi.StockDAO;
import model.Category;
import model.StockItem;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @PermitAll
    public List<StockItem> getCurrentStock() {
        return stockDAO.getStock();
    }

    @GET
    @PermitAll
    @Path("/categories")
    public List<Category> getCategories() {
        return stockDAO.getCategories();

    }
    @GET
    @PermitAll
    @Path("/categories/{category}/stock")
    public List<StockItem> getCategories(@PathParam("category") String category) {
        return stockDAO.getStock(category);

    }

}
