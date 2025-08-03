package homework2.hooks;

import com.google.inject.Inject;
import factory.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import scoped.GuiceScoped;

public class Hooks {

  @Inject
  private GuiceScoped guiceScoped;

  @Before
  public void init() {
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    guiceScoped.driver = webDriverFactory.get();
  }

  @After
  public void close() {
    if (guiceScoped.driver != null) guiceScoped.driver.quit();
  }
}
