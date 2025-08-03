package homework.steps;

import com.google.inject.Inject;
import components.popup.EducationMenuInHeaderPopup;
import homework.context.TestContext;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.MainPage;
import utils.RandomSelector;

import java.util.List;

public class MainPageSteps {

  @Inject
  private MainPage mainPage;

  @Inject
  private EducationMenuInHeaderPopup educationMenuInHeaderPopup;

  @Inject
  private TestContext testContext;

  @Inject
  RandomSelector randomSelector;

  @Пусть("Открыта главная страница")
  public void openMainPage() {
    mainPage.open();
  }

  @Тогда("Меню 'Обучение' не раскрыто")
  public void menuLearningNotOpen() {
    educationMenuInHeaderPopup.popupShouldNotVisible();
  }
  @Тогда("Курсор наведен на меню 'Обучение'")
  public void moveMouseOnMenuLearning() {
    educationMenuInHeaderPopup.moveMouseOnEducationMenu();
  }
  @Тогда("Меню 'Обучение' раскрыто")
  public void menuLearningOpen() {
    educationMenuInHeaderPopup.popupShouldBeVisible();
  }

  @Когда("Выбрано случайное направление обучения")
  public void chooseCategoryLearning() {
    List<String> allDirections = educationMenuInHeaderPopup.getAllEducationDirections();
    String randomDirection = randomSelector.getRandomElement(allDirections);
    testContext.set("randomDirection", randomDirection);
    educationMenuInHeaderPopup.moveMouseOnDirectionOfStudyAndClick(randomDirection);
  }
}
