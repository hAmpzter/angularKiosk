import Resources.StockResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import static org.eclipse.jetty.servlets.CrossOriginFilter.*;

public class KioskApplication extends Application<KioskConfiguration> {
    public static void main(String[] args) throws Exception {
        new KioskApplication().run(args);
    }

    @Override
    public void run(KioskConfiguration config, Environment environment) throws Exception {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);

        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, environment.getApplicationContext().getContextPath() + "*");
        filter.setInitParameter(ALLOWED_METHODS_PARAM, "GET,PUT,POST,OPTIONS");
        filter.setInitParameter(ALLOWED_HEADERS_PARAM, "Origin, Content-Type, Accept");
        filter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");
        final StockResource stockResource = new StockResource();
        environment.jersey().register(stockResource);
    }
}
