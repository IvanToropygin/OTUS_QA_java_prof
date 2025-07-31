import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CoursesPage;

import java.util.stream.Stream;

@ExtendWith(UIExtension.class)
public class HomeworkOneTest {

  @Inject
  private CoursesPage coursesPage;

  @ParameterizedTest
  @MethodSource("courseNamesAndTitlesProvider")
  public void openCoursesCatalogAndClickItemByNameTest(String courseName) {
    coursesPage.open()
        .clickCourseName(courseName)
        .assertTitlePage(courseName);
  }

  static Stream<Arguments> courseNamesAndTitlesProvider() {
    return Stream.of(
        Arguments.of("SRE практики и инструменты"),
        Arguments.of("C# Developer. Basic")
    );
  }
}
