package pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CourseDetailsPage extends AbsBasePage<CourseDetailsPage> {

  public CourseDetailsPage(WebDriver driver) {
    super(driver);
  }

  public void assertTitlePage(String courseName) {
    String titleLocator = "//main//h1";
    waiter.waitForElementVisible(By.xpath(titleLocator));
    Assertions.assertEquals(courseName, $(By.xpath(titleLocator)).getText(), "Заголовок страницы не совпал");
  }

  public void assertStartDate(LocalDate expectedStartDate) {
    String pageSource = driver.getPageSource();
    Document doc = Jsoup.parse(pageSource);

    Element dateElement = doc.selectFirst("#__next > div > main > div > section > div > div > div:nth-child(1) > p");
    if (dateElement == null) {
      throw new AssertionError("Элемент с датой не найден на странице");
    }

    String dateText = dateElement.text().trim();

    try {
      DateTimeFormatter dayMonthFormatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("ru"));
      MonthDay actualMonthDay = MonthDay.parse(dateText, dayMonthFormatter);
      MonthDay expectedMonthDay = MonthDay.from(expectedStartDate);

      Assertions.assertEquals(expectedMonthDay, actualMonthDay,
          "День и месяц начала курса не совпали");
    } catch (Exception e) {
      throw new AssertionError("Ошибка при парсинге даты: " + dateText, e);
    }
  }

  public void backToCourseList() {
    driver.navigate().back();
  }
}
