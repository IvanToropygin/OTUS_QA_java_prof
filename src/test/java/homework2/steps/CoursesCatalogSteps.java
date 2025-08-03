package homework2.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import pages.CoursesCatalogPage;

public class CoursesCatalogSteps {

  @Inject
  private CoursesCatalogPage coursesCatalogPage;

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
}
