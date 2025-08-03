package components.popup;

import commons.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CoursesCatalogPage;
import scoped.GuiceScoped;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EducationMenuInHeaderPopup extends AbsCommon implements IPopup<EducationMenuInHeaderPopup> {

  private final By menuTitle = By.xpath("//*[@title='Обучение']");
  private final By titleAllCourses = By.xpath("//p[.='Все курсы'][ancestor::*[contains(., 'Обучение')]]");

  @FindBy(xpath = "//p[text()='Все курсы']/following-sibling::div/a")
  List<WebElement> directionsOfStudy;

  public EducationMenuInHeaderPopup(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @Override
  public EducationMenuInHeaderPopup popupShouldBeVisible() {
    assertThat(waiter.waitForElementVisible(titleAllCourses)).as("Меню 'Обучение' не видимо").isTrue();
    return this;
  }

  @Override
  public EducationMenuInHeaderPopup popupShouldNotVisible() {
    assertThat(waiter.waitForElementNotVisible(titleAllCourses)).as("Меню 'Обучение' видимо").isTrue();
    return this;
  }

  public EducationMenuInHeaderPopup moveMouseOnEducationMenu() {
    actions.moveToElement($(menuTitle)).build().perform();
    return this;
  }

  public EducationMenuInHeaderPopup sendKeysByEmailField(String email) {
    $(menuTitle).findElement(By.xpath(".//*[@class='email']"))
        .sendKeys(email);
    return this;
  }

  public List<String> getAllEducationDirections() {
    List<String> directions = directionsOfStudy.stream()
        .map(e -> e.getText().trim())
        .filter(text -> !text.isEmpty())
        .filter(text -> !text.contains("Специализации"))
        .filter(text -> !text.contains("Подготовительные курсы"))
        .map(text -> text.replaceAll("\\s*\\(\\d+\\)$", ""))
        .toList();

    if (directions.isEmpty()) {
      throw new IllegalArgumentException("Directions of study is Empty");
    }
    return directions;
  }

  public CoursesCatalogPage moveMouseOnDirectionOfStudyAndClick(String directionName) {
    WebElement direction = directionsOfStudy.stream()
        .filter(it -> it.getText().trim().contains(directionName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(String.format("Направление: %s, - не найдено", directionName)));

    actions.moveToElement(direction).build().perform();
    direction.click();
    return new CoursesCatalogPage(this.guiceScoped);
  }
}
