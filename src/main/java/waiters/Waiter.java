package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class Waiter {
  private final WebDriver driver;
  private final Duration timeout;

  public Waiter(WebDriver driver) {
    this(driver, Integer.parseInt(System.getProperty("waiter.timeout", "2")));
  }

  public Waiter(WebDriver driver, int timeoutInSeconds) {
    if (driver == null) {
      throw new IllegalArgumentException("WebDriver cannot be null");
    }
    this.driver = driver;
    this.timeout = Duration.ofSeconds(timeoutInSeconds);
  }

  public boolean waitForCondition(ExpectedCondition<?> condition) {
    try {
      new WebDriverWait(driver, timeout).until(condition);
      return true;
    } catch (TimeoutException ignored) {
      return false;
    }
  }

  public boolean waitForElementVisible(By locator) {
    return waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public boolean waitForElementNotVisible(By locator) {
    return waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
  }
}
