package jdbi;

import model.PurchasedItem;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.List;

public interface LoginDao {


    @SqlQuery("select userUserName from Users where userUserName=:username and UserPassword like :password")
    String login(@Bind("username") String username, @Bind("password") String password);
}
