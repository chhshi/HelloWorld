package EffectiveJava.Item2_Builder;

// JavaBeans Pattern - allows inconsistency, mandates mutability

public class NutritionFacts_JavaBean {

  // Parameters initialized to default values (if any)

  private int servingSize  = -1; // Required; no default value

  private int servings     = -1; // Required; no default value

  private int calories     = 0;

  private int fat          = 0;

  private int sodium       = 0;

  private int carbohydrate = 0;



  public NutritionFacts_JavaBean() { }


  // Setters

  public void setServingSize(int val)  { servingSize = val; }

  public void setServings(int val)    { servings = val; }

  public void setCalories(int val)    { calories = val; }

  public void setFat(int val)         { fat = val; }

  public void setSodium(int val)      { sodium = val; }

  public void setCarbohydrate(int val) { carbohydrate = val; }

}

class Main2 {
  public static void main(String[] args) {

    NutritionFacts_JavaBean cocaCola = new NutritionFacts_JavaBean();

    cocaCola.setServingSize(240);

    cocaCola.setServings(8);

    cocaCola.setCalories(100);

    cocaCola.setSodium(35);

    cocaCola.setCarbohydrate(27);

  }
}