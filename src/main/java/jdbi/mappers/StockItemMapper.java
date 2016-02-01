package jdbi.mappers;

import model.StockItem;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockItemMapper implements ResultSetMapper<StockItem> {
    @Override
    public StockItem map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new StockItem(resultSet.getString("ProduktID"), resultSet.getString("ProduktNamn"), resultSet.getInt("ProduktPris"), resultSet.getString("ProduktEAN"), 0,resultSet.getString("ProduktKategori"));
    }
}
