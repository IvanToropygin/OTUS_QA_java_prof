package homework.context;

import io.cucumber.guice.ScenarioScoped;

import java.util.HashMap;
import java.util.Map;

@ScenarioScoped
public class TestContext {
  private final Map<String, Object> context = new HashMap<>();

  public synchronized void set(String key, Object value) {
    context.put(key, value);
  }

  public synchronized <T> T get(String key) {
    return (T) context.get(key);
  }

  public synchronized void clear() {
    context.clear();
  }
}
