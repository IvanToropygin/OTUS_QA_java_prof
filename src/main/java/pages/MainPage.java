package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  //статический локатор
  @FindBy(tagName = "h1")
  private WebElement header;
  //динамический локатор
  private String headerEvent = "//*[@class='header']";

  public MainPage(WebDriver driver) {
    super(driver);
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
