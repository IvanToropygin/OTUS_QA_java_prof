package homework2.steps;

import com.google.inject.Inject;
import homework2.context.TestContext;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.CourseDetailsPage;
import pages.CoursesCatalogPage;

import java.util.List;

public class CoursesCatalogSteps {

  @Inject
  private CoursesCatalogPage coursesCatalogPage;

  @Inject
  private CourseDetailsPage courseDetailsPage;

  @Inject
  private TestContext testContext;

  @Пусть("Открыта страница каталога курсов в браузере")
  public void openCoursesCatalogPage() {
    coursesCatalogPage.open();
  }

  @Пусть("Закрыт нижний рекламный баннер, если видимый")
  public void closeBottomBanner() {
    coursesCatalogPage.closeBottomSaleBannerIfVisible();
  }

  @Когда("Осуществлен клик по имени курса (.*)$")
  public void clickCourseByName(String courseName) {
    coursesCatalogPage.clickCourseName(courseName);
  }

  @Дано("Выбраны курсы с самой ранней и поздней датой старта")
  public void getCoursesWithExtremeStartDate() {
    coursesCatalogPage.open().closeBottomSaleBannerIfVisible();
    List<CoursesCatalogPage.Course> courses = coursesCatalogPage.getCoursesWithExtremeDates();
    testContext.set("extreme_courses", courses);
  }

  @Тогда("Проверить Чекбокс в боковом меню у выбранного Направления")
  public void verifyDirectionCheckbox() {
    String direction = testContext.get("randomDirection");
    coursesCatalogPage.verifyCheckboxState(direction);
  }
}
