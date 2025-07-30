package commons;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiter;

public abstract class AbsCommon {

    protected WebDriver driver;
    protected Waiter waiter;
    protected Actions actions;

    @Inject
    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.actions = new Actions(driver);

        PageFactory.initElements(driver, this);
    }

    protected WebElement $(By selector) {
        return driver.findElement(selector);
    }
}
