package pages;

import annotations.Path;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {

  @FindBy(xpath = "//*[contains(@href,'lesson')]")
  private List<WebElement> coursesCards;

  @FindBy(xpath = "//*[contains(@href,'lesson')]//h6/div")
  private List<WebElement> coursesNames;

  @FindBy(xpath = "//button[.='Показать еще 20']")
  private WebElement moreTwentyBtn;

  @FindBy(xpath = "//*[contains(@class, 'js-sticky-banner-close')]")
  private WebElement bottomSaleBannerCloseBtn;

  public CoursesPage(WebDriver driver) {
    super(driver);
  }

  public CourseDetailsPage clickCourseName(String courseName) {
    WebElement course = coursesNames.stream()
        .filter(it -> it.getText().trim().equals(courseName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(String.format("Курс: %s, - не найден", courseName)));

    actions.scrollToElement(course).build().perform();
    course.click();

    return new CourseDetailsPage(this.driver);
  }

  public CoursesPage closeBottomSaleBannerIfVisible() {
    try {
      if (bottomSaleBannerCloseBtn.isDisplayed()) {
        bottomSaleBannerCloseBtn.click();
      }
    } catch (NoSuchElementException e) {
      e.getMessage();
    }
    return this;
  }

  public List<Course> getCoursesWithExtremeDates() {
    List<Course> allCourses = parseAllCourses().stream().distinct().toList();
    if (allCourses.isEmpty()) {
      return Collections.emptyList();
    }

    List<LocalDate> dates = allCourses.stream()
        .map(Course::startDate)
        .toList();

    LocalDate minDate = Collections.min(dates);
    LocalDate maxDate = Collections.max(dates);

    return allCourses.stream()
        .filter(c -> c.startDate().equals(minDate) || c.startDate().equals(maxDate))
        .toList();
  }

  private List<Course> parseAllCourses() {
    return coursesCards.stream()
        .map(card -> {
          try {
            String name = card.findElement(By.xpath(".//h6/div"))
                .getText();
            String dateText = card.findElement(By.xpath(".//*[contains(text(), 'месяцев')]"))
                .getText();
            return new Course(name, parseDate(dateText));
          } catch (Exception e) {
            System.out.println("Не удалось распарсить карточку курса: " + e.getMessage());
            return null;
          }
        })
        .filter(Objects::nonNull)
        .toList();
  }

  private LocalDate parseDate(String text) {
    String datePart = text.split("·")[0].trim();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
    return LocalDate.parse(datePart, formatter);
  }

  public void verifyCheckboxState(String directionName) {
    WebElement element = $(By.xpath(String.format("//label[contains(text(), '%s')]/..", directionName)));
    Assertions.assertEquals(
        "true", element.getAttribute("value"),
        String.format("Чекбокс у элемента: %s, - не отмечен или не найден", directionName));
  }

  public record Course(String name, LocalDate startDate) {
  }
}
