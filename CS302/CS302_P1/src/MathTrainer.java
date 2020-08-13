///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            MathTrainer
// Files:            MathTrainer.java
// Semester:         (CS 302) Spring 2016
//
// Author:           Spencer George
         
//////////////////////////// 80 columns wide //////////////////////////////////

//import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
/**
 * Represents a math quiz. Provides feedback for incorrect answers.
 *   
 * 
 * &lt;p&gt;Bugs: User selection of math operator only looks at first
 * character so if they want [A]ddition and type abcdef the program will
 * execute for addition. 
 *
 * @author Spencer George
 */
public class MathTrainer 
{
	/**
	 * Single method class with no parameters. Constructs a math question(s) 
	 * to user. Uppercase or lowercase characters may be used.  
	 * 
	 * &lt;p&gt; When this method is called, it will prompt the user to pick
	 * a math operator and then prompt the user to solve three questions.
	 * 
	 */
	public static void main(String[] args) 
	{
		// Implement Program1 - Math Trainer
		
		Scanner readConsole = new Scanner(System.in);
		Random randGen = new Random( Config.RANDOM_SEED );		
		
		double userAnswer = 0.0;	// Holds user answer from input
		double correctAnswer = 0.0; // Used to compare userAnswer
		
		boolean questionAnswered = false; // Breaks single question loop
										  // when user gives valid double
		
		int questionsCorrect = 0;	// reports correctly answered question
									// before termination of program
		
		boolean invalidOperator = false; // Is set to true when user does not
										 // provide valid operation selection
		
		System.out.println("Hello and welcome to the Math Trainer!" +
			"\n======================================\n");
		
		/** Only bracketed letters are accepted in either case */
		System.out.print("Which math operation would you like to practice?"
				+ "\n\t[A]ddition"
				+ "\n\t[S]ubtraction" 
				+ "\n\t[M]ultiplication" 
				+ "\n\t[D]ivision" 
				+ "\n\t[R]emainder"
				+ "\nEnter your choice: ");
		/**
		* Check for correct selection of operator during first step
		* of question loop
		*/
		char operatorSelection = readConsole.next().toUpperCase().charAt(0);
		
		/** 
		 *  Prompts user number of questions from config.
		 *  If user is wrong, a table of possible answers is generated
		 *  for each question before the loop terminates.
		 */
		for(int i = 0; i < Config.NUMBER_OF_QUESTIONS; i++)
		{
			/** Pick a random number from the correct number of values */
			double correctChance = randGen.nextInt(Config.MAX_VALUE 
					- Config.MIN_VALUE + 1);
			/** Shift whatever number is picked into the correct range */
			double correctRange1 = correctChance + Config.MIN_VALUE;
			
			correctChance = randGen.nextInt(Config.MAX_VALUE 
					- Config.MIN_VALUE + 1);
			double correctRange2 = correctChance + Config.MIN_VALUE;
			
			/** Used to show arithmetic strings as ints */
			int tempCorrectRange1 = (int) correctRange1;
			int tempCorrectRange2 = (int) correctRange2;
			
			/** Iterate through cases to match operatorSelection */
			switch(operatorSelection)
			{
				case 'A': // Selection is addition
					System.out.print("What is the solution to the problem: " 
							+ tempCorrectRange1 + " + " 
							+ tempCorrectRange2 + " = ");
					if(!(readConsole.hasNextDouble()))
					{
						System.out.println("\nAll solutions must be entered"
								+ " as decimal numbers.");
					}
					else
					{
						questionAnswered = true;
						userAnswer = readConsole.nextDouble();
					}
					correctAnswer = correctRange1 + correctRange2;
					break;
				case 'S': // Selection is subtraction
					System.out.print("What is the solution to the problem: " 
							+ tempCorrectRange1 + " - " 
							+ tempCorrectRange2 + " = ");
					if(!(readConsole.hasNextDouble()))
					{
						System.out.println("\nAll solutions must be entered"
								+ " as decimal numbers.");
					}
					else
					{
						questionAnswered = true;
						userAnswer = readConsole.nextDouble();
					}
					correctAnswer = correctRange1 - correctRange2;
					break;
				case 'M': // Selection is multiplication
					System.out.print("What is the solution to the problem: " 
							+ tempCorrectRange1 + " x " 
							+ tempCorrectRange2 + " = ");
					if(!(readConsole.hasNextDouble()))
					{
						System.out.println("\nAll solutions must be entered"
								+ " as decimal numbers.");
					}
					else
					{
						questionAnswered = true;
						userAnswer = readConsole.nextDouble();
					}
					correctAnswer = correctRange1 * correctRange2;
					break;
				case 'D': // Selection is division
					System.out.print("What is the solution to the problem: " 
							+ tempCorrectRange1 + " / " 
							+ tempCorrectRange2 + " = ");
					if(!(readConsole.hasNextDouble()))
					{
						System.out.println("\nAll solutions must be entered"
								+ " as decimal numbers.");
					}
					else
					{
						questionAnswered = true;
						userAnswer = readConsole.nextDouble();
					}
					correctAnswer = correctRange1 / correctRange2;
					break;
				case 'R': // Selection is remainder
					System.out.print("What is the solution to the problem: " 
							+ tempCorrectRange1 + " % " 
							+ tempCorrectRange2 + " = ");
					if(!(readConsole.hasNextDouble()))
					{
						System.out.println("\nAll solutions must be entered"
								+ " as decimal numbers.");
					}
					else
					{
						questionAnswered = true;
						userAnswer = readConsole.nextDouble();
					}
					correctAnswer = correctRange1 % correctRange2;
					break;
				default: System.out.println("I'm sorry, "
						+ "I only understand choices of: A, S, M, D, or R!");
					i = Config.NUMBER_OF_QUESTIONS;
					invalidOperator = true;	// Breaks question loop. 
											// will result in termination
					break;
			}
			
			/** Testing if user answer is correct within close enough range */
			if(userAnswer < (correctAnswer + Config.CLOSE_ENOUGH) 
					&& userAnswer > (correctAnswer - Config.CLOSE_ENOUGH)
					&& questionAnswered == true)
			{
				System.out.println("That is correct!");
				questionsCorrect++;
			}
			/** User answer is double but is incorrect */
			else if(invalidOperator == false) 
			{
				System.out.println("The correct solution is " 
						+ correctAnswer + ".");
				
				double columnLowerBound;
				double rowLowerBound;
				double columnUpperBound;
				double rowUpperBound;
				
				/** 
				 * If statements below create the lower and upper bounds
				 * for the possible solutions table for both row and column
				 */
				// Set row lower bound
				if((correctRange1 - 2.0) >= Config.MIN_VALUE)
				{
					rowLowerBound = correctRange1 - 2.0;
				}
				else if((correctRange1 - 1.0) >= Config.MIN_VALUE)
				{
					rowLowerBound = correctRange1 - 1.0;
				}
				else
				{
					rowLowerBound = correctRange1;
				}
				
				// Set column lower bound
				if((correctRange2 - 2.0) >= Config.MIN_VALUE)
				{
					columnLowerBound = correctRange2 - 2.0;
				}
				else if((correctRange2 - 1.0) >= Config.MIN_VALUE)
				{
					columnLowerBound = correctRange2 - 1.0;
				}
				else
				{
					columnLowerBound = correctRange2;
				}
				
				// Set row upper bound
				if((correctRange1 + 2.0) <= Config.MAX_VALUE)
				{
					rowUpperBound = correctRange1 + 2.0;
				}
				else if((correctRange1 + 1.0) <= Config.MAX_VALUE)
				{
					rowUpperBound = correctRange1 + 1.0;
				}
				else
				{
					rowUpperBound = correctRange1;
				}
				
				// Set column upper bound
				if((correctRange2 + 2.0) <= Config.MAX_VALUE)
				{
					columnUpperBound = correctRange2 + 2.0;
				}
				else if((correctRange2 + 1.0) <= Config.MAX_VALUE)
				{
					columnUpperBound = correctRange2 + 1.0;
				}
				else
				{
					columnUpperBound = correctRange2;
				}
				
				int rowCount = 0; // Used as loop counter to determine when to 
								  // print header row, --- row and data rows
				
				/** 
				 *  Loops and prints table by row
				 */
				do
				{
					if(rowCount == 0) // Print header row
					{
						System.out.print("\t|");
						double columnCount = columnLowerBound; 
						
						/** 
						 * Prints each column of header row
						 * starting at column lower bound
						 */
						do
						{
							System.out.printf("%7.2f", columnCount);
							columnCount++;
						}while(columnCount <= columnUpperBound);
					}
					else if(rowCount == 1) // Print line break
					{
						System.out.print("\n---------");
						double columnCount = columnLowerBound;
						do
						{
							System.out.print("-------");
							columnCount++;
						}while(columnCount <= columnUpperBound);
								
					}
					else // Print answer rows
					{
						System.out.println("");
						double columnCount = columnLowerBound;
						System.out.printf("%7.2f", rowLowerBound);
						System.out.print(" |");
						
						/**
						 * Prints data columns for row.
						 * Loops by column count set by bounds.
						 * 
						 */
						do
						{
							/**
							 * Rechecks operator selection and does math
							 * calculations to provide possible solution
							 * for each column in row.
							 */
							switch(operatorSelection)
							{
								case 'A': System.out.printf("%7.2f", (
										rowLowerBound + columnCount));
									break;
								case 'S': System.out.printf("%7.2f", (
										rowLowerBound - columnCount));
									break;
								case 'M': System.out.printf("%7.2f", (
										rowLowerBound * columnCount));
									break;
								case 'D': System.out.printf("%7.2f", (
										rowLowerBound / columnCount));
									break;
								case 'R': System.out.printf("%7.2f", (
										rowLowerBound % columnCount));
									break;
							}
							columnCount++;
						}while(columnCount <= columnUpperBound);
						rowLowerBound++;
					}
					rowCount++;
				}while(rowLowerBound <= rowUpperBound);
				System.out.println("");
			}
		}
		
		/**
		 *  Prints question answered correctly totals if questions
		 *  were presented to user and they didn't select an invalid 
		 *  choice.
		 */
		if(invalidOperator == false)
		{
			System.out.println("\n*** You answered " 
					+ questionsCorrect 
					+ " out of " 
					+ Config.NUMBER_OF_QUESTIONS 
					+ " questions correctly.");
		}
		System.out.println("======================================" +
			"\nThank you for using the Math Trainer!");
	}
}
