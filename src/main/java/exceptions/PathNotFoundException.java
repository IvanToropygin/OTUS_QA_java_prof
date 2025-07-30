package exceptions;

public class PathNotFoundException extends RuntimeException {

  public PathNotFoundException(Class clazz) {
    super(String.format(
        "Page class: %s must have annotation Path with url.\nIf it main page: path == '/'",
        clazz.getCanonicalName()));
  }
}
