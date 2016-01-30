package jdbi.mappers;

import model.Category;
import model.StockItem;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements ResultSetMapper<Category> {


    @Override
    public Category map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Category(resultSet.getString("ProduktKategori"));
    }
}
