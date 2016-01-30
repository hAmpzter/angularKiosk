package jdbi.mappers;

import model.CompletedPurchase;
import model.PurchasedItem;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchasedItemMapper implements ResultSetMapper<PurchasedItem> {
    @Override
    public PurchasedItem map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new PurchasedItem(resultSet.getInt("KioskID"),resultSet.getTimestamp("KioskDate").getTime(),resultSet.getString("ProduktNamn") );
         //SELECT KioskID, KioskDate, ProduktNamn FROM Kiosk join Produkter on KioskEAN=ProduktEAN where KioskLan=? order by KioskDate desc, ProduktNamn desc
    }
}
