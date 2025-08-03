package homework2.context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

  private final Map<String, Object> context = new HashMap<>();

  public void set(String key, Object value) {
    context.put(key, value);
  }

  public <T> T get(String key) {
    return (T) context.get(key);
  }

  public void clear() {
    context.clear();
  }
}
