package jdbi;

import jdbi.mappers.CategoryMapper;
import jdbi.mappers.StockItemMapper;
import model.Category;
import model.StockItem;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface StockDAO  {

    @Mapper(StockItemMapper.class)
    @SqlQuery("select ProduktID,ProduktNamn,ProduktPris,ProduktEAN,ProduktKategori FROM Produkter")
    List<StockItem> getStock();

    @Mapper(StockItemMapper.class)
    @SqlQuery("select ProduktID,ProduktNamn,ProduktPris,ProduktEAN,ProduktKategori FROM Produkter where ProduktKategori = :category")
    List<StockItem> getStock(@Bind("category") String category);

    @Mapper(CategoryMapper.class)
    @SqlQuery("Select distinct ProduktKategori FROM Produkter")
    List<Category> getCategories();


}
