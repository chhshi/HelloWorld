package EffectiveJava.Item5_Denpendency_Injection;

import java.util.List;
import java.util.Objects;

/**
 * In summary, do not use a singleton or static utility class to implement a class
 * that depends on one or more underlying resources whose behavior affects that of the class,
 * and do not have the class create these resources directly.
 *
 * Instead, pass the resources, or factories to create them, into the constructor (or static factory or builder).
 * This practice, known as dependency injection, will greatly enhance the flexibility, reusability, and testability of a class.
 *
 */

// Dependency injection provides flexibility and testability

public class SpellChecker {

  private final Lexicon dictionary;

  //Dependency Injection: pass the resource into the constructor when creating a new instance.

  public SpellChecker(Lexicon dictionary) {
    this.dictionary = Objects.requireNonNull(dictionary);
  }


  public boolean isValid(String word) {  return false; }

  public List<String> suggestions(String typo) {  return null;}

}

class Lexicon {

}


