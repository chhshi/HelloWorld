package EffectiveJava.Item1_Static_Factory_Methods;

public class factory {


  /**
   * One advantage of static factory methods is that, unlike constructors, they have names.
   * A second advantage of static factory methods is that, unlike constructors,
   *      they are not required to create a new object each time they’re invoked.
   * A third advantage of static factory methods is that, unlike constructors,
   *      they can return an object of any subtype of their return type.
   */
  public static Boolean valueOf(boolean b) {

    return b ? Boolean.TRUE : Boolean.FALSE;

  }
}
