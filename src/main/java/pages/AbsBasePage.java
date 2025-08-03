package pages;

import annotations.Path;
import com.google.inject.Inject;
import commons.AbsCommon;
import exceptions.PathNotFoundException;
import scoped.GuiceScoped;

public abstract class AbsBasePage<T> extends AbsCommon {

  @Inject
  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
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
