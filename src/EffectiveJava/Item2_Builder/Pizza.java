package EffectiveJava.Item2_Builder;

import static EffectiveJava.Item2_Builder.NyPizza.Size.*;
import static EffectiveJava.Item2_Builder.Pizza.Topping.*;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {

  public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }

  final Set<Topping> toppings;

  abstract static class Builder<T extends Builder<T>> {

    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

    public T addTopping(Topping topping) {
      toppings.add(Objects.requireNonNull(topping));
      return self();
    }

    abstract Pizza build();

    // Subclasses must override this method to return "this" to get its instance of its own
    protected abstract T self();

  }

  Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone(); // See Item  50
  }

}

class Main {

  public static void main(String[] args) {

    NyPizza pizza = new NyPizza.Builder(SMALL)
        .addTopping(SAUSAGE).addTopping(ONION).build();

    Calzone calzone = new Calzone.Builder()
        .addTopping(HAM).sauceInside().build();
  }
}