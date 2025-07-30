package components.popup;


import commons.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthPopup extends AbsCommon implements IPopup<AuthPopup> {

  private final By popupBy = By.cssSelector(".auth");

  public AuthPopup(WebDriver driver) {
    super(driver);
  }

  @Override
  public AuthPopup popupShouldBeVisible() {
    assertThat(waiter.waitForElementVisible(popupBy)).as("Error").isTrue();
    return this;
  }

  @Override
  public AuthPopup popupShouldNotVisible() {
    assertThat(waiter.waitForElementNotVisible(popupBy)).as("Error").isTrue();
    return this;
  }

  public AuthPopup sendKeysByEmailField(String email) {
    $(popupBy).findElement(By.xpath(".//*[@class='email']"))
        .sendKeys(email);
    return this;
  }
}
