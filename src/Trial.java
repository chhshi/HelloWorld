import java.math.BigDecimal;
import java.math.RoundingMode;

public class Trial {
  public static void main(String[] args) {
//    BigDecimal bd = new BigDecimal("1.1234567890123");
//    System.out.println(bd.toString());

  System.out.println(Trial.roundRateToTenDecimals(1.12345678901D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678902D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678903D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678904D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678905D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678906D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678907D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678908D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678909D));
    System.out.println(Trial.roundRateToTenDecimals(1.123456789010D));
    System.out.println(Trial.roundRateToTenDecimals(1.1234567D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678D));
    System.out.println(Trial.roundRateToTenDecimals(1.1234567899D));
    System.out.println(Trial.roundRateToTenDecimals(1.12345678999D));

  }

  public static Double roundRateToTenDecimals(Double val) {
    return new BigDecimal(val).setScale(10, RoundingMode.HALF_UP).doubleValue();
  }
}
