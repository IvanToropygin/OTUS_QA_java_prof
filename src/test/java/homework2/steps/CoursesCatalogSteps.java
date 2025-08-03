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

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoursesCatalogSteps {

  @Inject
  private CoursesCatalogPage coursesCatalogPage;

  @Inject
  private CourseDetailsPage courseDetailsPage;

  @Inject
  TestContext testContext;

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
    List<CoursesCatalogPage.Course> courses = coursesCatalogPage.getCoursesWithExtremeDates();
    testContext.set("data", courses);
    assertNotNull(testContext.get("data"), "Список курсов не был сохранен в контексте");
  }

  @Когда("Открыт курс с самой ранней или поздней датой старта")
  public void openEachCourseInContext() {
    List<CoursesCatalogPage.Course> courses = testContext.get("data");
    for (CoursesCatalogPage.Course course : courses) {
      coursesCatalogPage.clickCourseName(course.name());
    }
  }

  @Тогда("Проверен заголовок и дата старта курса с самой ранней или поздней датой старта")
  public void checkEachCourseInContext() {
    List<CoursesCatalogPage.Course> courses = testContext.get("data");
    for (CoursesCatalogPage.Course course : courses) {
      courseDetailsPage.assertTitlePage(course.name());
      courseDetailsPage.assertStartDate(course.startDate());
      courseDetailsPage.backToCourseList();
    }
  }
}
