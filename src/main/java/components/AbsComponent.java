package components;

import annotations.Component;
import commons.AbsCommon;
import exceptions.ComponentSelectorNotValidException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class AbsComponent extends AbsCommon {

  {
    assertThat(waiter.waitForElementVisible(getByComponent()))
        .as("Error").isTrue();
  }

  public AbsComponent(WebDriver driver) {
    super(driver);
  }

  private By getByComponent() {
    Class<?> clazz = getClass();
    if (clazz.isAnnotationPresent(Component.class)) {
      Component component = clazz.getDeclaredAnnotation(Component.class);
      String value = component.value();

      Pattern pattern = Pattern.compile("(.*?)->(.*?)");
      Matcher matcher = pattern.matcher(value);
      if (matcher.find()) {
        switch (matcher.group(1)) {
          case "css": {
            return By.cssSelector(matcher.group(2));
          }
          case "xpath": {
            return By.xpath(matcher.group(2));
          }
          case "id": {
            return By.id(matcher.group(2));
          }
        }
      }
    }
    throw new ComponentSelectorNotValidException();
  }

  public WebElement getComponentEntity() {
    return $(getByComponent());
  }
}
