/**
 * Interactive calculator frontend for calculating Big Fractions
 *
 * @author Trung Le
 */

import java.util.Scanner;


public class InteractiveCalculator {

  public static BFCalculator evaluateExpression (String[] tokens, Registers registers) throws Exception {
    BFCalculator result = null;

    for (int i = 0; i < tokens.length; i ++) {
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
          result = new BFCalculator();
          result.add(nextFraction);
        } else {
          String operator = tokens[i - 1];
          switch (operator) {
            case "+":
              result.add(nextFraction);
              break;
            case "-":
              result.subtract(nextFraction);
              break;
            case "*":
              result.multiply(nextFraction);
              break;
            case "/":
              result.divide(nextFraction);
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
    BFCalculator tempFraction = new BFCalculator();
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
          registers.store(inputArray[1].charAt(0), tempFraction.get());
      } else {
          try {
              BFCalculator result = evaluateExpression(inputArray, registers);
              tempFraction.clear(); // Reset the BFCalculator
              tempFraction.add(result.get()); // Update with the new result
              System.out.println(result.get().toString()); // Print the result
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage()); // Display exception message
          }
      }
  }
}
}
