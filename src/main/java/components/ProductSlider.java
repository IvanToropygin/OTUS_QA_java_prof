package components;

import org.example.annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Component("css->.block")
public class ProductSlider extends AbsComponent {

  public ProductSlider(WebDriver driver) {
    super(driver);
  }

  public ProductSlider checkHeader() {
    this.getComponentEntity().findElement(By.xpath(".//a[contains(@class, 'test_link')]"));
    return this;
  }
}
