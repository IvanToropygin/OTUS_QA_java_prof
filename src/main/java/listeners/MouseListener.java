package listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

public class MouseListener implements WebDriverListener {

  private WebElement lastHighlighted;

  @Override
  public void beforeClick(WebElement element) {
    WebDriver driver = getDriverFromElement(element);

    try {
      if (lastHighlighted != null) {
        resetBorder(lastHighlighted, driver);
      }
    } catch (StaleElementReferenceException ignored) {
      // Элемент больше не существует в DOM
    }

    highlight(element, driver);
    lastHighlighted = element;
  }

  private WebDriver getDriverFromElement(WebElement element) {
    return ((WrapsDriver) element).getWrappedDriver();
  }

  private void highlight(WebElement element, WebDriver driver) {
    try {
      ((JavascriptExecutor) driver).executeScript(
          "arguments[0].style.border='3px solid red';",
          element
      );
    } catch (StaleElementReferenceException | JavascriptException e) {
      // Игнорируем ошибки при выделении
    }
  }

  private void resetBorder(WebElement element, WebDriver driver) {
    try {
      ((JavascriptExecutor) driver).executeScript(
          "arguments[0].style.border='';",
          element
      );
    } catch (StaleElementReferenceException | JavascriptException e) {
      // Игнорируем ошибки при сбросе
    }
  }
}
