package exceptions;

public class WebdriverNotValidException extends RuntimeException {

  public WebdriverNotValidException(String browserName) {
    super(String.format("Webdriver for %s, - not supported", browserName));
  }
}
