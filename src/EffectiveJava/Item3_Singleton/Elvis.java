package EffectiveJava.Item3_Singleton;


/**
 * ITEM 3: ENFORCE THE SINGLETON PROPERTY WITH A PRIVATE CONSTRUCTOR OR AN ENUM TYPE
 */


// Singleton with public final field

public class Elvis {

  public static final Elvis INSTANCE = new Elvis();

  private Elvis() { ... }



  public void leaveTheBuilding() { ... }

}
