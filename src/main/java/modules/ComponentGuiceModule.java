package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.ProductSlider;
import org.openqa.selenium.WebDriver;

public class ComponentGuiceModule extends AbstractModule {

  private final WebDriver driver;

  public ComponentGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public ProductSlider getProductSlider() {
    return new ProductSlider(driver);
  }
}
