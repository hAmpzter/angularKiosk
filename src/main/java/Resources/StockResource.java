package Resources;


import com.google.common.collect.Lists;
import model.StockItem;

import javax.ws.rs.GET;
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
}
