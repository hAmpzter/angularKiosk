package Resources;

import auth.User;
import jdbi.PurchaseDao;
import jdbi.SettingsDao;
import model.Purchase;
import model.PurchasedItem;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    PurchaseDao purchaseDao;
    int lanNumber;
    public PurchaseResource(PurchaseDao purchaseDao, SettingsDao settingsDao) {
        this.purchaseDao = purchaseDao;
        lanNumber = settingsDao.getLanNumber();
    }

    @POST
    @PermitAll
    public Response purchase(Purchase purchase) {
        purchase.items.forEach(item -> purchaseDao.completePurchase(item.ean,new Timestamp(new Date().getTime()),null,lanNumber ));
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @PermitAll
    @Path("/history")
    public List<PurchasedItem> getPurchases() {

        return purchaseDao.getPurchaseHistory(this.lanNumber);
    }

    @DELETE
    @PermitAll
    @Path("/{purchaseId}")
    public Response deletePurchase(@PathParam("purchaseId") String purchaseId) {
        System.out.println("purchase with id deleted:"+ purchaseId);
        return Response.ok().build();
    }
    @DELETE
    @PermitAll
    @Path("/{purchaseId}/{itemId}")
    public Response deletePurchase(@PathParam("purchaseId") String purchaseId, @PathParam("itemId") String itemId) {
        System.out.println("item removed from purchase:"+ itemId);
        return Response.ok().build();
    }
}
