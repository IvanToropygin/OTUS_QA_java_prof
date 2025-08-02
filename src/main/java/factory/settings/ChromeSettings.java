package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeSettings implements ISettings {

  @Override
  public AbstractDriverOptions getSettings(DesiredCapabilities desiredCapabilities) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--window-size=1920,1080");
    chromeOptions.setExperimentalOption("excludeSwitches",
        new String[]{"disable-component-update"});
    chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
    chromeOptions.merge(desiredCapabilities);
    return chromeOptions;
  }
}
