package pages;

import annotations.Path;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {

  @FindBy(xpath = "//*[contains(@href,'lesson')][starts-with(@class, 'sc-zz')]" +
      "//h6[starts-with(@class, 'sc')]//*[starts-with(@class, 'sc')]")
  private List<WebElement> coursesNames;

  public CoursesPage(WebDriver driver) {
    super(driver);
  }

  public CourseDetailsPage clickCourseName(String courseName) {
    WebElement course = coursesNames.stream()
        .filter(it -> it.getText().trim().equals(courseName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(String.format("Курс: %s, - не найден", courseName)));

    actions.moveToElement(course).build().perform();
    course.click();

    return new CourseDetailsPage(this.driver);
  }
}
