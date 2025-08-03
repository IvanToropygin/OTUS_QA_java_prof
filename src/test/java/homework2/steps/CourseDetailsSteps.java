package homework2.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.CourseDetailsPage;

public class CourseDetailsSteps {

  @Inject
  private CourseDetailsPage courseDetailsPage;

  @Тогда("Открыта страница информации о курсе (.*)$")
  public void openCoursesCatalogPage(String courseName) {
    courseDetailsPage.assertTitlePage(courseName);
  }
}
