package EffectiveJava.Item6_Avoid_Unecessary_Object;

import java.util.regex.Pattern;


/**
 // Performance can be greatly improved!
 // it internally creates a Pattern instance for the regular expression and uses it only once,
 // after which it becomes eligible for garbage collection.
 // Creating a Pattern instance is expensive because it requires compiling the regular expression into a finite state machine.

 static boolean isRomanNumeral(String s) {

   return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"

   + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

 }

*/

public class RomanNumerals {


  // It would be possible to eliminate the initialization by lazily initializing the field
  //    (Item 83) the first time the isRomanNumeral method is invoked, but this is not recommended.
  // As is often the case with lazy initialization,
  //    it would complicate the implementation with no measurable performance improvement
  private static final Pattern ROMAN = Pattern.compile(

      "^(?=.)M*(C[MD]|D?C{0,3})"

          + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");



  static boolean isRomanNumeral(String s) {

    return ROMAN.matcher(s).matches();

  }




}
