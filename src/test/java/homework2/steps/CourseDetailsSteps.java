package homework2.steps;

import com.google.inject.Inject;
import homework2.context.MyTestContext;
import io.cucumber.java.ru.Тогда;
import pages.CourseDetailsPage;
import pages.CoursesCatalogPage;

import java.util.List;

public class CourseDetailsSteps {

  @Inject
  private CourseDetailsPage courseDetailsPage;

  @Inject
  private CoursesCatalogPage coursesCatalogPage;

  @Inject
  private MyTestContext myTestContext;

  @Тогда("Открыта страница информации о курсе (.*)$")
  public void openCoursesCatalogPage(String courseName) {
    courseDetailsPage.assertTitlePage(courseName);
  }

  @Тогда("Открыть и проверить каждый курс из списка")
  public void openAndVerifyEachCourse() {
    List<CoursesCatalogPage.Course> courses = myTestContext.get("extreme_courses");
    System.out.println(courses);

    for (CoursesCatalogPage.Course course : courses) {
      try {
        coursesCatalogPage.clickCourseName(course.name());
        courseDetailsPage.assertTitlePage(course.name());
        courseDetailsPage.assertStartDate(course.startDate());
      } finally {
        courseDetailsPage.backToCourseList();
      }
    }
  }
}
