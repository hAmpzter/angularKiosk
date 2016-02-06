import Resources.LoginResource;
import Resources.PurchaseResource;
import Resources.StockResource;
import auth.KioskAuthenticator;
import com.codahale.metrics.MetricRegistry;
import com.github.toastshaman.dropwizard.auth.jwt.JWTAuthFilter;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenParser;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Verifier;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.parser.DefaultJsonWebTokenParser;
import com.google.common.cache.CacheBuilder;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jdbi.LoginDao;
import jdbi.PurchaseDao;
import jdbi.SettingsDao;
import jdbi.StockDAO;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.security.Principal;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static org.eclipse.jetty.servlets.CrossOriginFilter.*;

public class KioskApplication extends Application<KioskConfiguration> {
    public static void main(String[] args) throws Exception {
        new KioskApplication().run(args);
    }
    @Override
    public void initialize(Bootstrap<KioskConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/web/dist", "/static/"));
    }
    @Override
    public void run(KioskConfiguration config, Environment environment) throws Exception {
        enableCORS(environment);


        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mssql");
        StockDAO stockDAO = jdbi.onDemand(StockDAO.class);
        PurchaseDao purchaseDao = jdbi.onDemand(PurchaseDao.class);
        LoginDao loginDao = jdbi.onDemand(LoginDao.class);
        SettingsDao settingsDao = jdbi.onDemand(SettingsDao.class);

        environment.jersey().register(new StockResource(stockDAO, purchaseDao, settingsDao));
        environment.jersey().register(new PurchaseResource(purchaseDao, settingsDao));
        environment.jersey().register(new LoginResource(config.getJwtTokenSecret(), loginDao));

        addAuthFilter(environment, config, loginDao);

    }

    private void addAuthFilter(Environment environment, KioskConfiguration configuration, LoginDao loginDao) {
        final JsonWebTokenParser tokenParser = new DefaultJsonWebTokenParser();
        final HmacSHA512Verifier tokenVerifier = new HmacSHA512Verifier(configuration.getJwtTokenSecret());
        environment.jersey().register(new AuthDynamicFeature(
                new JWTAuthFilter.Builder<>()
                        .setTokenParser(tokenParser)
                        .setTokenVerifier(tokenVerifier)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        .setAuthenticator(new CachingAuthenticator<>(new MetricRegistry(), new KioskAuthenticator(loginDao), CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES)))
                        .buildAuthFilter()));
    }

    private void enableCORS(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);

        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, environment.getApplicationContext().getContextPath() + "*");
        filter.setInitParameter(ALLOWED_METHODS_PARAM, "GET,PUT,DELETE,POST,OPTIONS");
        filter.setInitParameter(ALLOWED_HEADERS_PARAM, "Origin, Content-Type, Accept, jwt, Authorization");
        filter.setInitParameter(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "http://localhost:9000");
        filter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");
        filter.setInitParameter(ALLOWED_METHODS_PARAM, "GET,PUT,POST,OPTIONS");
        filter.setInitParameter(ALLOWED_HEADERS_PARAM, "Origin, Content-Type, Accept");
        filter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");
    }
}
