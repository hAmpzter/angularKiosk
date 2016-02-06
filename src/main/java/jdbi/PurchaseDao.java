package jdbi;

import jdbi.mappers.PurchasedItemMapper;
import model.CompletedPurchase;
import model.PurchasedItem;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Timestamp;
import java.util.List;

public interface PurchaseDao {


    @Mapper(PurchasedItemMapper.class)
    @SqlQuery("SELECT top 10 KioskID, KioskDate, ProduktNamn FROM Kiosk join Produkter on KioskEAN=ProduktEAN where KioskLan=:lanNumber order by KioskDate desc, ProduktNamn desc")
    List<PurchasedItem> getPurchaseHistory(@Bind("lanNumber") int lanNumber);


    @SqlUpdate("INSERT INTO KIOSK (KioskEAN, KioskDate, KioskUser,KioskLan) values (:ean,:date,:user,:lan)")
    void completePurchase(@Bind("ean") String ean, @Bind("date") Timestamp timestamp,@Bind("user") String user, @Bind("lan") int lan);

}
