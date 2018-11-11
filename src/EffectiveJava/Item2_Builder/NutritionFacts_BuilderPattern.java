package EffectiveJava.Item2_Builder;

public class NutritionFacts_BuilderPattern {

  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  public static class Builder {

    // Required parameters
    private final int servingSize;
    private final int servings;

    // Optional parameters - initialized to default values
    private int calories      = 0;
    private int fat           = 0;
    private int sodium        = 0;
    private int carbohydrate  = 0;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings    = servings;

    }



    public Builder calories(int val)

    { calories = val;      return this; }

    public Builder fat(int val)

    { fat = val;           return this; }

    public Builder sodium(int val)

    { sodium = val;        return this; }

    public Builder carbohydrate(int val)

    { carbohydrate = val;  return this; }



    public NutritionFacts_BuilderPattern build() {

      return new NutritionFacts_BuilderPattern(this);

    }

  }



  private NutritionFacts_BuilderPattern(Builder builder) {

    servingSize  = builder.servingSize;

    servings     = builder.servings;

    calories     = builder.calories;

    fat          = builder.fat;

    sodium       = builder.sodium;

    carbohydrate = builder.carbohydrate;

  }

}

class Main3 {

  public static void main(String[] args) {
    NutritionFacts_BuilderPattern cocaCola = new NutritionFacts_BuilderPattern.Builder(240, 8)
        .calories(100).sodium(35).carbohydrate(27).build();

  }
}
