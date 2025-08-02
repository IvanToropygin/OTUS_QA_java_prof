package listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Objects;

public final class MouseListener implements WebDriverListener {
  private String lastHighlightedElementId;
  private By lastHighlightedElementLocator;
  private WebDriver lastUsedDriver;

  @Override
  public void beforeClick(WebElement element) {
    Objects.requireNonNull(element, "WebElement for click cannot be null");

    WebDriver driver = getDriverFromElement(element);
    Objects.requireNonNull(driver, "Driver extracted from WebElement cannot be null");

    this.lastUsedDriver = driver;

    try {
      resetLastHighlightedIfNeeded();
    } catch (StaleElementReferenceException ignored) {
      // Элемент больше не существует в DOM
    }

    highlightElement(element, driver);
    storeElementReference(element);
  }

  private void resetLastHighlightedIfNeeded() {
    if (lastHighlightedElementId != null && lastUsedDriver != null) {
      try {
        WebElement lastElement = lastUsedDriver.findElement(By.id(lastHighlightedElementId));
        executeBorderReset(lastElement, lastUsedDriver);
      } catch (NoSuchElementException | StaleElementReferenceException ignored) {
        // Элемент больше не существует
      }
    }
  }

  private void highlightElement(WebElement element, WebDriver driver) {
    Objects.requireNonNull(element, "Element to highlight cannot be null");
    Objects.requireNonNull(driver, "Driver for highlighting cannot be null");

    try {
      executeBorderHighlight(element, driver);
    } catch (JavascriptException e) {
      System.err.println("Highlight failed: " + e.getMessage());
    }
  }

  private void executeBorderHighlight(WebElement element, WebDriver driver) {
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].style.border='3px solid red';",
        element
    );
  }

  private void executeBorderReset(WebElement element, WebDriver driver) {
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].style.border='';",
        element
    );
  }

  private void storeElementReference(WebElement element) {
    this.lastHighlightedElementId = element.getAttribute("id");
    this.lastHighlightedElementLocator = buildElementLocator(element);
  }

  private By buildElementLocator(WebElement element) {
    String id = element.getAttribute("id");
    return (id != null && !id.isEmpty())
        ? By.id(id)
        : buildXPathLocator(element);
  }

  private By buildXPathLocator(WebElement element) {
    return By.xpath(".//*[text()='" + element.getText() + "']");
  }

  private WebDriver getDriverFromElement(WebElement element) {
    return ((WrapsDriver) element).getWrappedDriver();
  }
}
