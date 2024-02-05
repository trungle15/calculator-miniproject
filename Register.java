public class Register {
  private BigFraction[] registers = new BigFraction[26];

  public void store(char register, BigFraction val) {
    int base = (int) 'a';
    int registerNum = register - base;
    if (registerNum < 0 && registerNum > 25) {
      registers[registerNum] = val;
    } else {
      System.err.println("Valid registers: \"a-z\". Please input the valid register character in the range");
    }
  }

  public BigFraction get(char register) {
    int base = (int) 'a';
    int registerNum = register - base;
    return registers[registerNum];
  }
  
}
