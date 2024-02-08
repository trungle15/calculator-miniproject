/**
 * Class for handling calculation Big Fractions
 *
 * @author Trung Le
 */

public class BFCalculator {
  private BigFraction latestValue; 

  // --- Constructor ---
  public BFCalculator() {
    latestValue = new BigFraction(0, 1);
  }
  
  public BigFraction get() {
    return latestValue;
  }

  void add(BigFraction val) {
    latestValue = latestValue.add(val);
  }

  void subtract(BigFraction val) {
    latestValue = latestValue.subtract(val);
  }

  void multiply(BigFraction val) {
    latestValue = latestValue.multiply(val);
  }

  void divide(BigFraction val) {
    latestValue = latestValue.divide(val);
  }

  void clear() {
    latestValue = new BigFraction(0, 1);
  }
}
