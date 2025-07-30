import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class MainPageTest {

  @Inject
  private MainPage mainPage;

//  @Inject
//  private ProductSlider productSlider;
//
//  @Inject
//  private AuthPopup authPopup;


  @Test
  public void checkHeaderPage() {
    mainPage.open();
//    authPopup.popupShouldNotVisible();
//    mainPage.clickHeaderEvent();
//    authPopup.popupShouldBeVisible()
//        .sendKeysByEmailField("test@test.ru");
  }
}
