import java.util.Scanner;

/**
 * The PrimeMethods class is used to demonstrate static methods and how
 * they are used to make programs modular and task oriented.
 *
 * @author David Guild and Jim Skrentny, copyright 2011-2012
 */

public class PrimeMethods
{

	public static void main(String[] args)
	{

		Scanner userInput = new Scanner(System.in);

		System.out.println("Input an integer: ");
		int userNumber = userInput.nextInt();



		//**** TIP: Remove the first "/" below to comment out a section.
		/*
		//TODO Task 1:

		if (PrimeMethods.isPrime(userNumber))
		{
		    System.out.println(userNumber + " is prime!");
		}
		else
		{
		    System.out.println(userNumber + " is not prime.");
		}
		// end of Task 1 section */

		//For Task 1, ignore the rest of the main method below.



		//**** TIP: Add a "/" in front of the "/*" to uncomment a section.

		/*
		//TODO Task 2:
		System.out.print("The prime factorization of " + userNumber + " is: ");
		
		while(findMinPrimeFactor(userNumber) > 1)
		{
			System.out.print(findMinPrimeFactor(userNumber));
			userNumber = userNumber / findMinPrimeFactor(userNumber);
			if(userNumber > 1)
			{
				System.out.print(" * ");
			}
		}
		
		// end of Task 2 section */



		//*
		//TODO Task 3:
 		//Finish findGreatestCommonDivisor method first.
		System.out.println("Input another integer: ");
		int otherUserNumber = userInput.nextInt();
		System.out.println("The greatest common divisor of " + userNumber +
			" and " + otherUserNumber + " is " +
			findGreatestCommonDivisor(userNumber, otherUserNumber) );
		// end of Task 3 section */
		
	}
	

	/**
	 * Determines whether a number is prime.
	 * 
	 * Precondition: The parameter "number" should be positive.
	 * 
	 * @param number a positive integer
	 * @return true if number is prime, false if number is not prime
	 */
	public static boolean isPrime(int number)
	{

		boolean primeSoFar = true;  //assume number is prime    

		for (int i = 2; i < number; i++)
		{
			if (number % i == 0)     //does i divide number evenly?
			{
			        primeSoFar = false;  //then number is not prime
			}
		}

		return primeSoFar;    //true iff no i was found that divided number evenly

	}


	/**
	 * Finds the smallest prime factor of a number.
	 * The parameter "number" should be positive.
	 * 
	 * @param number
	 * @return the smallest prime that evenly divides number
	 */
	public static int findMinPrimeFactor (int number)
	{
		for (int i = 2; i < number; i++)
		{
			if (number % i == 0) //does i divide number evenly?
			{
				return i; //then i is the answer we want
			}
		}
		return number; //if no i was found, then number is prime
	}


	/**
	 * Finds the greatest common divisor of two integers.
	 * 
	 * @param firstNumber
	 * @param secondNumber
	 * @return the largest integer that divides both firstNumber and secondNumber
	 */
	public static int findGreatestCommonDivisor(int firstNumber, int secondNumber)
	{
		//TODO Task 3: Write this method
		int smallestNumber;
		int largestNumber;
		if(firstNumber > secondNumber)
		{
			smallestNumber = secondNumber;
			largestNumber = firstNumber;
		}
		else if(firstNumber == secondNumber) // error handling
		{
			return firstNumber;
		}
		else
		{
			smallestNumber = firstNumber;
			largestNumber = secondNumber;
		}
		
		for(int i = smallestNumber - 1; i >= 1; i--)
		{
			if(smallestNumber % i == 0)
			{
				if(largestNumber % i == 0)
				{
					return i;
				}
			}
		}
		return 0; //replace this line with code that returns the correct value
	}

}