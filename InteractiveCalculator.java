/**
 * Interactive calculator frontend for calculating Big Fractions
 *
 * @author Trung Le
 */

import java.util.Scanner;


public class InteractiveCalculator {

  public static BigFraction evaluateExpression (String[] tokens, Registers registers) throws Exception {
    BigFraction result = null;

    for (int i = 1; i < tokens.length; i ++) {
      BigFraction nextFraction;

      if (i % 2 == 0) {
        if (Character.isLetter(tokens[i].charAt(0)) && tokens[i].length() == 1) {
          nextFraction = registers.get(tokens[i].charAt(0));
          if (nextFraction == null) {
            throw new Exception("Register not found"); 
          }
        } else {
          nextFraction = new BigFraction(tokens[i]);
        }

        if (result == null) {
          result = nextFraction;
        } else {
          String operator = tokens[i - 1];
          switch (operator) {
            case "+":
              result = result.add(nextFraction);
              break;
            case "-":
              result = result.subtract(nextFraction);
              break;
            case "*":
              result = result.multiply(nextFraction);
              break;
            case "/":
              result = result.divide(nextFraction);
              break;
            default:
              throw new Exception("Invalid operator: " + operator);
          }
        }
      } 
    }
    if (result == null) {
      throw new Exception("Invalid expression"); 
    }
    return result;
  }



  public static void main(String[] args) {
    Scanner eye = new Scanner(System.in);
    BFCalculator fraction = new BFCalculator();
    Registers registers = new Registers();

    while (true) {
      System.out.print("> ");
      String input = eye.nextLine().trim();

      if (input.equals("QUIT")) {
        eye.close();
        return; 
      }
      String[] inputArray = input.split(" ");

      if ("STORE".equalsIgnoreCase(inputArray[0]) && inputArray.length == 2) {
          registers.store(inputArray[1].charAt(0), fraction.get());
      } else {
          try {
              BigFraction result = evaluateExpression(inputArray, registers);
              fraction.clear(); // Reset the BFCalculator
              fraction.add(result); // Update with the new result
              System.out.println(result.toString()); // Print the result
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage()); // Display exception message
          }
      }
  }
}
}
