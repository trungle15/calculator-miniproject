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
    this.reduce();
  }

  // ---- Constructor for (int, int) 
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
    this.reduce();
  }

  // --- Constructor for (String) --- Parsing Strings
  public BigFraction(String fraction) {
    // To add input validation later??
    int divisorIndex = fraction.indexOf('/');

    String numArray = fraction.substring(0, divisorIndex);
    String denomArray = fraction.substring(divisorIndex + 1);

    this.num = new BigInteger(numArray);
    this.denom = new BigInteger(denomArray);
    this.reduce();
  }

  // Methods
  // --- Getters ---
  public BigInteger getNumerator() {
    return num;
  }

  public BigInteger getDenominator() {
    return denom;
  }
  
  // --- Athrimetic methods ---
  public BigFraction add (BigFraction addMe) {
    BigInteger resultNum;
    BigInteger resultDenom;

    resultDenom = this.denom.multiply(addMe.denom);
    resultNum = this.num.multiply(addMe.denom).add(addMe.num.multiply(this.denom));
    return new BigFraction(resultNum, resultDenom);
  }

  public BigFraction subtract (BigFraction subtractMe) {
    BigInteger resultNum;
    BigInteger resultDenom;

    resultDenom = this.denom.multiply(subtractMe.denom);
    resultNum = this.num.multiply(subtractMe.denom).subtract(subtractMe.num.multiply(this.denom));

    return new BigFraction(resultNum, resultDenom);
  }
  
  public BigFraction multiply (BigFraction multiplyMe) {
    BigInteger resultNum;
    BigInteger resultDenom;

    resultDenom = this.denom.multiply(multiplyMe.denom);
    resultNum = this.num.multiply(multiplyMe.num);

    return new BigFraction(resultNum, resultDenom);
  }

  public BigFraction divide (BigFraction divideMe) {
    BigInteger resultNum;
    BigInteger resultDenom;

    resultDenom = this.denom.multiply(divideMe.num);
    resultNum = this.num.multiply(divideMe.denom);

    return new BigFraction(resultNum, resultDenom);
  }

  // --- Reduce method ---
  public void reduce() {
    BigInteger gcd = num.gcd(denom);
    this.num = this.num.divide(gcd);
    this.denom = this.denom.divide(gcd);
  }

  // --- Convert fraction to string ---
  public String toString() {
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    }
    return this.num + "/" + this.denom;
  }
}