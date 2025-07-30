package extensions;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.ComponentGuiceModule;
import modules.PageGuiceModule;
import modules.PopupGuiceModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private Injector injector;

  @Override
  public void beforeEach(ExtensionContext context) {
    WebDriver driver = new WebDriverFactory().get();

    injector = Guice.createInjector(
        new AbstractModule() {
          @Override
          protected void configure() {
            bind(WebDriver.class).toInstance(driver);
          }
        },
        new PageGuiceModule(driver),
        new ComponentGuiceModule(driver),
        new PopupGuiceModule(driver)
    );

    context.getTestInstance().ifPresent(injector::injectMembers);
  }

  @Override
  public void afterEach(ExtensionContext context) {
    if (injector != null) {
      WebDriver driver = injector.getInstance(WebDriver.class);
      if (driver != null) {
        driver.quit();
      }
    }
  }
}
