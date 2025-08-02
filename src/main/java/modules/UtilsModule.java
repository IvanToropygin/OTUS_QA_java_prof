package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import utils.RandomSelector;

public class UtilsModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(RandomSelector.class).in(Singleton.class);
  }
}
