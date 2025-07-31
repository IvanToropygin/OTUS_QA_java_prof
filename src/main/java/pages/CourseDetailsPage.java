package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CourseDetailsPage extends AbsBasePage<CourseDetailsPage> {

  public CourseDetailsPage(WebDriver driver) {
    super(driver);
  }

  public void assertTitlePage(String courseName) {
    String titleLocator = "//main//h1";
    waiter.waitForElementVisible(By.xpath(titleLocator));
    Assertions.assertEquals(courseName, $(By.xpath(titleLocator)).getText(), "Заголовок страницы не совпал");
  }
}
