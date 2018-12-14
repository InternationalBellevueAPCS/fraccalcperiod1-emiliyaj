/*Emiliya Jones
 *Period 1
 *Fraction Calculator
 *14 December, 2018 
 */
/* Note: I had to change my code from earlier checkpoints 1 and 2 because I kept failing the tests and some of my numbers were said to be out of range. I used the split function
instead of using a for-loop to search for the operand because it assured that my substring would not be out of range and created less room for error */
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
    public static String produceAnswer(String input) // Method that is called in main to find the answer (Returned as String)
    {
    	if (input.indexOf(" ") < 0)
    	{
             return "Please input a correct number or fix your input format. Thank you!"; // If the input is inputed incorrectly (i.e. 4+ 2 (there is no 
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
           //String answer = Integer.toString(finalanswer); Lines 91-92 utilized in checkpoint 3, NOT FINAL 
           //String answer = (Integer.toString(finalnumerator) + "/" + Integer.toString(finaldenominator));
           if ((finalnumerator) == 0 || (finaldenominator == 0))
           {
        	   return "0"; // Has to be returned as a String and not just '0' because produceAnswer calls for String
           }
           else // Simplifies the answer by using static method 'Simplification' below 
           {
             return Simplification(wholenumber,finalnumerator,finaldenominator); 
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
           if ((Math.abs(finalnumerator))> (Math.abs(finaldenominator))) /* Makes sure that even if it is a negative
        	   															then the number is tested and simplified */
           {
        	   return Simplification(wholenumber,finalnumerator,finaldenominator);
           }
           if ((finalnumerator != 0) && (finalnumerator2!= 0))
           {
        	   int actualnumerator = Reduction(finalnumerator,finaldenominator);
               int actualdenominator = Reduction(finaldenominator,finalnumerator);
               return Integer.toString(actualnumerator) + "/" + Integer.toString(actualdenominator); 
               // Return as a String because 'produceAnswer' calls for String
           }
           String answer = (Integer.toString(finalnumerator) + "/" + Integer.toString(finaldenominator));
           if (finalnumerator == 0 || finaldenominator == 0) // If they're both 0, it just returns 0
           {
        	   return "0";
           } 
           else
           {
        	   return answer; // Essentially this should not return because it has to simplify in the first condition
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
             if ((Math.abs(finalnumerator)) > (Math.abs(finaldenominator))) 
             {
            	 return Simplification(wholenumber,finalnumerator,finaldenominator);
             }
             if (finalnumerator != 0 && finaldenominator != 0) // Numbers must be reduced 
             {
                 finalnumerator = Reduction(finalnumerator, finaldenominator);
                 finaldenominator = Reduction(finaldenominator, finalnumerator);
             }
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
        	{
        		if ((wholenumber != 0) && (wholenumber2 != 0)) // If the solution is not just 0 (in which case the program would just return 0, as in the bottom of this else-if's return)
        		{
        			if (wholenumber == wholenumber2) 
        			{
        				return "1";
        			}
        		}
        		if (wholenumber2 == 1) 
        		{
        			return Integer.toString(wholenumber);
                }
        		else if (wholenumber2 == -1) 
                {
        			wholenumber *= -1; // Has to be converted to a negative to make sure that the number is not returned falsely positive 
        			return Integer.toString(wholenumber);
                }
                if ((wholenumber < 0) && (wholenumber2 < 0))
                {
                	wholenumber *=-1; // If they are both negative, then the output is positive WHICH MEANS they must both be multiplied by -1 to keep same number but convert to positive
                    wholenumber2 *=-1; // Note: Math.abs could also be utilized for this portion of the code
                }
                if (Math.abs(wholenumber) == 0) 
                {
                	if ((Math.abs(finalnumerator) == 0) || (Math.abs(finaldenominator) == 0))
                	{
                		return "0";
                	}
                }
                if ((finalnumerator == 0) && (finalnumerator2 == 0) && (wholenumber != 0) && (wholenumber2 != 0))
                {
                	finalnumerator = wholenumber;
                    finaldenominator = wholenumber2;                    
               if (Math.abs(finalnumerator)> Math.abs(finaldenominator))
               {
            	   return Simplification(wholenumber,finalnumerator,finaldenominator);
               }
               return Integer.toString(wholenumber)+ "/" + Integer.toString(wholenumber2);
               }
                else if ((finalnumerator == 0) && (finalnumerator2 == 0) && (wholenumber < 0) && (wholenumber2 < 0)) // This is if the input is both negatives
                {
                	wholenumber *= -1;
                    wholenumber2 *= -1;
                    if (Math.abs(finalnumerator)>Math.abs(finaldenominator)) 
                    {
                    	return Simplification(wholenumber,finalnumerator,finaldenominator);
                    }
                    return Integer.toString(wholenumber)+ "/" + Integer.toString(wholenumber2);
                }
                finalnumerator = mixedFraction(wholenumber,finalnumerator,finaldenominator);
                finalnumerator2 = mixedFraction(wholenumber2, finalnumerator2,finaldenominator2);
                wholenumber = 0;
                if (finalnumerator2 < 0) 
                {
                	finalnumerator *= finaldenominator2;
                	finalnumerator *=-1;
                	finaldenominator *=finalnumerator2;
                	finaldenominator *=-1;
                	} else
                	{
                		finalnumerator *= finaldenominator2;
                		finaldenominator *=finalnumerator2;
                	}
                if ((finalnumerator != 0) && (finaldenominator != 0))
                {
                	return Simplification(wholenumber,finalnumerator,finaldenominator);
                }
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
        	
        }
       return ""; // Need something to return in String form so that the program can run 
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
        public static int Reduction(int numerator, int denominator) /* This method is called when testing the operations, and is used
        											to reduce the final answer, after simplification or without 
        											if just a fraction */
        {
        	int gcd = GCD(numerator, denominator);
        	if (gcd != 0) 
        	{
        		return numerator/gcd;
        	} 
        	else
        	{
                return numerator;
        	}
        }
        public static String Simplification (int whole, int numerator, int denominator) // Simplifies any fractions/mixed numbers
        {
        	if (Math.abs(numerator) >= Math.abs(denominator)) // Makes sure that negatives are taken into account
        	{
                whole = numerator/denominator;
                numerator = numerator % denominator;
                if (numerator == denominator)
                {
                	whole++; // When the numerator and denominator are the same, then they divide by one another for 1
                	return Integer.toString(whole);
                }
                }
        	if (denominator < 0) // If denominator is negative
        	{
                denominator *= -1; // Multiplied by -1 to make simplification easier
            }
        	if ((numerator <0) && (whole < 0)) 
        	{
                numerator*= -1;
        	}
        	if ((numerator <0) && (whole >0))
        	{
                numerator*= -1;
                whole *= -1;
            }
        	
        	int a = Reduction(numerator,denominator); // Call to reduce the numerator and denominator 
        	int b = Reduction(denominator,numerator);
        	if (a == b) // This means that when divided by one another, they would return 1
        	{
                return "1";
            }
        	 if (a == 0 || b == 0) // If there is NO numerator and denominator, then there is just a whole number
        	{
        		return Integer.toString(whole);
        	}
        	else if (whole == 0) // There is no whole number and the reduced result is just a fraction
        	{
        		return (Integer.toString(a) + "/" + Integer.toString(b));
        	}
        	return (Integer.toString(whole) + "_" + Integer.toString(a) + "/" + Integer.toString(b));
        	/* This needs to be returned if none of the conditions are met, meaning it is a mixed number 
        	and has both a whole number and fraction that is REDUCED */
      }
        
    /* This static method is used to find the GCD or 'Greatest Common Factor'*/
    public static int GCD(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) 
        {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }

}