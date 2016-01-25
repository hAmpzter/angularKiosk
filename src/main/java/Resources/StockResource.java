package Resources;


import com.google.common.collect.Lists;
import model.Category;
import model.StockItem;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
public class StockResource {


    public StockResource() {
    }

    @GET
    public List<StockItem> getCurrentStock() {
        StockItem stockItem = new StockItem("Cola", 5, "1234", 12, "Soda");
        return Lists.newArrayList(stockItem);

    }

    @GET
    @Path("/categories")
    public List<Category> getCategories() {
        return Lists.newArrayList(new Category("Soda"));

    }
}
