import com.google.inject.Inject;
import components.popup.EducationMenuInHeaderPopup;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CourseDetailsPage;
import pages.CoursesPage;
import pages.MainPage;
import utils.RandomSelector;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(UIExtension.class)
public class HomeworkOneTest {

  @Inject
  private CoursesPage coursesPage;

  @Inject
  private MainPage mainPage;

  @Inject
  private EducationMenuInHeaderPopup educationMenuInHeaderPopup;

  @Inject
  RandomSelector randomSelector;

  /**
   * Сценарий 1.
   * Открыть страницу каталога курсов https://otus.ru/catalog/courses
   * Найти курс по имени (имя курса должно передаваться как данные в тесте)
   * Кликнуть по плитке курса и проверить, что открыта страница верного курса
   * Для поиска курса по имени обязательно необходимо использовать stream api.
   */
  @ParameterizedTest
  @MethodSource("courseNamesAndTitlesProvider")
  public void openCoursesCatalogAndClickItemByNameTest(String courseName) {
    coursesPage
        .open()
        .closeBottomSaleBannerIfVisible()
        .clickCourseName(courseName)
        .assertTitlePage(courseName);
  }

  static Stream<Arguments> courseNamesAndTitlesProvider() {
    return Stream.of(
        Arguments.of("SRE практики и инструменты"),
        Arguments.of("C# Developer. Basic")
    );
  }

  /**
   * Сценарий 2.
   * Открыть страницу каталога курсов https://otus.ru/catalog/courses
   * Найти курсы, которые стартуют раньше и позже всех. Если даты совпадают, то выбрать все курсы, у которых дата совпадает.
   * Проверить, что на карточке самого раннего/позднего курсов отображается верное название курса и дата его начала
   * Для поиска таких курсов необходимо использовать stream api и reduce.
   * Также для проверки данных на странице карточки курса необходимо использовать jsoup.
   */
  @Test
  public void getAndOpenEarliestAndLatestCoursesTest() throws Exception {
    coursesPage
        .open()
        .closeBottomSaleBannerIfVisible();
    List<CoursesPage.Course> courses = coursesPage.getCoursesWithExtremeDates();

    for (CoursesPage.Course course : courses) {
      CourseDetailsPage courseDetails = coursesPage.clickCourseName(course.name());
      courseDetails.assertTitlePage(course.name());
      courseDetails.assertStartDate(course.startDate());
      courseDetails.backToCourseList();
    }
  }

  /**
   * Сценарий 3.
   * Открыть главную страницу https://otus.ru
   * В заголовке страницы открыть меню «Обучение» и выбрать случайную категорию курсов
   * Проверить, что открыт каталог курсов верной категории
   */
  @Test
  public void getRandomCourseCategoryTest() {
    mainPage.open();
    educationMenuInHeaderPopup
        .popupShouldNotVisible()
        .moveMouseOnEducationMenu()
        .popupShouldBeVisible();
    List<String> directionsOfStudy = educationMenuInHeaderPopup.getAllEducationDirections();
    String randomDirection = randomSelector.getRandomElement(directionsOfStudy);
    educationMenuInHeaderPopup.moveMouseOnDirectionOfStudyAndClick(randomDirection)
        .verifyCheckboxState(randomDirection);
  }
}
