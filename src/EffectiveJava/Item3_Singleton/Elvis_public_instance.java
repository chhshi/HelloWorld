package EffectiveJava.Item3_Singleton;

// Lazy_Initialization with public final field

public class Elvis_public_instance {

  public static final Elvis_public_instance INSTANCE = new Elvis_public_instance();

  private Elvis_public_instance() {  }

  public void leaveTheBuilding() {  }

}