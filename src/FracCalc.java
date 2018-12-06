/*Emiliya Jones
 *Period 1
 *Fraction Calculator
 *11 December, 2018 
 */
import java.util.*; // Import this library so that 'Scanner' can be utilized 
public class FracCalc {
    public static void main(String[] args) 
    {
    	System.out.println("Welcome to Fraction Calculator! Please input 2 values seperated by a space before and "
    			+ "after the operation (+,-,*,/). Improper fractions can be inputed with an underscore"
    			+ "seperating the number and the fraction.");
    	System.out.println("Example: 4 + 6 OR 4_1/2 + 6 Please enter 'quit' if you'd like to stop!");
    	Scanner console = new Scanner(System.in); //Scanner initialized so that the program can receive input from user
    	System.out.print("Please input equation: ");
    	String equation = console.nextLine();
    	while (!(equation.equals("quit")))
    	{
    		System.out.println(produceAnswer(equation));
    		System.out.print("Please input equation: ");
        	equation = console.nextLine(); // While loop needs to be updated in order to ask the user for input multiple times
    	}
    	if (equation.equalsIgnoreCase("quit")) // If user inputs 'quit' the program stops, not case-sensitive 
    	{
    		System.out.println("Thanks for playing!");
    	}
    }
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
    	for(int i = 0; i < input.length(); i++) 
    	{
    		char y = input.charAt(i);
            if (y == '+'|| y=='-'|| y=='*' || (y == '/' && input.charAt(i+1) == ' ')) 
            {
                String second = input.substring(i+2, input.length()); 
                String first = input.substring(0,i); // This line gets the first answer but is not applied at this checkpoint
                int underscore = second.indexOf("_");
                int numeratorSeperate = second.indexOf("/");
                if (underscore > 0) // Checks to see if the input is a mixed fraction
                {
                	String whole = "Whole: " + second.substring(0, underscore);
                
               	
                if (numeratorSeperate > 0) // This is utilized when there is an underscore to separate a mixed fraction
                	{
                    String numerator = "Numerator: "+ second.substring(underscore + 1, numeratorSeperate);
                    String denominator = "Denominator: "+ second.substring(numeratorSeperate + 1, second.length());
                    return whole + " " + numerator + " " + denominator;
                	}
                }
                if ((!(underscore > 0)) && numeratorSeperate > 0) // This is utilized when the second input is only a fraction
                {
                	String numerator = "Numerator: "+ second.substring(underscore + 1, numeratorSeperate);
                    String denominator = "Denominator: "+ second.substring(numeratorSeperate + 1, second.length());
                    return numerator + " " + denominator;
                }
                else // If not a mixed fraction AND not just a fraction, this prints out the number as a whole number
                {
                	return "Whole: " + input.substring(i+2, input.length());
                }
              }
                
    	}
    	return input; // If you don't have this return statement then the program does not run correctly
}

 // TODO: Implement this function to produce the solution to the input
    // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
    // Checkpoint 2: Return the second operand as a string representing each part.
    //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
    // Checkpoint 3: Evaluate the formula and return the result as a fraction.
    //               Example "4/5 * 1_2/4" returns "6/5".
    //               Note: Answer does not need to be reduced, but it must be correct.
    // Final project: All answers must be reduced.
    //               Example "4/5 * 1_2/4" returns "1_1/5". 

    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
