package components.popup;


import commons.AbsCommon;
import org.openqa.selenium.By;
import scoped.GuiceScoped;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthPopup extends AbsCommon implements IPopup<AuthPopup> {

  private final By popupBy = By.cssSelector(".auth");

  public AuthPopup(GuiceScoped guiceScoped) {
    super(guiceScoped);
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
