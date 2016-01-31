package Resources;

import jdbi.PurchaseDao;
import model.Purchase;
import model.PurchasedItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    PurchaseDao purchaseDao;

    public PurchaseResource(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @POST
    public Response purchase(Purchase purchase) {
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/history")
    public List<PurchasedItem> getPurchases() {

        return purchaseDao.getPurchaseHistory();
        //return Lists.newArrayList(new CompletedPurchase(Lists.newArrayList(new PurchasedItem(12, "Cola"), new PurchasedItem(32, "Fanta")), 321));
    }

    @DELETE
    @Path("/{purchaseId}")
    public Response deletePurchase(@PathParam("purchaseId") String purchaseId) {
        System.out.println("purchase with id deleted:"+ purchaseId);
        return Response.ok().build();
    }
    @DELETE
    @Path("/{purchaseId}/{itemId}")
    public Response deletePurchase(@PathParam("purchaseId") String purchaseId, @PathParam("itemId") String itemId) {
        System.out.println("item removed from purchase:"+ itemId);
        return Response.ok().build();
    }
}
