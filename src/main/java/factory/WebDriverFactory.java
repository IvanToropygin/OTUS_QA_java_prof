package factory;

import com.google.inject.Provider;
import exceptions.WebdriverNotValidException;
import factory.settings.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.MouseListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.time.Duration;
import java.util.Locale;

public class WebDriverFactory implements Provider<WebDriver> {

  private final String browserName =
      System.getProperty("browser.name", "chrome")
          .toLowerCase(Locale.ROOT).trim();

  @Override
  public WebDriver get() {
    WebDriver baseDriver = createBaseDriver();
    baseDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    return decorateWithListeners(baseDriver);
  }

  private WebDriver createBaseDriver() {
    return switch (browserName) {
      case "chrome" -> {
        WebDriverManager.chromedriver().setup();
        yield new ChromeDriver((ChromeOptions) new ChromeSettings().getSettings(new DesiredCapabilities()));
      }
      case "firefox" -> {
        WebDriverManager.firefoxdriver().setup();
        yield new FirefoxDriver();
      }
      default -> throw new WebdriverNotValidException(browserName);
    };
  }

  private WebDriver decorateWithListeners(WebDriver driver) {
    return new EventFiringDecorator<>(new MouseListener()).decorate(driver);
  }
}
