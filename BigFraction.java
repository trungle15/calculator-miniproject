import java.math.BigInteger;

public class BigFraction {
  // Fields
  private BigInteger num;
  private BigInteger denom;

  // Constructors 

  // ---- Constructor for (BigInteger, BigInteger) 
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  }

  // ---- Constructor for (int, int) 
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  }

  // --- Constructor for (String) --- Parsing
  public BigFraction(String fraction) {
    // To add input validation later??
    int divisorIndex = fraction.indexOf('/');

    String numArray = fraction.substring(0, divisorIndex);
    String denomArray = fraction.substring(divisorIndex + 1);

    this.num = new BigInteger(numArray);
    this.denom = new BigInteger(denomArray);
  }

  // Methods
  public BigInteger getNumerator() {
    return num;
  }

  public BigInteger getDenominator() {
    return denom;
  }
}