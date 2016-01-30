package jdbi;

import jdbi.mappers.PurchasedItemMapper;
import model.CompletedPurchase;
import model.PurchasedItem;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface PurchaseDao {


    @Mapper(PurchasedItemMapper.class)
    @SqlQuery("SELECT top 10 KioskID, KioskDate, ProduktNamn FROM Kiosk join Produkter on KioskEAN=ProduktEAN where KioskLan=10 order by KioskDate desc, ProduktNamn desc")
    List<PurchasedItem> getPurchaseHistory();
}
