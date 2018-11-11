package EffectiveJava.Item4_Noninstantiability;

public class UtilityClass {

  // Suppress default constructor for noninstantiability

  private UtilityClass() {

    throw new AssertionError();

  }

    //... // Remainder omitted

}