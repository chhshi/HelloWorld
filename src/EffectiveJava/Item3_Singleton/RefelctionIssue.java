package EffectiveJava.Item3_Singleton;

import java.lang.reflect.Constructor;




/**
 * An advanced user can change the private access modifier of the constructor to anything they want at runtime using reflection.
 * If this happens, our only mechanism for non-instantiability breaks.
 *
 * Making Singletons With Enum in Java
 * Reflection & Serialize problems can be solved very easily by using the enum type to make singletons.
 */
public class RefelctionIssue {
  public static void main(String[] args) throws Exception {
    Singleton singleton = Singleton.INSTANCE;
    Constructor constructor = singleton.getClass().getDeclaredConstructor(new Class[0]);
    constructor.setAccessible(true);
    Singleton singleton2 = (Singleton) constructor.newInstance();
    if (singleton == singleton2) {
      System.out.println("Two objects are same");
    } else {
      System.out.println("Two objects are not same");
    }
    singleton.setValue(1);
    singleton2.setValue(2);
    System.out.println(singleton.getValue());
    System.out.println(singleton2.getValue());
  }
}