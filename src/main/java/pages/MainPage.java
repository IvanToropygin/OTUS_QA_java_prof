package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import scoped.GuiceScoped;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  //статический локатор
  @FindBy(tagName = "h1")
  private WebElement header;
  //динамический локатор
  private String headerEvent = "//*[@class='header']";

  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public MainPage clickHeaderEvent() {
    $(By.xpath(headerEvent)).click();
    return this;
  }

  //шаблонизированный локатор
  public MainPage clickHeaderTemplate(String... data) {
    $(By.xpath(String.format("//*[@class='%s']", data[0]))).click();
    return this;
  }
}
