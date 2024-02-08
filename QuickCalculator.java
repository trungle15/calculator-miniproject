/**
 * Quick calculator frontend for calculating Big Fractions
 *
 * @author Trung Le
 */

public class QuickCalculator {
    private static BFCalculator fractionCalculator = new BFCalculator();
    private static Registers registers = new Registers();

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("STORE")) {
                // Handle STORE command
                char register = arg.split(" ")[1].charAt(0);
                registers.store(register, fractionCalculator.get());
            } else {
                // Evaluate expression
                try {
                    String[] tokens = arg.split(" ");
                    BigFraction result = evaluateExpression(tokens, registers);
                    fractionCalculator.clear(); 
                    fractionCalculator.add(result);
                    System.out.println(arg + " = " + result);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage()); // Display exception message
                }
            }
        }
    }
    
    // similar to interactive calculator
    public static BigFraction evaluateExpression(String[] tokens, Registers registers) throws Exception {
        BigFraction result = null;

        for (int i = 0; i < tokens.length; i++) {
            BigFraction nextFraction;

            if (i % 2 == 0) { // Operand expected
                if (Character.isLetter(tokens[i].charAt(0)) && tokens[i].length() == 1) {
                    nextFraction = registers.get(tokens[i].charAt(0));
                    if (nextFraction == null) {
                        throw new Exception("Register not found");
                    }
                } else { // Direct fraction
                    nextFraction = new BigFraction(tokens[i]);
                }

                if (result == null) {
                    result = nextFraction;
                } else {
                }
            } else {
                // Operator
                String operator = tokens[i];
                i++; // Move to next operand
                nextFraction = new BigFraction(tokens[i]);
                
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

        if (result == null) {
            throw new Exception("Invalid expression");
        }
        return result;
    }
}
