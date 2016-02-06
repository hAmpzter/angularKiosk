package jdbi;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface SettingsDao {





    @SqlQuery("select SettingLan from SETTING")
    int getLanNumber();

}
