package exceptions;

public class ComponentSelectorNotValidException extends RuntimeException {

  public ComponentSelectorNotValidException(String componentAnnotationNotFound) {
    super("Component selector not valid");
  }
}
