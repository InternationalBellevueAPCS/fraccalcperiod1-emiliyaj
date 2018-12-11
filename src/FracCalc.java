/*Emiliya Jones
 *Period 1
 *Fraction Calculator
 *14 December, 2018 
 */
/* Note: I had to change my code from earlier checkpoints 1 and 2 because I kept failing the tests and some of my numbers were said to be out of range. I used the split function
instead of using a for-loop to search for the operand because it assured that my substring would not be out of range and less room for error */
import java.util.*; // Import this library so that 'Scanner' can be utilized 
public class FracCalc 
{
    public static void main(String[] args) 
    {
    	System.out.println("Welcome to Fraction Calculator! Please input 2 values seperated by a space before and "
    			+ "after the operation (+,-,*,/). Improper fractions can be inputed with an underscore"
    			+ "seperating the number and the fraction.");
    	System.out.println("Example: 4 + 6 OR 4_1/2 + 6 Please enter 'quit' if you'd like to stop!");
    	Scanner console = new Scanner(System.in); // Scanner initialized so that the program can receive input from user
    	System.out.print("Please input equation: ");
    	String equation = console.nextLine();
    	while (!(equation.equals("quit"))) // Ensures that console continues to ask for input unless user states otherwise 
    	{
    		System.out.println(produceAnswer(equation));
    		System.out.print("Please input equation: ");
        	equation = console.nextLine(); // While loop needs to be updated in order to ask the user for input multiple times
    	}
    	if (equation.equalsIgnoreCase("quit")) // If user inputs 'quit' the program stops, not case-sensitive 
    	{
    		System.out.println("Thanks for using!");
    	}
    }
    public static String produceAnswer(String input)
    {
    	if (input.indexOf(" ") < 0)
    	{
             return "Please input a correct number or number format. Thank you!"; // If the input is inputed incorrectly (i.e. 4 +2 (there is no 
             										// space after the '+' operand) then this print response is returned
        }
       int space = input.indexOf(" "); // Finds the first encounter of a space (in the first number)
       
       // In this section, the function inputed is split in the first and second number, as well as the operand which is used later to determine output 
       String first = input.substring(0, space);
       String operator = input.substring(space+1,space+2);
       String second = input.substring(space+3);
       
       // Find the whole, numerator, and denominator in the first number
       String whole = wholeNumber(first);
       String numerator = numerator(first);
       String denominator = denominator(first);
       // Find the whole, numerator, and denominator in the second number
       String whole2 = wholeNumber(second);
       String numerator2 = numerator(second);
       String denominator2 = denominator(second);  
       
       /* Both the first and second number have been split up and divided into whole, numerator, and denominator.
       In this section, they are converted into integers from Strings so they can be utilized in the program as numbers NOT characters */
       
       // First number
       int wholenumber = Integer.parseInt(whole);
       int finalnumerator = Integer.parseInt(numerator);
       int finaldenominator = Integer.parseInt(denominator);
       // Second number
       int wholenumber2 = Integer.parseInt(whole2);
       int finalnumerator2 = Integer.parseInt(numerator2);
       int finaldenominator2 = Integer.parseInt(denominator2);

       // This part of the code checks to see what operation will be used to run the equation through
       if (operator.equals("+")) // DO NOT use '==' because that refers to numbers equaling one another, not characters (String)
       {
    	   if ((finaldenominator == 1) && (finaldenominator2 == 1))
    	   {
    		   int sum = wholenumber + wholenumber2;
               // Must be converted back to String b/c 'produceAnswer' calls for String
               return Integer.toString(sum);
    	   }
    	   if (finaldenominator != finaldenominator2) /* If there IS a denominator and they are not equal, the denominators must 
    		   										     be converted (this makes the math easier and a smaller chance of it 
    		   										     being incorrect */
    	   {
    		   int other = finaldenominator;
               int other2 = finaldenominator2;
               finaldenominator*=other2;
               finalnumerator*=other2;
               finaldenominator2*= other;
               finalnumerator2*= other;  
            } 
    	   // If the number in either first or second is a mixed fraction, then it must be converted to a non-reduced fraction
           // (Generally the notation would be numerator/denominator, with the numerator being larger)
           finalnumerator = mixedFraction(wholenumber,finalnumerator,finaldenominator);
           finalnumerator2 = mixedFraction(wholenumber2, finalnumerator2,finaldenominator2);
           finalnumerator+=finalnumerator2;
           String answer = (Integer.toString(finalnumerator) + "/" + Integer.toString(finaldenominator));
           if ((finalnumerator) == 0 || (finaldenominator == 0))
           {
        	   return "0"; // Has to be returned as a String and not just '0' because produceAnswer calls for String
           }
           else // If the condition earlier is not applicable, then the program returns the String 'answer'
           {
             return answer;
           }
       }
       
       /* This code is used if the operation was not addition and is subtraction (USE AN ELSE-IF because it tests the IF first to see if it's applicable)
       Just an IF statement could be utilized as well */
       else if (operator.equals("-")) 
       {
    	   if ((finaldenominator == 1) && (finaldenominator2 == 1))
           {
        	   int answer = wholenumber-wholenumber2;
               return Integer.toString(answer);     
           }
           finalnumerator = mixedFraction(wholenumber,finalnumerator,finaldenominator);
           finalnumerator2 = mixedFraction(wholenumber2,finalnumerator2,finaldenominator2);     
           if (finaldenominator != finaldenominator2)
           {
        	   int other = finaldenominator;
               int other2 = finaldenominator2;
               finaldenominator*=other2;
               finalnumerator*=other2;
               finaldenominator2*=other;
               finalnumerator2*=other;  
           } 
           finalnumerator-=finalnumerator2; // Earlier += was used but now -= because subtraction 
           String answer = (Integer.toString(finalnumerator) + "/" + Integer.toString(finaldenominator));
           if (finalnumerator == 0 || finaldenominator == 0) 
           {
        	   return "0";
           } 
           else
           {
           return answer;
           }
       }
        // If the operation is multiplication, the code moves on to this section and executes code   
        else if (operator.equals("*")) 
        {
             if ((finalnumerator == 0) && (finalnumerator2 == 0))
             {
            	 wholenumber *= wholenumber2; // Multiply the whole numbers
                 return Integer.toString(wholenumber);                  
             }
             finalnumerator = mixedFraction(wholenumber,finalnumerator,finaldenominator);
             finalnumerator2 = mixedFraction(wholenumber2, finalnumerator2,finaldenominator2);   
             
             finalnumerator*=finalnumerator2;
             finaldenominator*=finaldenominator2;
             String answer = (Integer.toString(finalnumerator) + "/" + Integer.toString(finaldenominator));
             if ((finalnumerator == 0) || (finaldenominator == 0))
             {
            	 return "0";
             } 
             else
             {
             return answer;
             }
        }
        else if (operator.equals("/")) 
        {
        	if ((finalnumerator == 0) && (finalnumerator2 == 0) && (wholenumber > 0) && (wholenumber2 > 0))
        	{
        		return Integer.toString(wholenumber)+ "/" + Integer.toString(wholenumber2); 
            }
        	else if ((finalnumerator == 0) && (finalnumerator2 == 0) && (wholenumber < 0) && (wholenumber2 < 0))
        	{
        		wholenumber2 *= -1;
                return Integer.toString(wholenumber)+ "/" + Integer.toString(wholenumber);
            }
        	finalnumerator = mixedFraction(wholenumber,finalnumerator,finaldenominator);
            finalnumerator2 = mixedFraction(wholenumber2, finalnumerator2,finaldenominator2);    
            finalnumerator*=finaldenominator2;
            finaldenominator*=finalnumerator2;
            String answer = (Integer.toString(finalnumerator)+ "/" + Integer.toString(finaldenominator));
            if ((finalnumerator == 0) && (finaldenominator == 0))
            {
            	return "0";
             }
            else 
            {
            	return answer;
            }
        }
        else
        {
             return ""; // Return statement needed for code to run properly because String needs to be returned 
        } 
    }
    
    
    public static String wholeNumber(String input) // Method calculates the whole numbers
    {
       if ((input.indexOf("_") > 0) || (input.indexOf("/") < 0))
       {
    	   String[] underscore = input.split("_"); // This checks for where there is an underscore for mixed fractions and keeps
              											// that as a whole number
           String answer = underscore[0]; /*	The whole number would be the first index of the array SO it has to be set at 0
              								because of 0-indexing */
           return answer; // 'answer' must be a String because the static method is set to return a String
       } 
       else 
       {
    	   return "0"; // If nothing is found, then a 0 is returned as a String (meaning no whole number)
       }
    }
        public static String numerator(String input) // Method calculates the numerator
        {
             if ((input.indexOf("_") > 0))
             {
            	 String[] underscore = input.split("_");
            	 String fraction = underscore[1]; // The fraction is the second index of the array
            	 String[] numbersplit = fraction.split("/"); // Numerator and denominator are separated by the '/'
            	 String numerator = numbersplit[0];
            	 return numerator;
        }
             else if ((input.indexOf("/") > 0)) // This will check to see if the second number is a fraction
             {
            	 String[] numbersplit = input.split("/");
            	 String numerator = numbersplit[0];
            	 return numerator;
             } 
             else 
             {
            	 return "0";
             }
        }
        
        public static String denominator(String input) // Method calculates the denominator 
        {
             if ((input.indexOf("_") > 0))
             {
            	 String[] underscore = input.split("_");
            	 String fraction = underscore[1];
            	 String[] numbersplit = fraction.split("/");
            	 String denominator = numbersplit[1];
              return denominator;
        }
             else if ((input.indexOf("/") > 0))
             {
            	 String[] numbersplit = input.split("/");
            	 String denominator = numbersplit[1];
             
             return denominator;
             } 
             else
             {
             return "1";
             }
    }
        public static int mixedFraction(int whole, int numerator, int denominator) /* Method used to calculate the mixed fraction using the other 3 methods 
        																			wholenNumber, fraction, and denominator (CALLED IN 'produceAnswer' */
        {
        	if (whole > 0) 
        	{
        		numerator += denominator * whole; 
             }
             if (whole < 0) 
             {
            	 whole*=denominator;
                 numerator *= -1;
                 numerator+=whole;
                 return numerator;
             }
             else
             {
             return numerator;
             }
        }
        public static int Simplification (int finalnumerator, int finaldenominator) // Using this method for final checkpoint due Friday, disregard :)
        {
        	if (finalnumerator % finaldenominator == 0)
        	{
        		return finalnumerator/finaldenominator;
        	}
        	else if (finalnumerator % finaldenominator != 0)
        	{
        		int first = finalnumerator % finaldenominator;
        		String first1 = Integer.toString(first);
        		int second = finalnumerator/ finaldenominator;
        		String second2 = Integer.toString(second);
        		return second + "_" + first;
        	}
        	
			return 0;
        }
        
    /* This static method is used to find the GCD or 'Greatest Common Factor'; I will not be using this method
        in this checkpoint (3) and will most likely use them when simplifying */
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
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}

