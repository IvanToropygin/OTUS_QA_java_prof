package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.popup.AuthPopup;
import org.openqa.selenium.WebDriver;

public class PopupGuiceModule extends AbstractModule {

  private final WebDriver driver;

  public PopupGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public AuthPopup getAuthPopup() {
    return new AuthPopup(driver);
  }
}
