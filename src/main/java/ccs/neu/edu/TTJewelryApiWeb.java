package ccs.neu.edu;

import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Properties;

public class TTJewelryApiWeb extends Application<TTJewelryWebConfiguration> {

  public static void main(String[] args) throws Exception {
    new TTJewelryApiWeb().run(args);
  }

  private Injector injector;

  @Override
  public void initialize(Bootstrap<TTJewelryWebConfiguration> bootstrap) {

    GuiceBundle<TTJewelryWebConfiguration> guiceBundle = GuiceBundle.<TTJewelryWebConfiguration>newBuilder()
        .addModule(new TTJewelryWebModule())
        .addModule(createJpaPersistModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .setConfigClass(TTJewelryWebConfiguration.class)
        .build();

    bootstrap.addBundle(guiceBundle);
    injector = guiceBundle.getInjector();
  }

  @Override
  public String getName() {
    return "t-t-jewelry-api-web";
  }

  @Override
  public void run(TTJewelryWebConfiguration TTJewelryWebConfiguration, Environment environment) throws Exception {
    environment.servlets().addFilter("DateHeaderServletFilter", injector.getInstance(PersistFilter.class))
        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
  }

  private JpaPersistModule createJpaPersistModule() {
    Properties props = new Properties();
    props.put("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1/hw7");
    props.put("javax.persistence.jdbc.user", "root");
    props.put("javax.persistence.jdbc.password", "Thanhcong123#");
    props.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
    JpaPersistModule jpaModule = new JpaPersistModule("Default");
    jpaModule.properties(props);
    return jpaModule;
  }
}

