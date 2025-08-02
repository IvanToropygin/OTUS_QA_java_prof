package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import pages.CourseDetailsPage;
import pages.CoursesPage;
import pages.MainPage;

public class PageGuiceModule extends AbstractModule {

  private final WebDriver driver;

  public PageGuiceModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public MainPage getMainPage() {
    return new MainPage(driver);
  }

  @Provides
  @Singleton
  public CoursesPage getCoursesPage() {
    return new CoursesPage(driver);
  }

  @Provides
  @Singleton
  public CourseDetailsPage getCourseDetailsPage() {
    return new CourseDetailsPage(driver);
  }
}
