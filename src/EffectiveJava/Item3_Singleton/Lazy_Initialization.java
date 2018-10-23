package EffectiveJava.Item3_Singleton;

public class Lazy_Initialization {

  private static Lazy_Initialization INSTANCE = null;

  private Lazy_Initialization() {}


  /**
   * Static fields are initialized at class loading time.
   * Therefore, singleton instances are created even in a case we don't use them at runtime.
   * This is not a problem as long as the singleton object is not too big and creating the instance is not too expensive.
   *
   * To avoid this problem with lazy initialization:
   * the instance is created when we access the singleton object for the first time.
   * Fine-grained synchronization is used to ensure that no more than one object is created with multiple concurrent threads.
   */
  public static Lazy_Initialization getInstance() {
    if (INSTANCE == null) {
      synchronized (Lazy_Initialization.class) {
        if (INSTANCE == null) {
          INSTANCE = new Lazy_Initialization();
        }
      }
    }
    return INSTANCE;
  }
}