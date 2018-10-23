package EffectiveJava.Item3_Singleton;


/**
 * ITEM 3: ENFORCE THE SINGLETON PROPERTY WITH A PRIVATE CONSTRUCTOR OR AN ENUM TYPE
 */


// Lazy_Initialization with public final field

public class Elvis_static_factory {

  //The private constructor is called only once, to initialize the public static final field Elvis_static_factory.INSTANCE.
  //public  static final Elvis_static_factory INSTANCE = new Elvis_static_factory();
  private static final Elvis_static_factory INSTANCE = new Elvis_static_factory();

  private Elvis_static_factory() {  }

  public static Elvis_static_factory getInstance() { return INSTANCE; }

  public void leaveTheBuilding() {  }

}
