package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.ComponentGuiceModule;
import modules.PageGuiceModule;
import modules.PopupGuiceModule;
import modules.UtilsModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

  @Override
  public void beforeEach(ExtensionContext context) {
    WebDriver driver = new WebDriverFactory().get();
    DRIVER.set(driver);

    Injector injector = Guice.createInjector(
        new PageGuiceModule(driver),
        new ComponentGuiceModule(driver),
        new PopupGuiceModule(driver),
        new UtilsModule()
    );
    context.getTestInstance().ifPresent(injector::injectMembers);
  }

  @Override
  public void afterEach(ExtensionContext context) {
    WebDriver driver = DRIVER.get();
    if (driver != null) {
      driver.quit();
    }
  }
}
