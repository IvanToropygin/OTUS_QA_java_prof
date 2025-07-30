package pages;

import annotations.Path;
import commons.AbsCommon;
import exceptions.PathNotFoundException;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage<T> extends AbsCommon {

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String getPagePath() {
    Class<? extends AbsBasePage> clazz = this.getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      return clazz.getAnnotation(Path.class).value();
    }
    throw new PathNotFoundException(clazz);
  }

  public T open() {
    String baseUrl = System.getProperty("base.url", "https://otus.ru");
    driver.get(baseUrl + getPagePath());
    return (T) this;
  }
}
