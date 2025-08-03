package utils;


import io.cucumber.guice.ScenarioScoped;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ScenarioScoped
public class RandomSelector {

  public String getRandomElement(List<String> list) {
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException("List cannot be null or empty");
    }
    return list.get(ThreadLocalRandom.current().nextInt(list.size()));
  }
}
